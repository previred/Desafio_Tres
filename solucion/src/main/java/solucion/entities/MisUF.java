package solucion.entities;

import java.util.Date;
import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;

/**
 * 
 * @author Jorge Rojas
 *
 * Esta clase define la entidad que será usada dentro de la clase de servicio para contener el resultado de la operación. una instancia de
 * esta clase es entregada finalmente a la clase Controller.
 *
 */

public class MisUF 
{
	private Date fecha_inicio;
	private Date fecha_fin;
	private List<Uf> lista_uf;
	
	public MisUF() {}

	public MisUF(Date fecha_inicio, Date fecha_fin, List<Uf> lista_uf) {
		super();
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.lista_uf = lista_uf;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public List<Uf> getLista_uf() {
		return lista_uf;
	}

	public void setLista_uf(List<Uf> lista_uf) {
		this.lista_uf = lista_uf;
	}
	
	
		
}
