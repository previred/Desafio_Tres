package cl.jm.desafiotres.resultado;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.jm.desafiotres.resultado.utils.CreateCSV;

public class App 
{
    public static void main( String[] args ) throws ParseException, IOException
    {
    	Valores nRandomDate = new Valores();
        
        Ufs n = nRandomDate.getRango();
            	    	
    	Ufs n2 = new Ufs();
    	
    	String dateInicio="2010-01-01";
    	String dateFin="2011-01-01";
    	
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateInicio);  
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(dateFin);  
    	
        n2.setInicio(date1);
    	n2.setFin(date2);        
    	
    	CreateCSV createCSV = new CreateCSV();
    	
    	createCSV.Create(n);
        
        /*CreateXml createXml = new CreateXml();
        
        createXml.Create(ufs);*/
    	
    	System.out.println("Fin de la ejecución - El archivo valores.csv fue creado.");
    }
}
