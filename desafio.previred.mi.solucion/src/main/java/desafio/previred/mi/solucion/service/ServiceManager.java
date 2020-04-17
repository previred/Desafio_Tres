package desafio.previred.mi.solucion.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import desafio.previred.mi.solucion.entities.ViewUfs;

@Service
public class ServiceManager {

	private Uf uf = new Uf();
	private Ufs ufs = new Ufs();
	private Valores valoresManager = new Valores();
	private DatosUf datosManager;

	public ViewUfs getUfs() {
		ViewUfs ufsRt = new ViewUfs();

		ufs = valoresManager.getRango();

		List<Uf> colUf = new ArrayList<Uf>(ufs.getUfs().stream().map(u -> {
			checkFecha(u, ufs.getInicio(), ufs.getFin());
			checkValue(u);
			return u;
		}).collect(Collectors.toList()));

		colUf.sort(Comparator.comparing(Uf::getFecha));
		ufsRt.setFin(ufs.getFin());
		ufsRt.setInicio(ufs.getInicio());

		ufsRt.setUfs(colUf);

		return ufsRt;
	}

	private void checkValue(Uf item) {
		if (item.getValor() == null) {

			Uf ufFind = getUfbyDate(item.getFecha());
			if (ufFind.getValor() != null)
				item.setValor(ufFind.getValor());

		}
	}

	private void checkFecha(Uf item, Date inicio, Date fin) {

		if (item.getFecha() == null) {
			item.setFecha(new Date());
			Uf uf = getUfsbyRange(inicio, fin)
					.stream()		            
		            .filter(v -> item.getValor() > v.getValor())
		            .findFirst()
		            .get();
			
			item.setFecha(uf.getFecha());					
		}

	}

	private List<Uf> getUfsbyRange(Date inicio, Date fin) {

		List<Uf> coluf = datosManager.getUfs(inicio, fin);

		return coluf;
	}

	private Uf getUfbyDate(Date fecha) {

		uf = datosManager.getUf(fecha);

		return uf;
	}

}
