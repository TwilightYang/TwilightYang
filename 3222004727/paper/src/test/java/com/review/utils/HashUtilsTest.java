package com.review.utils;

import org.junit.Test;

public class HashUtilsTest {
    @Test
    public void getHashTest() {
        // 短文本
        String[] strings = {"画画" , "多大" , "GG" , "火" , "阿凡达ya" , "版"};
        for (String string : strings) {
            String strHash = HashUtils.getHash(string);
            if (strHash != null) {
                System.out.println(strHash.length());
            }
            System.out.println(strHash);
        }
    }
//    @Test
//    public void getHashTest1() {
//        String str = "";
//        String strHash = HashUtils.getHash(str);
//            if (strHash != null) {
//                System.out.println(strHash.length());
//                System.out.println(strHash);
//            }
//            else System.out.println("Hash值为空");
//    }
    @Test
    public void getSimHashTest0() {
        // 短文本
        String str = "好";
        System.out.println(HashUtils.getSimHash(str));
    }
    @Test
    public void getSimHashTest() {
        // 长文本
        String str0 = TxtUtils.readFile("D:/test/orig.txt");
        String str1 = TxtUtils.readFile("D:/test/orig_0.8_add.txt");
        System.out.println(HashUtils.getSimHash(str0));
        System.out.println(HashUtils.getSimHash(str1));
    }
}
