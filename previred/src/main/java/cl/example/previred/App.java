package cl.example.previred;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.example.previred.utils.FileUtil;
import cl.example.previred.utils.Util;

public class App 
{
    public static void main( String[] args )
    {
      complementoValores();
      System.out.println("Archivo generado con éxito.");
    }
    
    
    private static void complementoValores() {
    	
    	DatosUf datosUf = DatosUf.getInstance();
    	
    	Valores valores = new Valores();
        Ufs rangoUFS = valores.getRango();
        Set<Uf> ufs = rangoUFS.getUfs();
        
        //Recorrer Fechas
        LocalDate start = rangoUFS.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
        LocalDate end = rangoUFS.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 

        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) { 

        	Date fecha = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        	
        	if(!Util.existeFecha(ufs, fecha))
        		ufs.add(datosUf.getUf(fecha));
        	
        }
        
        //Ordenando UF Descendentemente
        List<Uf> ufsOrdered = ufs.stream().sorted(
        						Comparator.comparing(Uf::getFecha).reversed()
        					   ).collect(Collectors.toList());
        
        //GenerandoArchivo
        FileUtil.toCSV(ufsOrdered, rangoUFS);
    }
    
}
