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
                //获取文件输入流
                InputStream fi = Files.newInputStream(zip.toPath());
                //构建缓冲流(Apache 建议)
                InputStream bi = new BufferedInputStream(fi);
        ) {
            ArchiveStreamFactory factory = new ArchiveStreamFactory();
            //从缓冲输入流中获取归档文件输入流
            ArchiveInputStream archiveInputStream = factory.createArchiveInputStream(bi);
            //加压路径(默认解压到压缩文件同级的同名目录下, 自动创建)
            File targetDir = new File(zip.getParent(), zip.getName().substring(0, zip.getName().length() - 4));
            //开始解压
            boolean ok = decompress(archiveInputStream, targetDir);
            if (!ok) {
                deleteDir(targetDir);
            }
            return ok;
        } catch (IOException e) {
            logger.error("can not get InputStream from file", e);
            logger.error("can not get InputStream from file, {}", zip.getAbsoluteFile());
        } catch (ArchiveException e) {
            logger.error("can not create ArchiveInputStream ", e);
        }
        return false;
    }

    /**
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

        try (
                //获取文件输入流
                InputStream fi = Files.newInputStream(zip.toPath());
                //构建缓冲流(Apache 建议)
                InputStream bi = new BufferedInputStream(fi);
        ) {
            ArchiveStreamFactory factory = new ArchiveStreamFactory();
            //从缓冲输入流中获取归档文件输入流
            ArchiveInputStream archiveInputStream = factory.createArchiveInputStream(bi);
            //开始解压
            boolean ok = decompress(archiveInputStream, targetDir);
            if (!ok) {
                deleteDir(targetDir);
            }
            return ok;
        } catch (IOException e) {
            logger.error("can not get InputStream from file", e);
            logger.error("can not get InputStream from file, {}", zip.getAbsoluteFile());
        } catch (ArchiveException e) {
            logger.error("can not create ArchiveInputStream ", e);
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
            //获取压缩文件中的每一个文件
            while ((entry = i.getNextEntry()) != null) {
                //验证压缩文件中的文件是否可读
                if (!i.canReadEntryData(entry)) {
                    logger.error("can not read entry of zip file, entry : {}, entry.size {}", entry.getName(), entry.getSize());
                    continue;
                }
                //获取输出文件的全路径(绝对路径)
                String name = fileName(targetDir, entry);
                File f = new File(name);
                //如果压缩文件中当前文件是一个目录, 则检查文件是否是目录,如果不是则创建,如果创建失败则抛出异常
                if (entry.isDirectory()) {
                    if (!f.isDirectory() && !f.mkdirs()) {
                        throw new IOException("failed to create directory " + f);
                    }
                } else {
                    //如果是文件, 检查文件所在目录是否存在,如果不存在则创建
                    File parent = f.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("failed to create directory " + parent);
                    }
                    //将文件写入到磁盘中
                    try (OutputStream o = Files.newOutputStream(f.toPath())) {
                        IOUtils.copy(i, o);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("decompress failed", e);
        }
        return false;
    }

    /**
     * 获取输出文件路径
     *
     * @param targetDir 目标路径
     * @param entry
     * @return
     * @throws IOException
     */
    private static String fileName(File targetDir, ArchiveEntry entry) throws IOException {
        String name = entry.getName();
        File file = new File(targetDir, name);
        if (entry.isDirectory()) {
            file.mkdirs();
        } else if (!file.getParentFile().exists()) {
            boolean ok = file.getParentFile().mkdirs();
            if (ok) {
                return null;
            }
        }
        return file.getCanonicalPath();
    }

    /**
     * 递归删除目录
     *
     * @param folder
     */
    private static void deleteDir(File folder) {
        if (folder != null && folder.exists()) {
            //如果是文件或空目录, 直接删除
            if (folder.isFile() || (folder.listFiles() == null && folder.listFiles().length == 0)) {
                folder.delete();
                return;
            }
            //循环处理目录中文件或子目录
            for (File file : folder.listFiles()) {
                //目录
                if (file.isDirectory()) {
                    deleteDir(file);
                }
                //文件
                else {
                    file.delete();
                }

            }

            folder.delete();
        }
    }
}
