package com.previred.egs.domain;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GenerateJsonFile implements GenerateOutputFile {


    @Override
    public void generateFile(Object obj) {

        try {
            Gson gson = new Gson();
            String path = "../valores.json";
            String content = gson.toJson(obj);
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
