package com.rank.calculate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TitleAnswerTest {

    @Test
    public void testProblemDescriptionGetterAndSetter() {
        final TitleAnswer titleAnswerUnderTest = new TitleAnswer("problemDescription", "answer");
        final String problemDescription = "problemDescription";
        titleAnswerUnderTest.setProblemDescription(problemDescription);
        assertEquals(problemDescription, titleAnswerUnderTest.getProblemDescription());
    }

    @Test
    public void testAnswerGetterAndSetter() {
        final TitleAnswer titleAnswerUnderTest = new TitleAnswer("problemDescription", "answer");
        final String answer = "answer";
        titleAnswerUnderTest.setAnswer(answer);
        assertEquals(answer, titleAnswerUnderTest.getAnswer());
    }

}
