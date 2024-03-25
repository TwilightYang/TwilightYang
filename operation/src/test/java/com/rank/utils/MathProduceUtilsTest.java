package com.rank.utils;

import com.rank.calculate.TitleAnswer;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MathProduceUtilsTest {
    @Test
    public void testProduceMaths() {
        int n = 10; // Number of problems
        double r = 5.0; // Range of numbers

        List<TitleAnswer> result = MathProduceUtils.produceMaths(n, r);

        assertNotNull(result);
        assertEquals(n, result.size());
    }
    @Test
    public void testMain() {
        MathProduceUtils.main(new String[]{"args"});
    }
}
