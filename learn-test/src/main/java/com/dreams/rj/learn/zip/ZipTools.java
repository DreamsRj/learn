package com.dreams.rj.learn.zip;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

public class ZipTools {

    private static final Logger logger = LoggerFactory.getLogger(ZipTools.class);

    /**
     * 解压文件， 将文件解压到以文件名未目录名的路径中。 <br>
     * 如果文件名为： /a/b/c.zip <br>
     * 文件将解压到： /a/b/c 目录下， 目录将自动创建 <br>
     *
     * @param zip
     * @return
     */
    public static boolean unZip(File zip) {
        if (zip == null || !zip.exists()) {
            logger.error("zip file is null or not exist!");
            return false;
        }
        try (
                InputStream fi = Files.newInputStream(zip.toPath());
                InputStream bi = new BufferedInputStream(fi);
        ) {
            ArchiveStreamFactory factory = new ArchiveStreamFactory();
            ArchiveInputStream archiveInputStream = factory.createArchiveInputStream(bi);

            File targetDir = new File(zip.getParent(), zip.getName().substring(0, zip.getName().length() - 4));
            decompress(archiveInputStream, targetDir);
        } catch (IOException e) {
            logger.error("can not get InputStream from file", e);
            logger.error("can not get InputStream from file, {}", zip.getAbsoluteFile());
        } catch (ArchiveException e) {
            logger.error("can not create ArchiveInputStream ", e);
        }

        return false;
    }

    /**
     *
     * @param zip
     * @param targetDir
     * @return
     */
    public static boolean unZip(File zip, File targetDir) {
        if (targetDir == null || !targetDir.exists()) {
            logger.error("target directory is null or not exist!");
            return false;
        }
        if (zip == null || !zip.exists()) {
            logger.error("zip file is null or not exist!");
            return false;
        }
        return false;
    }

    private static boolean decompress(ArchiveInputStream archiveInputStream, File targetDir) {
        if (archiveInputStream == null) {
            logger.error("can not read data from null stream : archiveInputStream ");
            return false;
        }

        if (targetDir == null) {
            logger.error("target directory not specify");
            return false;
        }

        try (ArchiveInputStream i = archiveInputStream) {
            ArchiveEntry entry = null;
            while ((entry = i.getNextEntry()) != null) {
                if (!i.canReadEntryData(entry)) {
                    logger.error("can not read entry of zip file, entry : {}, entry.size {}", entry.getName(), entry.getSize());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static String fileName(File targetDir, ArchiveEntry entry) throws IOException {
        String name = entry.getName();

        if(entry.isDirectory()){

        }
        File file = new File(targetDir, name);
        if (!file.getParentFile().exists()) {
            boolean ok = file.mkdirs();
            if (ok) {
                return null;
            }
        }
        return file.getCanonicalPath();
    }
}
