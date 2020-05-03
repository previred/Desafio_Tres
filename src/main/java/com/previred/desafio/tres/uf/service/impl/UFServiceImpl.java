package com.previred.desafio.tres.uf.service.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.common.UFConstant;
import com.previred.desafio.tres.uf.model.UF;
import com.previred.desafio.tres.uf.model.UFSResult;
import com.previred.desafio.tres.uf.service.UFService;
import com.previred.desafio.tres.uf.util.UFUtil;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Service
public class UFServiceImpl implements UFService{

	@Override
	public String getUfs() {		
		UFSResult result = getRangoRestructured();
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		try {
			json = objectMapper.writeValueAsString(result);
			writeLocalFile(json);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private UFSResult getRangoRestructured() {
		UFSResult result = new UFSResult();
		Valores valores = new Valores();	
		//1. Consumir la función getRango de la clase com.previred.desafio.tres.uf.Valores
		Ufs rango = valores.getRango();
		result.setInicio(UFUtil.dateFormat(rango.getInicio(), UFConstant.YYYYMMDD));
		result.setFin(UFUtil.dateFormat(rango.getFin(), UFConstant.YYYYMMDD));
		
		//2. Escribir un algoritmo para complementar los valores de UF para 
		//las fechas faltantes en la lista contenidas en la clase Ufs que retorna getRango
		ArrayList<UF> ufs = getComplementedRango(rango);
			
		//4. La lista de salida debe esta ordenada de forma descendente.
		result.setUfs(sortListByDateDec(ufs));
		return result;
	}
	
	
	private ArrayList<UF> getComplementedRango(Ufs rango) {
		//3. Para complementar los valores de UF se pueden utilizar 
		//los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf.
		List<Uf> list = getUfsByRange(rango.getInicio(),rango.getFin());
		ArrayList<UF> ufs = new ArrayList<UF>();
		for (Uf originalUF : list) {
			UF uf = new UF();
			uf.setFecha(UFUtil.dateFormat(originalUF.getFecha(), UFConstant.YYYYMMDD));
			uf.setDato(String.valueOf(UFUtil.valueFormat(originalUF.getValor())));
			ufs.add(uf);
		}
		return ufs;
		
	}
	
	private List<Uf> getUfsByRange(Date initDate, Date endDate) {
		return DatosUf.getInstance().getUfs(initDate, endDate);
	}
	
	private ArrayList<UF> sortListByDateDec(ArrayList<UF> list) {
		Collections.sort(list, UF.Fecha);
		return list;
	} 
	
	private void writeLocalFile(String result) {
		try {
            FileOutputStream outputStream = new FileOutputStream(UFConstant.FILENAME + "." + UFConstant.FILEEXTENSION);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(result);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
