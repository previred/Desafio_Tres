package com.example.previredTest.controller;

import com.example.previredTest.dto.PeriodWithUfDTO;
import com.example.previredTest.service.PreviredTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PreviredTestController {

    @Autowired
    private PreviredTestService previredTestService;

    @GetMapping("/Valores")
    public ResponseEntity<PeriodWithUfDTO> consultarUf() throws IOException {
        PeriodWithUfDTO periodWithUfDTO = previredTestService.getPeriodWithUfData();
        return new ResponseEntity(periodWithUfDTO, HttpStatus.OK);
    }
}
