package desafio.controllers;

import desafio.dao.Desafio;
import desafio.dao.SalidaJSON;
import desafio.enumerators.ConstantesStr;
import desafio.enumerators.ConstantesMsg;
import desafio.interfaces.IDesafio;
import desafio.interfaces.ISalidaJSON;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class DesafioController {

    private IDesafio desafio;
    private ISalidaJSON salidaJSON;
    private final Logger logger = Logger.getLogger(DesafioController.class.getName());

    public DesafioController(IDesafio desafio, ISalidaJSON salidaJSON) {
        this.desafio = desafio;
        this.salidaJSON = salidaJSON;
    }

    public DesafioController() {
        this.desafio = new Desafio();
        this.salidaJSON = new SalidaJSON(desafio);
    }

    @GetMapping(path="/valoresJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createJsonFile(){
        this.logger.info(ConstantesMsg.ACCESS_GRANTED.toString() + ConstantesStr.VALUES_URL.toString());
        return this.salidaJSON.createJsonFile();
    }
}
