package previred;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import Utils.UfUtils;
import models.UfDiariaLstModel;
import models.UfDiariaModel;


public class RellenaUfDiaria {
	
	
	
	public static void main( String[] args ) {
		
		Valores vl = new Valores();
		Ufs ufs = vl.getRango();
		Set<Uf> setUfs = ufs.getUfs();	
		
		UfDiariaLstModel ufDiariaLstModel = new UfDiariaLstModel();
		Map<String, Uf> ufHashMap = UfUtils.setUfToHashmap(setUfs);
		
		ufDiariaLstModel.setFechaInicio( ufs.getInicio()  );
		ufDiariaLstModel.setFechaFin( ufs.getFin()    );
		ufDiariaLstModel.setFechaInicioFmt( UfUtils.dateToString( ufs.getInicio() ) );
		ufDiariaLstModel.setFechaFinFmt(  UfUtils.dateToString( ufs.getFin() )  );
		
		
		Long dias = UfUtils.rangoDias( 	ufDiariaLstModel.getFechaInicio(),
										ufDiariaLstModel.getFechaFin() );

		List<UfDiariaModel> lstUfDiaria = UfUtils.getLstUfDiaria( 	ufDiariaLstModel.getFechaInicio(), 
																	dias,
																	ufHashMap);
		
		ufDiariaLstModel.setLstUfDiaria( lstUfDiaria );
		UfUtils.imprimeCSV( ufDiariaLstModel );
		
		
	}
	
}
