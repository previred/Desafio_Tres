package solucion.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import solucion.entities.MisUF;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.*;

/**
 * 
 * @author Jorge Rojas
 *
 *clase de servicio para rellenar las lagunas de Ufs que retorna la llamada del método Valores.getRango()
 *Él único método de esta clase entrega un log sencillo por consola.
 */
@Service
public class UFService 
{
	private Valores set_valores = new Valores();
	private Ufs ufs = new Ufs();
	private DatosUf data = DatosUf.getInstance();
	private List<Uf> lista_uf;
	
	
	public MisUF entrega_UF()
	{
		ufs = set_valores.getRango(); // lista con lagunas
		lista_uf = data.getUfs(ufs.getInicio(), ufs.getFin()); //lista completa

		Comparator<Uf> porFecha = Comparator.comparing(Uf::getFecha);
		
		List<Uf> lista_valores_uf_laguna = new ArrayList<Uf>();
		lista_valores_uf_laguna.addAll(ufs.getUfs());
			
		for(Uf buscador : lista_uf)
		{
			if(lista_valores_uf_laguna.contains(buscador))
			{
				System.out.println("Esta UF esta: " +buscador.toString());
			}	
			else
			{
				System.out.println("Esta UF NO esta: " +buscador.toString());
				lista_valores_uf_laguna.add(buscador);
			
			}
		}
		
		Collections.sort(lista_valores_uf_laguna, porFecha.reversed()); //ordenamos
		
		System.out.println("Fecha Inicio:" +ufs.getInicio());
		System.out.println("Fecha Fin: "+ufs.getFin());
		System.out.println("Largo Lista Original: " + lista_uf.size());
		System.out.println("Largo Lista Laguna: " + lista_valores_uf_laguna.size());
		
		MisUF mis_ufs = new MisUF(ufs.getInicio(),ufs.getFin(),lista_valores_uf_laguna);
		
		return mis_ufs;
	}
	
}
