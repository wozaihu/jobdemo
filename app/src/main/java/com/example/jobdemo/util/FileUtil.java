package com.example.jobdemo.util;

import java.io.File;

/**
 * @Author Administrator
 * @Date 2021/11/18 10:43
 */
public class FileUtil {
    /**
     * @param file 调此方法前确保具备读写权限，文件就直接删除，文件夹删除本身和内部所有
     */
    public static void deleteFile(File file) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File[] childFile = file.listFiles();
                    if (childFile != null && childFile.length != 0) {
                        for (File f : childFile) {
                            deleteFile(f);
                        }
                    }
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.showD("TestFunctionAndPrint打印", "deleteFile删除文件异常-------" + e.getMessage());
        }
    }


    /**
     * @param file 如果是文件直接删除，如果是文件夹删除目录下所有文件和文件夹，但保留本文件夹
     */
    public static void deleteFileReserveSelf(File file) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File[] childFile = file.listFiles();
                    if (childFile != null && childFile.length != 0) {
                        for (File f : childFile) {
                            deleteFile(f);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.showD("TestFunctionAndPrint打印", "deleteFileReserveSelf删除文件异常-------" + e.getMessage());
        }
    }
}
