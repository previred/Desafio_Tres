package desafio.controllers;

import desafio.dao.Desafio;
import desafio.dao.SalidaJSON;
import desafio.interfaces.IDesafio;
import desafio.interfaces.ISalidaJSON;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesafioController {

    private IDesafio desafio;
    private ISalidaJSON salidaJSON;

    public DesafioController(IDesafio desafio, ISalidaJSON salidaJSON) {
        this.desafio = desafio;
        this.salidaJSON = salidaJSON;
    }

    public DesafioController() {
        this.desafio = new Desafio();
        this.salidaJSON = new SalidaJSON(desafio);
    }

    @RequestMapping(path="/valoresJson", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public String createJsonFile(){
        String result = this.salidaJSON.createJsonFile();
        return result;
    }
}
