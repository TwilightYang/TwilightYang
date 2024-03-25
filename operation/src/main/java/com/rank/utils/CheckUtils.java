package com.rank.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class CheckUtils {
    public static List<String> readAnswersFromFile(String filePath) {
        List<String> answers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 去除序号前缀，序号与答案之间用：分隔
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    answers.add(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answers;
    }

    public static void gradeWrite(String filePath, int right, int wrong,int[] rightNum,int[] wrongNum){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("正确题数: " + right + "\n");
            bw.write("正确题目序号: ");
            for (int i = 0; i < rightNum.length; i++) {
                if (rightNum[i] != 0) {
                    bw.write(rightNum[i] + " ");
                }
        }
            bw.write("\n");
            bw.write("错误题数: " + wrong + "\n");
            bw.write("错误题目序号: ");
            for (int i = 0; i < wrongNum.length; i++) {
                if (wrongNum[i] != 0){
                    bw.write(wrongNum[i] + " ");
                }
        }
            bw.write("\n");
            bw.write("正确率: " + (double) right / (right + wrong) * 100 + "%\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
