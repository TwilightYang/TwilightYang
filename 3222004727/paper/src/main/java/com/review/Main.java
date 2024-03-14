package com.review;

import com.review.utils.HammingUtils;
import com.review.utils.HashUtils;
import com.review.utils.TxtUtils;

//import java.math.BigDecimal;

//import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("请提供正确的参数：[原文文件] [抄袭版论文的文件] [答案文件]");
            return;
        }

        // 从命令行输入的路径名读取对应的文件
        // 文件内容转化为字符串
//        String str0 = TxtUtils.readFile("D:/test/orig.txt");
//        String str1 = TxtUtils.readFile("D:/test/orig_0.8_add.txt");
//        String answerFile = "D:/test/answer.txt";
        String str0 = TxtUtils.readFile(args[0]);
        String str1 = TxtUtils.readFile(args[1]);
        String answerFile = args[2];
        // 求simHash值
        String simHash0 = HashUtils.getSimHash(str0);
        String simHash1 = HashUtils.getSimHash(str1);
        // 求相似度
        double similarity = HammingUtils.calculateSimilarity(simHash0, simHash1);

        // 相似度写入结果文件
        TxtUtils.writeFile(similarity, answerFile);
//        System.out.println(similarity);
    }
}
