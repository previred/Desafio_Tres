package com;
import java.util.*;
import java.util.function.*;
import java.time.*;
import java.time.temporal.*;
import java.util.stream.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import com.previred.desafio.tres.uf.*;
import com.previred.desafio.tres.uf.vo.*;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;

public class Desafio3 {
  public static void main(String[] args) throws Exception {
	  
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    Valores valores = new Valores();
    Ufs ufs = valores.getRango();

    SalidaJson salidajson = new SalidaJson();
    salidajson.setInicio(df.format(ufs.getInicio()));
    salidajson.setFin(df.format(ufs.getFin()));

    Set < Uf > originalSetUf = ufs.getUfs();
    ArrayList < Uf > originalListUf = new ArrayList < Uf > (originalSetUf);

    List < UfLocal > listUfLocal = originalListUf.stream().map(s -> new UfLocal(df.format(s.getFecha()), s.getValor().toString())).collect(Collectors.toList());

    Forma forma = new Forma();
    List < UfLocal > listUfLocalNormalizado = forma.completar(salidajson.getInicio(), salidajson.getFin(), listUfLocal);

    Collections.sort(listUfLocalNormalizado, (o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));

    salidajson.setUFs(listUfLocalNormalizado);


    Gson gson = new Gson();
    String jsonString = gson.toJson(salidajson);
	
	String ficheroSalida = "valores.json";

    Files.write(Paths.get( ficheroSalida ), jsonString.getBytes());
	
	System.out.println("Fichero generado: " + ficheroSalida);

  }
}
class SalidaJson {
  private String inicio;
  private String fin;
  private List < UfLocal > UFs;

  public String getInicio() {
    return this.inicio;
  }
  public void setInicio(String inicio) {
    this.inicio = inicio;
  }

  public String getFin() {
    return this.fin;
  }
  public void setFin(String fin) {
    this.fin = fin;
  }

  public List < UfLocal > getUFs() {
    return this.UFs;
  }
  public void setUFs(List < UfLocal > UFs) {
    this.UFs = UFs;
  }

}
class UfLocal {
  private String fecha;
  private String dato;

  public UfLocal(String fecha, String dato) {
    this.fecha = fecha;
    this.dato = dato;
  }

  public String getFecha() {
    return this.fecha;
  }
  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getDato() {
    return this.dato;
  }
  public void setDato(String fecha) {
    this.dato = dato;
  }

}
class Forma {

  public List < UfLocal > completar(String strFechaInicio, String strFechaFin, List < UfLocal > listUfLocal) {

    List < UfLocal > listUfLocalFaltantes = new ArrayList < UfLocal > ();

    LocalDate f1 = LocalDate.parse(strFechaInicio);
    LocalDate f2 = LocalDate.parse(strFechaFin);

    long numOfDaysBetween = ChronoUnit.DAYS.between(f1, f2.plusDays(1));

    List < String > listRangoCompleto = Stream.iterate(f1, x -> x.plusDays(1))
      .limit(numOfDaysBetween).map(s -> s.toString())
      .collect(Collectors.toList());

    for (UfLocal uflocal: listUfLocal) {

      listRangoCompleto.remove(uflocal.getFecha());
    }

    for (String strFecha: listRangoCompleto) {

      DatosUf datosUf = DatosUf.getInstance();

      Date dateSearch = java.util.Date.from(LocalDate.parse(strFecha).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

      Uf ufResp = datosUf.getUf(dateSearch);

      listUfLocalFaltantes.add(new UfLocal(strFecha, String.valueOf(ufResp.getValor())));
    }

    listUfLocal.addAll(listUfLocalFaltantes);

    return listUfLocal;

  }

}