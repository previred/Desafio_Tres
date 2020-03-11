package desafio3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio3.entity.Rango;
import desafio3.service.DesafioService;

@RestController
@RequestMapping("/api/v1")
public class DesafioController {
	
	private Logger logger = LogManager.getLogger(DesafioController.class);
	
	@Autowired
	private DesafioService desafioService;

    @GetMapping("/rango")
    public Rango getRangoUFs() throws Exception {
    	Rango rango = new Rango();
    	logger.info("Inicio de cálculo de rango");
    	try {
    		rango = desafioService.getRango();
    		logger.info("Generacion de rango finalizada");
    		
    	} catch (Exception e) {
    		logger.error("DesafioController - Error durante el cálculo del rango");
			throw new Exception("Error durante el cálculo del rango", e);
    	}
    	
        return rango;
    }

}
