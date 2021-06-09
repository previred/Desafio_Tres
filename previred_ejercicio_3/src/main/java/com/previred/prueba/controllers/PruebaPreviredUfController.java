package com.previred.prueba.controllers;

import com.previred.prueba.request.DatosEntrada;
import com.previred.prueba.response.DatosSalida;
import com.previred.prueba.response.Uf;
import com.previred.prueba.service.DatosUf;
import com.previred.prueba.service.PruebaPreviredUfService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@EnableSpringDataWebSupport
public class PruebaPreviredUfController {

    private static final Logger log = Logger.getLogger(PruebaPreviredUfController.class);

    @Autowired
    PruebaPreviredUfService pruebaPreviredFechasService;

    @RequestMapping(value = "/fecha", method = RequestMethod.GET)
    public ResponseEntity<Object> obtieneFecha() throws Exception {
        Date date = new Date();
        return new ResponseEntity<Object>(date, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(value = "/uf/detalle", method = RequestMethod.PUT)
    public ResponseEntity<Object> obtieneDetalleFechas(@RequestBody DatosEntrada datosEntrada) throws Exception {
        Object obj = null;
        try{
            if(datosEntrada == null){
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }else{

                DatosSalida datosSalida = new DatosSalida();

                datosSalida = pruebaPreviredFechasService.obtieneDetalleFechasUf(datosEntrada);
                 obj = datosSalida;
                return new ResponseEntity<Object>(obj, HttpStatus.OK);
            }
        }catch (Exception e){
             obj = e.getCause();
            return new ResponseEntity<Object>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

}