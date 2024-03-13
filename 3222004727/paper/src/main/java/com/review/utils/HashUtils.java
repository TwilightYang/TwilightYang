package com.review.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.review.utils.ExceptionUtils;

public class HashUtils {
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

    /**
     * 传入String,计算出它的simHash值
     * @param str 传入字符串
     * @return 返回str的simHash值
     */
    public static String getSimHash(String str){
        // 文本长度太短  HanLp无法取关键字
        try{
            if(str.length() < 200) throw new ExceptionUtils("文本过短！");
        }catch (ExceptionUtils e){
            e.printStackTrace();
            return null;
        }
        // 用数组表示特征向量,取128位,从 0 1 2 位开始表示从高位到低位
        int[] vector = new int[128];
        // 分词
        List<String> keywordList = HanLP.extractKeyword(str, str.length());
        // hash
        int size = keywordList.size();
        int i = 0;
        for(String keyword : keywordList){
            // 获取hash值
            String keywordHash = getHash(keyword);
            // 加权、合并
            for (int j = 0; j < vector.length; j++) {
                // 对keywordHash的每一位与'1'进行比较
                if (keywordHash.charAt(j) == '1') {
                    //权重分10级，由词频从高到低，取权重10~0
                    vector[j] += (10 - (i / (size / 10)));
                } else {
                    vector[j] -= (10 - (i / (size / 10)));
                }
            }
            i++;
        }
        // 降维
        StringBuilder simHash = new StringBuilder();
        for (int k : vector) {
            // 从高位遍历到低位
            if (k <= 0) {
                simHash.append("0");
            } else {
                simHash.append("1");
            }
        }
        return simHash.toString();
    }
}
