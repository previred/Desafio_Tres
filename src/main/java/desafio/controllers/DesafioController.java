package desafio.controllers;

import desafio.dao.Desafio;
import desafio.dao.SalidaJSON;
import desafio.enumerators.ConstantesStr;
import desafio.enumerators.ConstantesMsg;
import desafio.interfaces.IDesafio;
import desafio.interfaces.ISalidaJSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping(path="/downloadJson")
    public ResponseEntity<byte[]> downloadJsonFile() throws Exception {
        this.logger.info(ConstantesMsg.ACCESS_GRANTED.toString() + ConstantesStr.DOWNLOAD_URL.toString());
        Path path = Paths.get(ConstantesStr.URL_FILE.toString());
        byte[] isr = Files.readAllBytes(path);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + ConstantesStr.URL_FILE.toString());
        return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
    }
}
