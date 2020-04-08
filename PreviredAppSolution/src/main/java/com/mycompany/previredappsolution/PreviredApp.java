/*
 * @author: Carolina Tapia Salinas
 * @email: cartapia@gmail.com
 * @Description: Postulación Previred
 */
package com.mycompany.previredappsolution;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PreviredApp {
    public PreviredApp(){
        
    Valores val = new Valores();
    Ufs rango = val.getRango();
    DatosUf ufData = DatosUf.getInstance(); 
    int dias =0;
    
    Set<Uf> ufSet = rango.getUfs();
    List<Uf> ufSetSorted = ufSet.stream().collect(Collectors.toList());
    Collections.sort(ufSetSorted, (o1, o2) -> o1.getFecha().compareTo(o2.getFecha()));

 
    Uf element= new Uf();
    Uf element2=new Uf();
    List<Uf> ufMissing = new ArrayList();
    List<Uf> totalMissing = new ArrayList();
    for(int i=0; i<ufSetSorted.size()-1; i++){

       
         element = (Uf)ufSetSorted.get(i);
        i++;
        element2 = (Uf)ufSetSorted.get(i); 
        if(i==ufSetSorted.size()){
            
        }
        else{
            i--;
        }
        
        dias = (int) ((element2.getFecha().getTime()- element.getFecha().getTime())/86400000);
        if(dias>1){
            ufMissing = ufData.getUfs(element.getFecha(), element2.getFecha());
            totalMissing.addAll(ufMissing);
            //System.out.println("Hay un salto de "+dias+" en la info, entre fechas : "+element.getFecha()+" y "+element2.getFecha() );
        }      
    }
    ufSetSorted.addAll(totalMissing);
    ufSetSorted = ufSetSorted.stream().distinct().collect(Collectors.toList());
    Collections.sort(ufSetSorted, (o1,o2) -> o1.getFecha().compareTo(o2.getFecha()));
    Collections.reverse(ufSetSorted);

    createFile(ufSetSorted);
    }
    
    public void createFile(List<Uf> sortedList){
        
        String CSV_SEPARATOR = ";";
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("valores.csv"), "UTF-8"));
            StringBuffer encabezado = new StringBuffer();
            encabezado.append("1");
            encabezado.append(CSV_SEPARATOR);
            encabezado.append(transformDate(sortedList.get(sortedList.size()-1).getFecha()));
            encabezado.append(CSV_SEPARATOR);
            encabezado.append(transformDate(sortedList.get(0).getFecha()));
            bw.write(encabezado.toString());
            bw.newLine();
            for (Uf elementUf : sortedList)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append("2");
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transformDate(elementUf.getFecha()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(elementUf.getValor() == 0? "" : elementUf.getValor());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
        
        System.out.println("done!");
    }
    
    public String transformDate(Date myDate){
        
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

        try{
            dateString = sdfr.format( myDate );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }
    
    public static void main(String[] args) {
        PreviredApp myPrevApp = new PreviredApp();
       
    }
}
