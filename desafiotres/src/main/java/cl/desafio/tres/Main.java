package cl.desafio.tres;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.desafio.tres.model.Response;
import cl.desafio.tres.model.UF;
import cl.desafio.tres.util.Utils;

public class Main {
	public static void main(String[] args) {
		Valores valores=new Valores();
		Ufs rangoUfs=valores.getRango();
		Response ufsResult=new Response();
		ufsResult.setInicio(rangoUfs.getInicio());
		ufsResult.setFin(rangoUfs.getFin());
		List<Uf> lstOrde=rangoUfs.getUfs().stream().sorted(Comparator.comparing(Uf::getFecha)).collect(Collectors.toList());
		Date dayAux=lstOrde.get(0).getFecha();
		LinkedHashSet<UF> ufs=new LinkedHashSet<UF>();

		for(Uf item : lstOrde) {
			if(!Utils.equalDate(dayAux,item.getFecha())) {
				do {
					ufs=addUf(ufs,dayAux);
					dayAux= Utils.addDays(1,(Date) dayAux.clone());
				}while(Utils.equalDate(dayAux,item.getFecha()));
			}
			ufs= addUf(ufs,dayAux);
			dayAux=Utils.addDays(1,(Date) dayAux.clone());
		}
		ufsResult.setUfs(ufs);
	
		Utils.printToJSON(ufsResult);
	}
	private static LinkedHashSet<UF> addUf(LinkedHashSet<UF> ufs,Date dayAux) {
		try {
			dayAux.setHours(0); dayAux.setMinutes(0);dayAux.setSeconds(0);
			UF uf=new UF(dayAux,DatosUf.getInstance().getUf(dayAux).getValor());
			ufs.add(uf);
		}catch(Exception ex) {System.out.println("fecha "+dayAux); ex.printStackTrace();}
		return ufs;
	}
}
