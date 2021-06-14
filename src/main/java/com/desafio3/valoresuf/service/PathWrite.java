package com.desafio3.valoresuf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PathWrite {

    @Value("${write.file_name}")
    private String fileName;

    public static StringBuilder stringPath = new StringBuilder();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
