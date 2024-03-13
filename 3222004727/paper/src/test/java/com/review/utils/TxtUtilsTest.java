package com.review.utils;

import org.junit.Test;

public class TxtUtilsTest {
    @Test
    public void readTest() {
        // 路径存在 读取成功
        String str = TxtUtils.readFile("D:/test/orig.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }
    @Test
    public void readTestErr() {
        // 路径不存在 读取失败
        String str = TxtUtils.readFile("D:/test/orig111.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }
    @Test
    public void writeTest() {
        // 路径存在 写入成功
        double[] elem = {0.12, 0.23, 0.34, 0.45, 0.56,0.99,0.88};
        for (double v : elem) {
            TxtUtils.writeFile(v, "D:/test/answer.txt");
        }
    }
    @Test
    public void writeTestErr() {
        //路径错误 写入失败
        double[] elem = {0.12, 0.23, 0.34, 0.45, 0.56,0.99,0.88};
        for (double v : elem) {
            TxtUtils.writeFile(v, "QQ:/test//answer.txt");
        }
    }
}
