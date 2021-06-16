package desafio.dao;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import desafio.enumerators.ConstantesStr;
import desafio.interfaces.IDesafio;
import desafio.interfaces.ISalidaJSON;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SalidaJSON implements ISalidaJSON {

    private IDesafio desafio;

    private final Logger logger = Logger.getLogger(SalidaJSON.class.getName());

    public SalidaJSON(IDesafio desafio) {
        this.desafio = desafio;
    }

    public String createJsonFile() {
        String result = "";
        try (FileWriter file = new FileWriter(ConstantesStr.URL_FILE.toString())) {
            Ufs rango = desafio.getRango();
            String order = ConstantesStr.ORDER_FECHA.toString();
            List<Uf> listUfs = desafio.listUfs(rango, order);
            result = desafio.jsonFromListUfs(listUfs);
            file.write(result);
            file.flush();
        } catch (IOException e) {
            this.logger.severe(e.getMessage());
        }
        return result;
    }
}
