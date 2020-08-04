package com.dreams.rj.learn;

import static org.junit.Assert.assertTrue;

import com.dreams.rj.learn.zip.ZipTools;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testZipTool() {
//        String filepath = "/home/dreams/test/folder.zip";
        String filepath = "/home/dreams/test/nofolder.zip";
        boolean b = ZipTools.unZip(new File(filepath));
        Assert.assertTrue(b);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testZipTool2() {
//        String filepath = "/home/dreams/test/folder.zip";
        String filepath = "/home/dreams/test/nofolder.zip";
        File target = new File("/home/dreams/test/test");
        boolean b = ZipTools.unZip(new File(filepath), target);
        Assert.assertTrue(b);
    }
}
