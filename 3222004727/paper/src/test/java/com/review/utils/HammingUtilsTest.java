package com.review.utils;

import org.junit.Test;

import java.util.Objects;

public class HammingUtilsTest {
    @Test
    public void hammingDistanceTest0() {
        String str0 = "10101100";
        String str1 = "11001111";
        int distance = HammingUtils.calculateHammingDistance(str0,str1);
        double similarity = HammingUtils.calculateSimilarity(str0,str1);
        System.out.println("海明距离为：" + distance);
        System.out.println("相似度为：" + similarity);
    }
    @Test
    public void hammingDistanceTest1() {
        // 长度不等
        String str0 = "10101100";
        String str1 = "110011";
        int distance = HammingUtils.calculateHammingDistance(str0,str1);
//        double similarity = HammingUtils.calculateSimilarity(str0,str1);
        System.out.println("海明距离为：" + distance);
//        System.out.println("相似度为：" + similarity );
    }
    @Test
    public void hammingDistanceTest2() {
        String str0 = TxtUtils.readFile("D:/test/orig.txt");
        String str1 = TxtUtils.readFile("D:/test/orig_0.8_add.txt");
        int distance = HammingUtils.calculateHammingDistance(Objects.requireNonNull(HashUtils.getSimHash(str0)), Objects.requireNonNull(HashUtils.getSimHash(str1)));
        double similarity = HammingUtils.calculateSimilarity(HashUtils.getSimHash(str0),HashUtils.getSimHash(str1));
        System.out.println("海明距离为：" + distance);
        System.out.println("相似度为：" + similarity);
    }

    @Test
    public void hammingDistanceTest3() {
        String str0 = TxtUtils.readFile("D:/test/orig.txt");
        String str1 = TxtUtils.readFile("D:/test/orig_0.8_del.txt");
        int distance = HammingUtils.calculateHammingDistance(Objects.requireNonNull(HashUtils.getSimHash(str0)), Objects.requireNonNull(HashUtils.getSimHash(str1)));
        double similarity = HammingUtils.calculateSimilarity(HashUtils.getSimHash(str0),HashUtils.getSimHash(str1));
        System.out.println("海明距离为：" + distance);
        System.out.println("相似度为：" + similarity);
    }

    @Test
    public void hammingDistanceTest4() {
        String str0 = TxtUtils.readFile("D:/test/orig.txt");
        String str1 = TxtUtils.readFile("D:/test/orig_0.8_dis_1.txt");
        int distance = HammingUtils.calculateHammingDistance(Objects.requireNonNull(HashUtils.getSimHash(str0)), Objects.requireNonNull(HashUtils.getSimHash(str1)));
        double similarity = HammingUtils.calculateSimilarity(HashUtils.getSimHash(str0),HashUtils.getSimHash(str1));
        System.out.println("海明距离为：" + distance);
        System.out.println("相似度为：" + similarity);
    }
}
