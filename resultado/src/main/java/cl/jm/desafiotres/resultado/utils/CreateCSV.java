package cl.jm.desafiotres.resultado.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class CreateCSV {

	private static final String EXPORT_CSV = "valores.csv";
	private static final String CSV_SEPARATOR = ";";
	
	SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
	
	@SuppressWarnings("deprecation")
	public void Create(Ufs n) throws IOException {
		List<Uf> listUf = new ArrayList<Uf>();
		for (Uf n2 : n.getUfs()) {
			listUf.add(n2);
		}
		
		Collections.sort(listUf, new Comparator<Uf>() {
        	SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
            public int compare(Uf o1, Uf o2) {
            	return parse.format(o2.getFecha()).compareTo(parse.format(o1.getFecha()));
            }
            });
        
		DatosUf datosUf = DatosUf.getInstance();

		Set<Uf> listUfFinal = new TreeSet<>(new ComparateUf());
		
		for (int i=0;i<listUf.size()-1;i++) {
			if (1 == listUf.get(i).getFecha().compareTo(listUf.get(i+1).getFecha())) {
	        	Date oneDayBefore = new Date(listUf.get(i).getFecha().getTime() - 2);
	        	if(!(0 == listUf.get(i+1).getFecha().compareTo(oneDayBefore))) {
	            	oneDayBefore.setHours(0);
	            	oneDayBefore.setMinutes(0);
	            	oneDayBefore.setSeconds(0);
	            	oneDayBefore.getTimezoneOffset();
	        		List<Uf> ufs = datosUf.getUfs(listUf.get(i+1).getFecha(),listUf.get(i).getFecha());
	        		for (Uf u : ufs) {
	        			if(listUfFinal.size()>=100) break;
	        			listUfFinal.add(u);
	        		}
	        	}else {
	        		if(listUfFinal.size()>=100) break;
	        		listUfFinal.add(listUf.get(i+1));
	        	}
			}
	        
	        if(listUfFinal.size()>=100) break;
		}
		
		StringBuilder sb = new StringBuilder();
		if (listUf != null) {
			
			sb.append(parse.format(n.getInicio()));
			sb.append(CSV_SEPARATOR);
			sb.append(parse.format(n.getFin()));
			sb.append(CSV_SEPARATOR);
			sb.append("\n");
			
			for(Uf u : listUfFinal) {
				if (null != parse.format(u.getFecha())) {
					sb.append(parse.format(u.getFecha()));
				}
				sb.append(CSV_SEPARATOR);
				if (u.getValor() != null) {
					sb.append(u.getValor());
				}
				sb.append(CSV_SEPARATOR);
				sb.append("\n");
			}
			
		}
		
        
		File file = new File(EXPORT_CSV);
        if (file.exists()) {
            try {
                File newFileName = new File(EXPORT_CSV);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString());
        //System.out.println(sb.toString());
        bw.close();
	}
	
}
