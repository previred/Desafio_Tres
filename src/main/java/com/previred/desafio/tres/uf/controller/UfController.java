package com.previred.desafio.tres.uf.controller;

import com.previred.desafio.tres.uf.dto.ListaUfsOutputJson;
import com.previred.desafio.tres.uf.service.UfService;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/uf")
public class UfController {

    private static final Logger logger = getLogger(UfController.class);

    private UfService ufService;

    @Autowired
    public UfController(UfService ufService){
        this.ufService = ufService;
    }

    @GetMapping("/rango")
    public Ufs getUfsList() {

        logger.info("entrando a getUfsList...");
        try {
            return ufService.getRango();
        } catch (IOException e) {
            logger.error("IOException en Controller; e.getMessage() == "+e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/completo")
    public ListaUfsOutputJson getRangoCompleto() {

        logger.info("entrando a getRangoCompleto...");
        try {
            return ufService.getRangoCompleto();
        } catch (IOException e) {
            logger.error("IOException en Controller; e.getMessage() == "+e.getMessage());
        }
        return null;
    }




}
