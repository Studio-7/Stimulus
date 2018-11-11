package com.example.stimulus.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    static public void copyFile(String inputPath, String fileName) {

        String outputPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/documents/";
        InputStream in = null;
        OutputStream out = null;
        try {

            File dir = new File (outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath);
            out = new FileOutputStream(outputPath + fileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();

        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }

}
