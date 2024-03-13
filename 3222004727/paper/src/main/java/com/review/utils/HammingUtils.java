package com.review.utils;

public class HammingUtils {
    /**
     * 计算两个SimHash值的海明距离
     *
     * @param simHash1 第一个SimHash值
     * @param simHash2 第二个SimHash值
     * @return 海明距离
     */
    public static int calculateHammingDistance(String simHash1, String simHash2) {
        // 检查两个SimHash值的长度是否相同
        if (simHash1.length() != simHash2.length()) {
            throw new IllegalArgumentException("错误：SimHash值长度不相同！！");
        }

        int distance = 0;
        // 遍历两个SimHash值的每一位，比较是否相同
        for (int i = 0; i < simHash1.length(); i++) {
            if (simHash1.charAt(i) != simHash2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * 输入两个simHash值，输出相似度
     *
     * @param simHash1 第一个SimHash值
     * @param simHash2 第二个SimHash值
     * @return 相似度
     */
    public static double calculateSimilarity(String simHash1, String simHash2) {
        int hammingDistance = calculateHammingDistance(simHash1, simHash2);
        int totalBits = simHash1.length();
        return  (1 - (double)hammingDistance / totalBits) * 100;
    }
}
