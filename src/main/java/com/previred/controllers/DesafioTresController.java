package com.previred.controllers;

import com.previred.services.DesafioTresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
public class DesafioTresController {
    @Autowired
    private DesafioTresService desafioTresService;

    @RequestMapping(path = {"/", "/download-json"})
    public StreamingResponseBody downloadJson(HttpServletResponse response) {
        byte[] jsonBytes = desafioTresService.getJsonBytes();
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=valores.json");
        InputStream inputStream = new ByteArrayInputStream(jsonBytes);
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }

    @RequestMapping("/healthCheck")
    public String healthCheck() {
        return "I'm here!";
    }
}
