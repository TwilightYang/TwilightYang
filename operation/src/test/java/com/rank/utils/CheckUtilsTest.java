package com.rank.utils;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class CheckUtilsTest {

    @Test
    public void testReadAnswersFromFile_ValidFile() throws IOException {
        // 准备测试文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/resources/answers.txt"))) {
            writer.write("1:Answer1\n");
            writer.write("2:Answer2\n");
            writer.write("3:Answer3\n");
        }

        // 执行待测方法
        List<String> answers = CheckUtils.readAnswersFromFile("src/test/resources/answers.txt");

        // 验证结果
        assertNotNull(String.valueOf(answers), "Answers list should not be null.");
        assertEquals(String.valueOf(3), answers.size(), 3);
        assertEquals("Answer1", answers.get(0), "Answer1");
        assertEquals("Answer2", answers.get(1), "Answer2");
        assertEquals("Answer3", answers.get(2), "Answer3");
    }

    @Test
    public void testReadAnswersFromFile_InvalidFile() {
        // 使用一个不存在的文件路径
        String invalidFilePath = "fileThatDoesNotExist.txt";

        // 执行待测方法
        List<String> answers = CheckUtils.readAnswersFromFile(invalidFilePath);

        // 验证结果，期望抛出 IOException
        assertNotNull(String.valueOf(answers), "Answers list should not be null even if file does not exist.");
        assertTrue(answers.isEmpty());
    }

    @Test
    public void testGradeWrite() {
        CheckUtils.gradeWrite("filePath", 0, 0, new int[]{0}, new int[]{0});
    }
}
