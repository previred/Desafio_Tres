package com.previred.desafio.tres;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.dto.UfDto;
import com.previred.desafio.tres.dto.UfsDto;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.tres.utils.*;

public class App 
{
	
	public static Date restarDiasAFecha(Date fecha, int dias){
	      if (dias==0) return fecha;
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); 
	      calendar.add(Calendar.DAY_OF_YEAR, -dias);  
	      return calendar.getTime(); 
	}
	

	public static void main( String[] args ) throws Exception
    {
		//Consumir la función getRango de la clase com.previred.desafio.tres.uf.Valores
    	Valores valor = new Valores();
    	Ufs ufs = valor.getRango();
    	// DTO para la salida
    	UfsDto ufsDto = new UfsDto();
		SimpleDateFormat sdfDate = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		ufsDto.setInicio(sdfDate.format(ufs.getInicio()));
		ufsDto.setFin(sdfDate.format(ufs.getFin()));
						
    	List<UfDto> listUfDto = new ArrayList<UfDto>();
    	
    	///La clase com.previred.desafio.tres.uf.DatosUf este es un singleton que contiene 2 métodos
    	DatosUf generaValorUf = DatosUf.getInstance();
    	List<Uf> listaUf=generaValorUf.getUfs(ufs.getInicio(), ufs.getFin());
    	
    	System.out.println("Old "+ufs.getUfs().size()+ " New "+ listaUf.size());
    	
    	LocalDate start = LocalDate.parse(ufsDto.getInicio()),
    	          end   = LocalDate.parse(ufsDto.getFin());
    	LocalDate next = start;
    	
    	Date fechaInicio=ufs.getFin();
		int maxCantidad= Constantes.TOTAL_CANTIDAD_VALORES;
    	int contadorFalta = Constantes.INICIO_CONTADOR;
		int contadorTotal = Constantes.INICIO_CONTADOR;
		int cantidadFalta= Constantes.TOTAL_CANTIDAD_VALORES-ufs.getUfs().size();
    	while ((next = next.plusDays(Constantes.SUMA_UNO)).isBefore(end.plusDays(Constantes.SUMA_UNO))) {    		
    		UfDto ufDto = new UfDto();
			Date copiaFechaInicio= fechaInicio;
			Double valorTmp = ufs.getUfs().stream().filter(ufStream->ufStream.getFecha().equals(copiaFechaInicio)).map(ufStream->ufStream.getValor()).findFirst().orElse(0.00);
			DecimalFormat formato = new DecimalFormat(Constantes.FORMATO_MONEDA);
			if (valorTmp==Constantes.SIN_VALOR.doubleValue() & contadorFalta<cantidadFalta){
    			//Para complementar los valores de UF se pueden utilizar los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf.
    			valorTmp=generaValorUf.getUf(fechaInicio).getValor();
    			ufDto.setValor(formato.format(valorTmp).toString());
    			ufDto.setFecha(sdfDate.format(fechaInicio));
    			listUfDto.add(ufDto); 
    			contadorFalta++;
    			contadorTotal++;
    		}
    		else if(valorTmp>Constantes.SIN_VALOR.doubleValue() & contadorTotal<maxCantidad){    			
    			ufDto.setValor(formato.format(valorTmp).toString());
    			ufDto.setFecha(sdfDate.format(fechaInicio));
    			listUfDto.add(ufDto); 
    			contadorTotal++;
    		}						
//			System.out.println("fecha: "+ufDto.getFecha()+" Valor : "+ ufDto.getDato());			   		
			fechaInicio=restarDiasAFecha(fechaInicio,Constantes.SUMA_UNO);
			fechaInicio=new SimpleDateFormat(Constantes.FORMATO_FECHA).parse(sdfDate.format(fechaInicio));    		
    	}
    	ufsDto.setUFs(listUfDto);
    	System.out.println("Total Registros de Salida: "+contadorTotal);
    	System.out.println("Total Registros Faltantes: "+contadorFalta);    	
        
    	try { 
            // get Oraganisation object as a json string 
        	ObjectMapper Obj = new ObjectMapper(); 
            String jsonStr = Obj.writeValueAsString(ufsDto); 
  
            Obj.writerWithDefaultPrettyPrinter()
            .writeValue(new File(Constantes.RUTA_ARCHIVO_SALIDA), ufsDto);
            
            // Displaying JSON String 
            System.out.println("SALIDA JSON: \n" + jsonStr);
        } 
  
        catch (IOException e) { 
            e.printStackTrace(); 
        }        
    }
}
