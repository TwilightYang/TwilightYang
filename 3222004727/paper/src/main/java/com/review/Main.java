package com.review;

import com.review.utils.HammingUtils;
import com.review.utils.HashUtils;
import com.review.utils.TxtUtils;

//import java.math.BigDecimal;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // 从命令行输入的路径名读取对应的文件
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入原文文件地址：");
        String path0 = scanner.nextLine();

        System.out.print("请输入抄袭版论文文件地址：");
        String path1 = scanner.nextLine();

        System.out.print("请输入输出结果文件地址：");
        String answerFile = scanner.nextLine();


        // 文件内容转化为字符串
        String str0 = TxtUtils.readFile(path0);
        String str1 = TxtUtils.readFile(path1);

//        String str0 = TxtUtils.readFile(args[0]);
//        String str1 = TxtUtils.readFile(args[1]);
//        String answerFile = args[2];
        // 求simHash值
        String simHash0 = HashUtils.getSimHash(str0);
        String simHash1 = HashUtils.getSimHash(str1);
        // 求相似度
        double similarity = HammingUtils.calculateSimilarity(simHash0, simHash1);
        // 相似度写入结果文件
        TxtUtils.writeFile(similarity, answerFile);

        System.out.println(similarity);
        // 退出程序
        System.exit(0);
    }
}
