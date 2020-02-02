package com.previred.desafio.tres.impl.completar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.previred.desafio.tres.impl.completar.ordenar.OrdenarUfs;
import com.previred.desafio.tres.inter.completar.ICompletarUfs;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class CompletarUfs implements ICompletarUfs {

	private static final DatosUf DATOS_UF = DatosUf.getInstance();
	private static final OrdenarUfs ORDERNAR_UF = OrdenarUfs.getInstance();

	@Override
	public List<Uf> completarValoresUfs(Ufs ufs) {
		List<Uf> listaUf = ORDERNAR_UF.ordenarUfPorFecha(ufs.getUfs());
		List<Uf> listaUfImprimir = null;
		if (listaUf == null) {
			listaUfImprimir = rangoFechas(ufs.getInicio(), ufs.getFin());
		} else {
			listaUfImprimir = new ArrayList<Uf>();
			Uf ultimaUf = iterarFechas(ufs.getInicio(), listaUf, listaUfImprimir);
			if (!DateUtils.isSameDay(ufs.getFin(), ultimaUf.getFecha())) {
				Date fecha = sumarDia(ultimaUf.getFecha());
				listaUfImprimir.addAll(rangoFechas(fecha, ufs.getFin()));
			}
		}
		return listaUfImprimir;
	}

	/**
	 * Itera sobre las generacion parcial de fechas
	 * 
	 * @param fechaReferencial
	 *            fecha referencial de la lista de fechas generadas
	 * @param listaUf
	 *            lista de fechas generadas
	 * @param listaUfImprimir
	 *            lista resultante
	 * @return ultimo registro de la lista
	 */
	private Uf iterarFechas(Date fechaReferencial, List<Uf> listaUf, List<Uf> listaUfImprimir) {
		Uf uf = listaUf.remove(0);
		fechaReferencial = fechSinHora(fechaReferencial);
		Date fecha = fechSinHora(uf.getFecha());
		if (DateUtils.isSameDay(fechaReferencial, fecha)) {
			listaUfImprimir.add(uf);
		} else {
			listaUfImprimir.addAll(rangoFechas(fechaReferencial, fecha));
		}
		if (listaUf.isEmpty()) {
			return uf;
		} else {
			fechaReferencial = sumarDia(fecha);
			return iterarFechas(fechaReferencial, listaUf, listaUfImprimir);
		}
	}

	/**
	 * Cambia la fecha dejando el horas, minutos, segundos y milisegundos en cero
	 * 
	 * @param fecha
	 * @return
	 */
	private Date fechSinHora(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Extrae un rango de uf y las ordena
	 * 
	 * @param inicio
	 *            fecha de inicio del rango
	 * @param fin
	 *            fecha de fin del rango
	 * @return lista ordenada
	 */
	private List<Uf> rangoFechas(Date inicio, Date fin) {
		List<Uf> ufs = DATOS_UF.getUfs(inicio, fin);
		ORDERNAR_UF.ordenarUfPorFecha(ufs);
		return ufs;
	}

	/**
	 * Suma un dia a la fecha
	 * 
	 * @param fecha
	 * @return
	 */
	private Date sumarDia(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DATE, 1);
		fecha = calendar.getTime();
		return fecha;
	}

}
