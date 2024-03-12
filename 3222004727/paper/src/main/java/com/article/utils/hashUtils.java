package com.article.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.math.BigInteger;

public class hashUtils {
    /**
     * 传入String，计算出它的hash值，并以字符串形式输出
     * @param str 传入的String类型字符串
     * @return 返回str的128位二进制形式的 hash值
     */
    public static String getHash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));

            // 将byte数组的哈希值转换为二进制形式的字符串，确保总是128位
            StringBuilder binaryString = new StringBuilder(new BigInteger(1, digest).toString(2));
            // 前补零以确保结果是128位
            while (binaryString.length() < 128) {
                binaryString.insert(0, "0");
            }
            return binaryString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 发生异常时返回null
        }
    }


}
