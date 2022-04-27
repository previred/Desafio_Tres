package cl.zco.desafio3.negocio;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.zco.desafio3.Configuracion;
import cl.zco.desafio3.Proveedor;
import cl.zco.desafio3.util.Constantes;
import cl.zco.desafio3.util.Util;

/**
 * The Class ConsumirJarPrevired.
 * @author Patricio Angel
 */
public class ConsumirJarPrevired {
	
	/** The log. */
	private static Logger log = LogManager.getLogger(ConsumirJarPrevired.class);
	
	/** The max elementos json. */
	private int maxElementosJson = 0;
	
	/**
	 * Iniciar.
	 * @return true, if successful
	 */
	public boolean iniciar() {
		cargarConfiguraciones();
		return obtenerValoresUF();
	}
	
	private void cargarConfiguraciones() {
		maxElementosJson = Proveedor.getContexto().getBean(Configuracion.class).getCantidadjson();
	}
	
	/**
	 * Metodo que obtiene los valores de las UF.
	 * 
	 * @return true, if successful
	 */
	public boolean obtenerValoresUF() {
		boolean resultado = false;
		
		// Punto 1: se obtienen los valores y se consume el jar.
		Valores valores = new Valores();
		Ufs ufs = valores.getRango();
		List<Uf> listUf = new ArrayList<>( ufs.getUfs());
		
		/**
		 *  Punto 2: el algoritmo que entiendo que hay que hacer es llenar las fechas faltantes
		 *  pero siento que es innesacio hacer un algoritmo para validar si está o no dado que 
		 *  el singleton que se llena al inicio ya tiene los valores, entonces con solo obtener
		 *  todo entre el rango deberia bastar.
		 */
		// Punto 3: aqui se utiliza el .getUfs() este trae todos los valores.
		List<Uf> listUfRango = DatosUf.getInstance().getUfs(ufs.getInicio(), ufs.getFin());
		boolean igualCantidad = Util.validacionElementosEnRango(listUf, listUfRango);

		/**
		 *  Aqui podemos hacer de 2 maneras para agregar los que faltan entre el rango, una es con la lista 
		 *  tomar los extremos e ir consultando 1 a 1 y agreando si la fecha no esta, pero revisando es lo 
		 *  mismo traer la lista completa segun el rango y luego sobre esa lista ordenar.
		 *  > Validacion: de igual manera se validará que los que se encontraron dentro del rango estén todos en 
		 *  el ultimo listado.
		 */
		// Punto 4: se ordena de forma descendente
		Util.clasificarColeccion(listUfRango, false, Constantes.ATRIB_FECHA);
		
		if(igualCantidad) {
			log.trace("Todos los elementos de getRange() estan en el listado final.");
			resultado = true;
			resultado &= generarCSV(ufs);			    //FORMATO 1 //TODO: Pendiente para una nueva version
			resultado &= generarXML(ufs);			    //FORMATO 2 //TODO: Pendiente para una nueva version
			resultado &= generarJson(ufs, listUfRango); //FORMATO 3
		}
		
		return resultado;
	}
	
	/**
	 * Metodo para generar los CSV
	 * @param ufs parametro de entrada con data de ufs
	 * @return boolean generacion exitosa
	 */
	public boolean generarCSV(Ufs ufs) {
		log.trace("Se comienza con la generacion del csv");
		//TODO: solo era un formato de salida, asique queda pendiente.
		return true;
	}
	
	/**
	 * Metodo para generar los XML
	 * @param ufs parametro de entrada con data de ufs
	 * @return boolean generacion exitosa
	 */
	public boolean generarXML(Ufs ufs) {
		log.trace("Se comienza con la generacion del xml");
		//TODO: solo era un formato de salida, asique queda pendiente.
		return true;
	}
	
	/**
	 * Metodo que genera el json.
	 * @param ufs parametro de entrada con data de ufs
	 * @param listUfRango the list uf rango
	 * @return boolean generacion exitosa
	 */
	public boolean generarJson(Ufs ufs, List<Uf> listUfRango) {
		log.trace("Se comienza con la generacion del json");
		return ManejadorArchivos.crearFicheroJson(Util.convertiraJsonDTO(ufs,listUfRango,maxElementosJson), Constantes.RUTA_WKS_JSON);
	}
	
}
