package com.review.utils;

import org.junit.Test;

public class ExceptionUtilsTest {
    @Test
    public void exceptionTest(){
        //str.length()<200
        System.out.println(HashUtils.getSimHash("世界这么大"));
    }
    @Test
    public void exceptionTest0(){
        //长文本
        String str = TxtUtils.readFile("D:/test/orig.txt");
        System.out.println(HashUtils.getSimHash(str));
    }
}
