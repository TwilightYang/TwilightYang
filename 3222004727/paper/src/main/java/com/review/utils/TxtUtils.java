package com.review.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TxtUtils {
    /**
     * 读取文件
     * @param filePath 目标文件路径
     * @return 文件内容
     */
    public static String readFile(String filePath) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null){
                str.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 写入文件
     * @param fileElem 写入的内容
     * @param filePath 写入的文件路径
     */
    public static void writeFile(double fileElem, String filePath) {
        String str = Double.toString(fileElem);
        try(FileWriter fw = new FileWriter(filePath, true)){
            fw.write(str,0,(str.length() > 3 ? 4 : str.length()));
            fw.write("\r\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
