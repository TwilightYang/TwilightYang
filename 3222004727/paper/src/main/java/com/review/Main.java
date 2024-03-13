package com.review;

import com.review.utils.HammingUtils;
import com.review.utils.HashUtils;
import com.review.utils.TxtUtils;
//import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // 从命令行输入的路径名读取对应的文件
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("请输入文件地址：");
//        String path0 = scanner.nextLine();
//
//        System.out.print("请输入文件地址：");
//        String Path1 = scanner.nextLine();

        // 将文件的内容转化为对应的字符串
        String str0 = TxtUtils.readFile(args[0]);
        String str1 = TxtUtils.readFile(args[1]);
        String resultFile = args[2];
        // 由字符串得出对应的 simHash值
        String simHash0 = HashUtils.getSimHash(str0);
        String simHash1 = HashUtils.getSimHash(str1);
        // 由 simHash值求出相似度
        double similarity = HammingUtils.calculateSimilarity(simHash0, simHash1);
        // 把相似度写入最后的结果文件中
        TxtUtils.writeFile(similarity, resultFile);
        // 退出程序
        System.exit(0);

//        String simHash1 = "1100101010110000101010101010101010101010101010101010101010101010";
//        String simHash2 = "1100101010110000101010101010101011101010101010101010101010101011";
//
//        double similarity = calculateSimilarity(simHash1, simHash2);
//        System.out.println("The similarity between the two SimHash values is: " + similarity + "%");
    }
}
