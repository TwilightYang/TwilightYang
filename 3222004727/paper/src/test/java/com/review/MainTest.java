package com.review;

import com.review.utils.HammingUtils;
import com.review.utils.HashUtils;
import com.review.utils.TxtUtils;
import org.junit.Test;

public class MainTest {
    @Test
    public void mainTestAll() {
        String[] args = new String[6];
        args[0] = "D:/test/orig.txt";
        args[1] = "D:/test/orig_0.8_add.txt";
        args[2] = "D:/test/orig_0.8_del.txt";
        args[3] = "D:/test/orig_0.8_dis_1.txt";
        args[4] = "D:/test/orig_0.8_dis_10.txt";
        args[5] = "D:/test/orig_0.8_dis_15.txt";
        String answerPath = "D:/test/answerAll.txt";
        for (int i=1; i< args.length;i++){
            double answer = HammingUtils.calculateSimilarity(HashUtils.getSimHash(TxtUtils.readFile(args[0])),HashUtils.getSimHash(TxtUtils.readFile(args[i])));
            TxtUtils.writeFile(answer, answerPath);
        }
    }

    @Test
    public void mainTeatSame() {
        String str = "D:/test/orig.txt";
        String answerPath = "D:/test/answerSame.txt";
        double answer = HammingUtils.calculateSimilarity(HashUtils.getSimHash(TxtUtils.readFile(str)),HashUtils.getSimHash(TxtUtils.readFile(str)));
        TxtUtils.writeFile(answer,answerPath);
    }
}
