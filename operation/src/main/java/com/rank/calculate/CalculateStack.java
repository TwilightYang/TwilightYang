package com.rank.calculate;

import java.util.Stack;

public class CalculateStack extends Stack {
    public synchronized boolean addFirstElem(Object o) {
        o = String.valueOf(o);
        if (this.size() == 0) return super.add(o);

        if (this.peek().equals("✖")) {
            this.pop();
            String firstNumber = String.valueOf(this.pop());
            String secondNumber = String.valueOf(o);
            this.add(calculate(firstNumber, secondNumber, '✖'));
            return true;
        } else if (this.peek().equals("➗")) {
            this.pop();
            String firstNumber = String.valueOf(this.pop());
            String secondNumber = String.valueOf(o);
            this.add(calculate(firstNumber, secondNumber, '➗'));
            return true;
        } else return super.add(o);
    }

    private String calculate(String firstNumber, String secondNumber, char operation) {
        boolean firstChange = checkIsFraction(firstNumber);
        boolean secondChange = checkIsFraction(secondNumber);

        NumberRecord firstNumberRecord = NumberRecord.createNumberRecord(firstNumber, firstChange);
        NumberRecord secondNumberRecord = NumberRecord.createNumberRecord(secondNumber, secondChange);
        NumberRecord numberRecord = new NumberRecord();


        if (operation == '✖')
            numberRecord = NumberRecord.multiNumberRecord(firstNumberRecord, secondNumberRecord);
        else if (operation == '➗')
            numberRecord = NumberRecord.divisionNumberRecord(firstNumberRecord, secondNumberRecord);
        else if (operation == '➕')
            numberRecord = NumberRecord.addNumberRecord(firstNumberRecord, secondNumberRecord);
        else if (operation == '➖')
            numberRecord = NumberRecord.subtractionNumberRecord(firstNumberRecord, secondNumberRecord);

        return numberRecord.toString();
    }

    private boolean checkIsFraction(Object o) {
//        检查目标字符串target是否包含一个或多个分隔符（即'/'字符）
        String target = String.valueOf(o);
//        return target.indexOf("/")!=-1;
        return target.contains("/");
    }

    public static class NumberRecord {
//        用于处理分数加减乘除
        private Integer integerNum;  //整数部分
        private Integer moleculeNumber; //分子
        private Integer denominatorNumber; //分母
        private static NumberRecord numberRecord = new NumberRecord();
        private boolean isFraction; //是否为分数
        private boolean isHasInt; //是否包含整数

        public NumberRecord() {
            this.integerNum = 0;
            this.moleculeNumber = 0;
            this.denominatorNumber = 0;
            this.isFraction = true;
            this.isHasInt = true;
        }

        public NumberRecord(Integer integerNum, Integer moleculeNumber, Integer denominatorNumber, boolean isFraction, boolean isHasInt) {
            this.integerNum = integerNum;

            if (integerNum<0) this.moleculeNumber = -moleculeNumber;
            else this.moleculeNumber = moleculeNumber;

            this.denominatorNumber = denominatorNumber;
            this.isFraction = isFraction;
            this.isHasInt = isHasInt;
        }

        public static NumberRecord createNumberRecord(String target, boolean isFraction) {
            if (isFraction) {
                int intNum = target.indexOf("'");
                int childrenNum = target.indexOf("/");
                int denominatorNumber = Integer.parseInt(target.substring(childrenNum + 1));

                if (intNum == -1)
                    return new NumberRecord(
                            0,
                            Integer.parseInt(target.substring(0,childrenNum)),
                            denominatorNumber,
                            true,
                            false
                    );
                else {
                    return new NumberRecord(
                            Integer.parseInt(target.substring(0,intNum)),
                            Integer.parseInt(target.substring(intNum+1, childrenNum)),
                            denominatorNumber,
                            true,
                            true
                    );
                }
            } else return new NumberRecord(
                    Integer.parseInt(target),
                    0,
                    0,
                    false,
                    true
            );
        }

        private static void clearNumberRecord() {
            numberRecord.integerNum = 0;
            numberRecord.moleculeNumber = 0;
            numberRecord.denominatorNumber = 0;
            numberRecord.isFraction = true;
            numberRecord.isHasInt = true;
        }

        private static NumberRecord adjustRecord(NumberRecord numberRecord) {
            //同号处理
            if (numberRecord.integerNum > 0 && numberRecord.moleculeNumber < 0) {
                numberRecord.integerNum--;
                numberRecord.moleculeNumber = 1 * numberRecord.denominatorNumber + numberRecord.moleculeNumber;
            } else if(numberRecord.integerNum < 0 && numberRecord.moleculeNumber > 0) {
                numberRecord.integerNum++;
                numberRecord.moleculeNumber = -1 * numberRecord.denominatorNumber + numberRecord.moleculeNumber;
            }

            //归正
            if (numberRecord.moleculeNumber > numberRecord.denominatorNumber) {
                numberRecord.integerNum += numberRecord.moleculeNumber / numberRecord.denominatorNumber;
                numberRecord.moleculeNumber = numberRecord.moleculeNumber % numberRecord.denominatorNumber;
            }

            //去除分数
            if (numberRecord.moleculeNumber == 0) {
                numberRecord.denominatorNumber = 0;
                numberRecord.isFraction = false;
                numberRecord.isHasInt = false;
            }

            //约分
            if (numberRecord.isFraction) approximatelyDivided();

            if (numberRecord.integerNum != 0)
                numberRecord.isHasInt = true;

            return numberRecord;
        }

        private static void approximatelyDivided() {
            // 获取分母，并判断分子的正负，将分子取绝对值
            int biggerNumber = numberRecord.denominatorNumber;
            boolean pos = numberRecord.moleculeNumber<0?true:false;
            int smallerNumber = pos?-numberRecord.moleculeNumber:numberRecord.moleculeNumber;
            // 使用辗转相减法找到最大公约数
            while(biggerNumber != smallerNumber) {
                biggerNumber -= smallerNumber;
                if (biggerNumber < smallerNumber) {
                    int change = biggerNumber;
                    biggerNumber = smallerNumber;
                    smallerNumber = change;
                }
            }
            // 将分子和分母都除以最大公约数，实现分数的大致等分
            numberRecord.moleculeNumber /= biggerNumber;
            numberRecord.denominatorNumber /= biggerNumber;
        }

        public static NumberRecord addNumberRecord(NumberRecord first, NumberRecord second) {
            clearNumberRecord();

            if (first.isFraction && second.isFraction) {
                numberRecord.integerNum = first.integerNum + second.integerNum;
                numberRecord.denominatorNumber = first.denominatorNumber * second.denominatorNumber;
                numberRecord.moleculeNumber = first.moleculeNumber * second.denominatorNumber + second.moleculeNumber * first.denominatorNumber;
            } else if(first.isFraction || second.isFraction) {
                numberRecord.integerNum = first.integerNum + second.integerNum;
                numberRecord.moleculeNumber = first.isFraction?first.moleculeNumber:second.moleculeNumber;
                numberRecord.denominatorNumber = first.isFraction?first.denominatorNumber:second.denominatorNumber;
            } else numberRecord.integerNum = first.integerNum + second.integerNum;

            adjustRecord(numberRecord);

            return numberRecord;
        }

        public static NumberRecord multiNumberRecord(NumberRecord first, NumberRecord second) {
            clearNumberRecord();
            //都是分数
            if (first.isFraction && second.isFraction) {
                int firstInt = first.integerNum * first.denominatorNumber;
                int secondInt = second.integerNum * second.denominatorNumber;
                numberRecord.moleculeNumber = (first.moleculeNumber + firstInt) * (second.moleculeNumber + secondInt);
                numberRecord.denominatorNumber = first.denominatorNumber * second.denominatorNumber;
            } else if(first.isFraction || second.isFraction) {
                //只有一个是分数
                numberRecord.integerNum = 0;
                int addNum = first.isFraction?
                        (first.integerNum * first.denominatorNumber + first.moleculeNumber) * second.integerNum :
                        (second.integerNum * second.denominatorNumber + second.moleculeNumber) * first.integerNum
                        ;
                if (second.integerNum < 0 && first.integerNum < 0) addNum = -addNum;
                numberRecord.moleculeNumber = addNum;
                numberRecord.denominatorNumber = first.isFraction?first.denominatorNumber:second.denominatorNumber;
            } else numberRecord.integerNum = first.integerNum * second.integerNum;

            adjustRecord(numberRecord);

            return numberRecord;
        }

        public static NumberRecord subtractionNumberRecord(NumberRecord first, NumberRecord second) {
            clearNumberRecord();
            if (first.isFraction && second.isFraction) {
                numberRecord.integerNum = first.integerNum - second.integerNum;
                numberRecord.denominatorNumber = first.denominatorNumber * second.denominatorNumber;
                numberRecord.moleculeNumber = first.moleculeNumber * second.denominatorNumber - second.moleculeNumber * first.denominatorNumber;
            } else if(first.isFraction || second.isFraction) {
                numberRecord.integerNum = first.integerNum - second.integerNum;
                numberRecord.moleculeNumber = first.isFraction?first.moleculeNumber:-second.moleculeNumber;
                numberRecord.denominatorNumber = first.isFraction?first.denominatorNumber:second.denominatorNumber;
            } else numberRecord.integerNum = first.integerNum - second.integerNum;

            adjustRecord(numberRecord);

            return numberRecord;
        }

        public static NumberRecord divisionNumberRecord(NumberRecord first, NumberRecord second) {
            clearNumberRecord();
            //都是分数
            if (first.isFraction && second.isFraction) {
                numberRecord.integerNum = 0;
                int firstInt = first.integerNum * first.denominatorNumber + first.moleculeNumber;
                int secondInt = second.integerNum * second.denominatorNumber + second.moleculeNumber;
                numberRecord.moleculeNumber = firstInt * second.denominatorNumber;
                numberRecord.denominatorNumber = first.denominatorNumber * secondInt;
            } else if(first.isFraction || second.isFraction) {
                //只有一个是分数
                int firstInt = first.isFraction?first.integerNum * first.denominatorNumber + first.moleculeNumber:first.integerNum;
                int secondInt = second.isFraction?second.integerNum * second.denominatorNumber + second.moleculeNumber:second.integerNum;
                numberRecord.moleculeNumber = first.isFraction?firstInt * 1:second.denominatorNumber * firstInt;
                numberRecord.denominatorNumber = first.isFraction?first.denominatorNumber * secondInt : secondInt;
            } else {
                if (first.integerNum % second.integerNum == 0) numberRecord.integerNum = first.integerNum / second.integerNum;
                else {
                    numberRecord.moleculeNumber = first.integerNum;
                    numberRecord.denominatorNumber = second.integerNum;
                }
            }

            adjustRecord(numberRecord);

            return numberRecord;
        }

        @Override
        public String toString() {
            if (isFraction)
                return integerNum + "'"
                        + moleculeNumber + "/"
                        + denominatorNumber;
            else return integerNum + "";
        }
    }

    public synchronized  String returnsCalculate() {
        Stack stack = new Stack();
        while(!this.isEmpty())
            stack.add(this.pop());


        CalculateStack calculateStack = new CalculateStack();
        while(!stack.isEmpty()) {
            if (calculateStack.size() == 0) calculateStack.add(stack.pop());
            if (calculateStack.peek().equals("➕")) {
                calculateStack.pop();
                String firstNumber = String.valueOf(calculateStack.pop());
                String secondNumber = String.valueOf(stack.pop());
                calculateStack.add(calculate(firstNumber, secondNumber, '➕'));
            } else if (calculateStack.peek().equals("➖")) {
                calculateStack.pop();
                String firstNumber = String.valueOf(calculateStack.pop());
                String secondNumber = String.valueOf(stack.pop());
                calculateStack.add(calculate(firstNumber, secondNumber, '➖'));
            } else if (!stack.isEmpty()) calculateStack.add(stack.pop());
        }

        return String.valueOf(calculateStack.pop());
    }
}
