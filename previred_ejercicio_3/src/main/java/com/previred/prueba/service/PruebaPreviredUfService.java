package com.previred.prueba.service;

import com.previred.prueba.request.DatosEntrada;
import com.previred.prueba.response.DatosSalida;


public interface PruebaPreviredUfService {

    DatosSalida obtieneDetalleFechasUf(DatosEntrada datosEntrada) throws Exception;


}
