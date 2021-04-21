package cl.previred.tres;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.tres.uf.vo.Uf;

public class UFRange {
	private LocalDate inicio;
	private LocalDate fin;
	private List<Uf> UFs;
	
	public UFRange(Ufs ufs) {
		inicio = ufs.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		fin = ufs.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		DatosUf datosUf = DatosUf.getInstance();
		
		final Map<LocalDate, Uf> ufByDate = new HashMap<LocalDate, Uf>();

		ufs.getUfs().forEach(uf -> ufByDate.put(uf.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() , uf));
		
		UFs = new ArrayList<Uf>();
		
		LocalDate iterator = fin.minusDays(1);
		LocalDate lastDate = inicio.minusDays(1);
		while (iterator.isAfter(lastDate)) {
			if(ufByDate.containsKey(iterator)) {
				UFs.add(ufByDate.get(iterator));
			} else {
				UFs.add(datosUf.getUf(java.sql.Date.valueOf(iterator)));
			}
			iterator = iterator.minusDays(1);
		}
		
		
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public List<Uf> getUFs() {
		return UFs;
	}

	public void setUFs(List<Uf> uFs) {
		UFs = uFs;
	}

}
