package com.previred.controllers;

import com.previred.services.DesafioTresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DesafioTresController {
    @Autowired
    private DesafioTresService desafioTresService;

    @RequestMapping("/download-json")
    public StreamingResponseBody downloadJson(HttpServletResponse response) {
        return null;
    }

    @RequestMapping("/healthCheck")
    public String healthCheck() {
        return "I'm here!";
    }
}
