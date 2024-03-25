package com.rank.utils;

import com.rank.calculate.CalculateStack;
import com.rank.calculate.TitleAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MathProduceUtils {
    private static List<TitleAnswer> recordProduce = new ArrayList<>();

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            3
            , 10
            , 1000l
            , TimeUnit.MILLISECONDS
            , new ArrayBlockingQueue<Runnable>(25)
    );

    public static List<TitleAnswer> produceMaths(int n, double r) {

        recordProduce.clear();

        //获取数量
        int pageLimit = n;
        //获取大小限制
        double numberLimit = r;

        int intNumber = (int)(Math.random()*numberLimit+1);
        int fractionNumber = (int)(Math.random()*numberLimit+1) - intNumber;

        threadPoolExecutor.submit(()->{
            for (int i = 0; i < intNumber ; ++i)
                recordProduce.add(produceMathsInt(numberLimit));
        });

        threadPoolExecutor.submit(()->{
            for (int i = 0; i < pageLimit - intNumber - fractionNumber ; ++i)
                recordProduce.add(produceMathsIntMixFraction(numberLimit));
        });

        threadPoolExecutor.submit(()->{
            for (int i = 0; i < fractionNumber ; ++i)
                recordProduce.add(produceMathsFraction(numberLimit));
        });

        while(recordProduce.size() < numberLimit) {
            try {
                Thread.sleep(2000l);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return recordProduce;
    }

    private static TitleAnswer produceMathsInt(Double numberLimit) {
        int[] numberLists = {
                (int)(Math.random()*numberLimit+1),
                (int)(Math.random()*numberLimit+1),
                (int)(Math.random()*numberLimit+1),
                (int)(Math.random()*numberLimit+1)
        };
        Arrays.sort(numberLists);

        String[] targetList = {
                String.valueOf(numberLists[0]),
                String.valueOf(numberLists[1]),
                String.valueOf(numberLists[2]),
                String.valueOf(numberLists[3])
        };

        TitleAnswer titleAnswer = null;
        while((titleAnswer = produceMathsModule(targetList)) == null) ;
        return titleAnswer;
    }

    private static TitleAnswer produceMathsModule(String[] numberLists) {
        //运算符数量
        double randomOperationTime = Math.random()*3+1;

        CalculateStack calculate = new CalculateStack();

        int pos = 0;
        calculate.add(numberLists[pos]);
        String titleDescription = numberLists[pos++] + "";
        for (int i = 0 ; i < randomOperationTime ; ++i) {
//        for (int i = 0 ; i < 3 ; ++i) {
            //运算符随机选择
            double randomOperation = Math.random()*4;
            char operation = operationChoice(randomOperation);
            calculate.addFirstElem(operation);
            calculate.addFirstElem(numberLists[pos]);
            titleDescription = titleDescription + operation +numberLists[pos++];
            if(pos == 4) break;
        }

        String checkRight = String.valueOf(calculate.returnsCalculate());
        if (checkRight.contains("-")) return null;

        return new TitleAnswer(titleDescription, checkRight);
    }

    private static TitleAnswer produceMathsFraction(Double numberLimit) {
        String[] numberLists={
                produceFraction(numberLimit),
                produceFraction(numberLimit),
                produceFraction(numberLimit),
                produceFraction(numberLimit)
        };

        TitleAnswer titleAnswer = null;
        while((titleAnswer = produceMathsModule(numberLists)) == null) ;
        return titleAnswer;
    }

    private static String produceFraction(Double numberLimit) {
        int denominatorNumber = (int)(Math.random()*numberLimit+1);
        int moleculeNumber = (int)(Math.random()*denominatorNumber+1);
        int integerNum = (int)(Math.random()*numberLimit);

        return integerNum==0?moleculeNumber+"/"+denominatorNumber:(denominatorNumber==moleculeNumber?integerNum+"":integerNum+"'"+moleculeNumber+"/"+denominatorNumber);
    }

    private static TitleAnswer produceMathsIntMixFraction(Double numberLimit) {

        String[] numberLists={
                produceFraction(numberLimit),
                produceFraction(numberLimit),
                String.valueOf((int)(Math.random()*numberLimit+1)),
                String.valueOf((int)(Math.random()*numberLimit+1))
        };

        TitleAnswer titleAnswer = null;
        while((titleAnswer = produceMathsModule(numberLists)) == null) ;
        return titleAnswer;
    }

    private static char operationChoice(double randomOperation) {

        if (randomOperation > 0 && randomOperation < 1) return '➕';
        else if (randomOperation > 1 && randomOperation < 2) return '➖';
        else if (randomOperation > 2 && randomOperation < 3) return '➗';
        else if (randomOperation > 3 && randomOperation < 4) return '✖';

        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(produceMathsIntMixFraction(10d).toString());
    }
}
