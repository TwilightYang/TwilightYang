package com.rank;

import com.rank.calculate.TitleAnswer;
import com.rank.utils.CheckUtils;
import com.rank.utils.MathProduceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("请输入生成题目的数量-n:");
        int n = in.nextInt();
        System.out.println("请输入生成题目的数量范围-r:");
        int r = in.nextInt();

        List<TitleAnswer> titleAnswer = MathProduceUtils.produceMaths(n,r);
        int count = (int) titleAnswer.stream().count();
        System.out.println("生成的题目数为：" + count);
        // 将题目按序号写入文件
        try (FileWriter fileWriter = new FileWriter("src/main/resources/exercise.txt");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            // 清空文件
            printWriter.print("");
            for (int i = 0; i < count; i++) {
                printWriter.println( (i + 1) + "、" + titleAnswer.get(i).getProblemDescription());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("题目已生成并写入文件");

        // 将题目序号写入答案文件
        try (FileWriter fileWriter = new FileWriter("src/main/resources/answers.txt");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (int i = 0; i < count; i++) {
                printWriter.println( "第" +(i + 1) + "题:" );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("请在 answers.txt 文件中输入完答案后继续...");

        // 定时检测文件是否有新内容输入
        try {
            File file = new File("src/main/resources/answers.txt");
            long lastModified = file.lastModified();
            while (true) {
                Thread.sleep(5000); // 每隔5秒检查一次

                if (file.exists() && file.lastModified() > lastModified) {
                    lastModified = file.lastModified();
                    System.out.println("检测到新内容，开始检查答案...");
                    int rightCount = 0, wrongCount = 0;
                    int[] rightNumbers = new int[count];
                    int[] wrongNumbers = new int[count];
                    List<String> answers = CheckUtils.readAnswersFromFile("src/main/resources/answers.txt");

                    for (int i = 0; i < count; i++) {
                        if (answers.get(i).equalsIgnoreCase(titleAnswer.get(i).getAnswer())){
                            rightCount++;
                            rightNumbers[i] = i + 1;
                        } else {
                            wrongCount++;
                            wrongNumbers[i] = i + 1;
                        }
                    }
                    CheckUtils.gradeWrite("src/main/resources/grade.txt",rightCount, wrongCount, rightNumbers, wrongNumbers);
                    System.out.println("答案检查完成，已生成成绩单。");
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

