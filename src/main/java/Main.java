import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Valores valores = new Valores();

            Ufs rango = valores.getRango();
            List<Uf> ufs = DatosUf.getInstance().getUfs(rango.getInicio(), rango.getFin());
            ufs.sort(new Comparator<Uf>() {
                @Override
                public int compare(Uf o1, Uf o2) {
                    return o1.getFecha().after(o2.getFecha()) ? -1 : 1;
                }
            });

            ResponseJSON json = new ResponseJSON(rango.getInicio(), rango.getFin(), new ArrayList<>());
            for (int i = 0; i < ufs.size(); i++) {
                json.getUFs().add(new ResponseUf(ufs.get(i).getFecha(), ufs.get(i).getValor().toString()));
            }

            Path file = Paths.get("resultado").toAbsolutePath().normalize();
            Files.createDirectories(file);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            Path target = file.resolve("response.json");
            FileOutputStream outputStream = new FileOutputStream(target.toString());
            outputStream.write(gson.toJson(json).getBytes(StandardCharsets.UTF_8));
            outputStream.close();

            System.out.println("Finalizado !!!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
