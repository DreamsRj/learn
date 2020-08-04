package com.dreams.rj.learn.zip;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TestZip {
    public static void main(String[] args) {
//        try {
//            test2();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ArchiveException e) {
//            e.printStackTrace();
//        }
//        String a = "abc.zip";
//        System.out.println(a.substring(0, a.length() - 4));

        try {
            unZip(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TestZip() throws IOException {
    }

    public static void unZip(File zip) throws IOException {
        File file = new File("/home/dreams/test/test.zip");

        ZipFile zipFile = new ZipFile(file, "GBK");
        Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry entry = entries.nextElement();
            String name = entry.getName();
            InputStream in = zipFile.getInputStream(entry);
            System.out.println(name);
            if (entry.isDirectory()) {
                continue;
            }

        }


    }

    public static void test() {
        CompressorStreamFactory factory = new CompressorStreamFactory();
    }

    public static void test2() throws IOException, ArchiveException {
        File targetDir = new File("/dreamsrj/extdir/abc");
        try (
                InputStream fi = Files.newInputStream(Paths.get("//home/dreams/test/test.zip"));
                InputStream bi = new BufferedInputStream(fi);
        ) {

            ArchiveStreamFactory factory = new ArchiveStreamFactory();
            ArchiveInputStream archiveInputStream = factory.createArchiveInputStream(bi);

//            try (ArchiveInputStream i = archiveInputStream) {
//                ArchiveEntry entry = null;
//                while ((entry = i.getNextEntry()) != null) {
//                    if (!i.canReadEntryData(entry)) {
//                        // log something?
//                        continue;
//                    }
//                    String name = fileName(targetDir, entry);
//                    File f = new File(name);
//                    if (entry.isDirectory()) {
//                        if (!f.isDirectory() && !f.mkdirs()) {
//                            throw new IOException("failed to create directory " + f);
//                        }
//                    } else {
//                        File parent = f.getParentFile();
//                        if (!parent.isDirectory() && !parent.mkdirs()) {
//                            throw new IOException("failed to create directory " + parent);
//                        }
//                        try (OutputStream o = Files.newOutputStream(f.toPath())) {
//                            IOUtils.copy(i, o);
//                        }
//                    }
//                }
//            }
        }


    }

    public static void test3() throws IOException {
        File targetDir = new File("/dreamsrj/extdir/abc");

        try (
                InputStream fi = Files.newInputStream(Paths.get("my.tar.gz"));
                InputStream bi = new BufferedInputStream(fi);
                InputStream bbi = new ZipArchiveInputStream(fi);

                InputStream gzi = new GzipCompressorInputStream(bi);
                ArchiveInputStream ain = new TarArchiveInputStream(gzi)) {

            try (ArchiveInputStream i = ain) {
                ArchiveEntry entry = null;
                while ((entry = i.getNextEntry()) != null) {
                    if (!i.canReadEntryData(entry)) {
                        // log something?
                        continue;
                    }
                    String name = fileName(targetDir, entry);
                    File f = new File(name);
                    if (entry.isDirectory()) {
                        if (!f.isDirectory() && !f.mkdirs()) {
                            throw new IOException("failed to create directory " + f);
                        }
                    } else {
                        File parent = f.getParentFile();
                        if (!parent.isDirectory() && !parent.mkdirs()) {
                            throw new IOException("failed to create directory " + parent);
                        }
                        try (OutputStream o = Files.newOutputStream(f.toPath())) {
                            IOUtils.copy(i, o);
                        }
                    }
                }
            }
        }

    }

    private static String fileName(File targetDir, ArchiveEntry entry) throws IOException {
        String name = entry.getName();
        File file = new File(targetDir, name);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        return file.getCanonicalPath();
    }

    public List<File> getAllFiles(File folder, List<File> list) {
        if (folder == null || !folder.exists()) {
            return null;
        }
        if (list == null) {
            list = new ArrayList<>();
        }

        File[] files = folder.listFiles();
        return null;
    }
}
