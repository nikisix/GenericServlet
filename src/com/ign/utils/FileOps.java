package com.ign.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class FileOps {
    public static String ReadFileAsString(String filePath) {
        String FileContents = null;

        File file = new File(filePath);

        if (file.exists()) {
            long fileSize = file.length();

            if (fileSize < Integer.MAX_VALUE) {
                int intFileSize = (int) fileSize;

                byte[] buffer = new byte[intFileSize];

                BufferedInputStream f = null;
                try {
                    f = new BufferedInputStream(new FileInputStream(filePath));
                    f.read(buffer);

                    FileContents = new String(buffer);
                } catch (Exception ignore) {
                } finally {
                    if (f != null) {
                        try {
                            f.close();
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }

        return FileContents;
    }


    public static boolean WriteFileFromString(String filePath, String Contents) {
        boolean Done = false;

        File file = null;
        BufferedOutputStream stream = null;

        try {
            file = new File(filePath);
            stream = new BufferedOutputStream(new FileOutputStream(file));

            if (Contents != null && Contents.length() > 0) {
                stream.write(Contents.getBytes());
            }

            Done = true;
        } catch (Exception ignore) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ignored) {
                }
            }
        }

        return Done;
    }

    public static String ConvertSepChar(String path) {
        String p = path;

        if (File.separatorChar == '/') {
            p = p.replace('\\', File.separatorChar);
        } else {
            p = p.replace('/', File.separatorChar);
        }

        return p;
    }
}