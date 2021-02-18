package com.markoapps.sweetchex1.utils;

import net.lingala.zip4j.ZipFile;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
    static public void saveFileToDisk(InputStream inputStream, String pathname) throws IOException {
        File file = new File(pathname);
        try (OutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[4 * 1024]; // or other buffer size
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }

            output.flush();
        }
        finally {
            inputStream.close();
        }
    }

    static public void deleteFilesFromDirectory(String dir) {
        deleteRecursive(new File(dir));
    }

    static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    static public void extractZipFile(String file, String destinatinDirectory) throws IOException {
        new ZipFile(file).extractAll(destinatinDirectory);
    }

    public static String getFirstFile(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles(new FileFilter() {
            boolean first = true;
            public boolean accept(final File pathname) {
                if (first) {
                    first = false;
                    return true;
                }
                return false;
            }
        });
        if(files.length > 0) {
            return files[0].getAbsolutePath();
        } else {
            return null;
        }
    }

}
