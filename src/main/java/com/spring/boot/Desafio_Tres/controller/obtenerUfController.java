package com.spring.boot.Desafio_Tres.controller;

import com.spring.boot.Desafio_Tres.service.ValoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obtenerJson")
public class obtenerUfController {

    @Autowired
    private ValoresService valoresService;

    /**
     * Realiza la generación del archivo Json con sus valores UF correspondiente.
     * @requestMethod GET
     * @return Mensaje que entrega si fue exitoso la creación del archivo.
     */
    @RequestMapping(value = "/uf", method = RequestMethod.GET)
    public String generarJson(){
        return valoresService.obtenerUf();
    }

}
