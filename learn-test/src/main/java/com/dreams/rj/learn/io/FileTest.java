package com.dreams.rj.learn.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {
    @Test
    public void test() {
        File file = new File("./");
        try {
            // 会返回规范路径，将路径中的 ./ ../ 解析为绝对路径
            String canonicalPath = file.getCanonicalPath();
            System.out.println(canonicalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
