package desafio.dao;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import desafio.enumerators.ConstantesStr;
import desafio.interfaces.IDesafio;
import desafio.interfaces.ISalidaJSON;
import java.io.*;
import java.util.List;

public class SalidaJSON implements ISalidaJSON {

    private IDesafio desafio;

    public SalidaJSON(IDesafio desafio) {
        this.desafio = desafio;
    }

    public String createJsonFile() {
        String result = "";
        try (FileWriter file = new FileWriter("valores.json")) {
            Ufs rango = desafio.getRango();
            String order = ConstantesStr.ORDER_FECHA.toString();
            List<Uf> listUfs = desafio.listUfs(rango, order);
            result = desafio.jsonFromListUfs(listUfs);
            file.write(result);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
