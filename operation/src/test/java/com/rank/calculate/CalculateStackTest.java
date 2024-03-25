package com.rank.calculate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CalculateStackTest {

    private CalculateStack stack;

    @Before
    public void setUp() {
        stack = new CalculateStack();
    }

    @After
    public void tearDown() {
        stack = null;
    }

    @Test
    public void testAddFirstElemWithEmptyStack() {
        boolean result = stack.addFirstElem(5);
        assertTrue(result);
        assertEquals(1, stack.size());
    }

    @Test
    public void testAddFirstElemWithMultiplication() {
        stack.push("✖");
        stack.push(2);
        boolean result = stack.addFirstElem(3);
        assertTrue(result);
        assertEquals("3", stack.peek());
    }

    @Test
    public void testAddFirstElemWithDivision() {
        stack.push("➗");
        stack.push(8);
        boolean result = stack.addFirstElem(2);
        assertTrue(result);
        assertEquals("2", stack.peek());
    }

    @Test
    public void testAddFirstElemWithOtherOperation() {
        stack.push("➕");
        boolean result = stack.addFirstElem(7);
        assertTrue(result);
        assertEquals("7", stack.peek());
    }

    @Test
    public void testReturnsCalculateWithAddition() {
        // Arrange
        stack.push("3");
        stack.push("➕");
        stack.push("2");
        String result = stack.returnsCalculate();
        assertEquals("5", result);
    }

    @Test
    public void testReturnsCalculateWithSubtraction() {
        // Arrange
        stack.push("5");
        stack.push("➖");
        stack.push("2");
        String result = stack.returnsCalculate();
        assertEquals("3", result);
    }

}
