package com.desafio3.valoresuf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathWrite {

    @Value("${write.file_name}")
    private String fileName;

    public static final StringBuilder stringPath = new StringBuilder();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
