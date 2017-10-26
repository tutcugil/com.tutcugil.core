package com.tutcugil.core.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class FileOperations {
    public static void saveContent(String path, String content) {
        if (!create(path))
            return;

        File file = FileOperations.get(path);
        if  (file == null)
            return;

        FileOperations.write(file, content);
    }

    public static boolean delete(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists())
                return true;

            return file.delete();
        } catch (Exception ex) {
            Logger.error(ex);

            return false;
        }
    }

    public static boolean create(String path) {
        try {
            File file = new File(path);
            return file.exists() || file.createNewFile();
        } catch (Exception ex) {
            return false;
        }
    }

    public static File get(String path) {
        try {
            return new File(path);
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean write(File file, String content) {
        BufferedWriter buf;
        FileWriter fWriter;
        try {
            fWriter = new FileWriter(file, true);
            buf = new BufferedWriter(fWriter);
            buf.append(content);
            buf.newLine();
            buf.close();
            return true;
        } catch (Exception e) {
            Logger.error(e);
            return false;
        }
    }

    public static String getContent(String path) {
        File file;
        BufferedReader in = null;
        try {
            file = new File(path);
            if (!file.exists())
                return null;

            in = new BufferedReader(new FileReader(file));
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (Exception e) {
            Logger.error(e);
            return null;
        } finally {
            try {
                if (in != null)
                    in.close();

            } catch (Exception e) {
                Logger.error(e);
            }
        }
    }

    public static boolean deleteDirectory(File file) {
        if (!file.exists())
            return true;

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                if (f.isDirectory()) {
                    if (!deleteDirectory(f))
                        return false;
                    continue;
                }

                if (!f.delete())
                    return false;
            }
        }

        return file.delete();
    }
}
