package cl.zco.desafio3.util;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.zco.desafio3.Configuracion;

/**
 * The Class UtilTest.
 */
class UtilTest {
	
	/** The ufs. */
	private Ufs ufs; 
	
	/**
	 * Sets the up.
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		ufs = new Ufs();
		ufs.setInicio(Calendar.getInstance().getTime());
		ufs.setFin(Calendar.getInstance().getTime());
		
		//Iniciar clases de utilidad
		Configuracion conf = new Configuracion();
		Constantes constante = new Constantes();
		Util util = new Util();
	}
	
	/**
	 * Obtener fecha formato.
	 */
	@Test
	void obtenerFechaFormato() {
		assertNotNull(Util.obtenerFechaFormato(Calendar.getInstance().getTime(), Constantes.FORMATO_DD_MMM_YYYY));
	}
	
	/**
	 * Clasificar coleccion.
	 */
	@Test
	void clasificarColeccion() {
		Util.clasificarColeccion(new ArrayList<Uf>(), true, Constantes.ATRIB_FECHA );
		Util.clasificarColeccion(new ArrayList<Uf>(), false, Constantes.ATRIB_FECHA );
		assertThatNoException();
	}
	
	/**
	 * Convertira json DTO.
	 */
	@Test
	void convertiraJsonDTO() {
		
		assertNotNull(Util.convertiraJsonDTO(ufs,null,1));
		Uf uf = new Uf();
		uf.setFecha(Calendar.getInstance().getTime());
		uf.setValor(0.1d);
		List<Uf> listuf = new ArrayList<Uf>();
		listuf.add(uf);
		ufs.setUfs(new HashSet<Uf>(listuf));
		assertNotNull(Util.convertiraJsonDTO(ufs,listuf,1));
	}
	
	/**
	 * Validacion elementos en rango.
	 */
	@Test
	void validacionElementosEnRango() {
		Uf uf = new Uf();
		uf.setFecha(Calendar.getInstance().getTime());
		uf.setValor(0.1d);
		List<Uf> listuf = new ArrayList<Uf>();
		listuf.add(uf);
		ufs.setUfs(new HashSet<Uf>(listuf));
		List<Uf> listuf2 = new ArrayList<Uf>();
		assertTrue(Util.validacionElementosEnRango(listuf, listuf)); 
		assertFalse(Util.validacionElementosEnRango(listuf, listuf2)); 
		assertThrows(NullPointerException.class, () -> assertTrue(Util.validacionElementosEnRango(listuf, null)));
	}
	
	/**
	 * Validacion elementos en rango elemento nulo.
	 */
	@Test
	void validacionElementosEnRangoElementoNulo() {
		Uf uf = new Uf();
		uf.setFecha(Calendar.getInstance().getTime());
		uf.setValor(0.1d);
		List<Uf> listuf = new ArrayList<Uf>();
		listuf.add(uf);
		ufs.setUfs(new HashSet<Uf>(listuf));
		assertThrows(NullPointerException.class, () -> assertTrue(Util.validacionElementosEnRango(listuf, null)));
	}
	
	/**
	 * Obtener etiqueta.
	 */
	@Test
	void obtenerEtiqueta() {
		assertEquals("Se ha creado correctamente el archivo en ", Constantes.obtenerEtiqueta("mensaj.final.ok.ini"));
	}
	
	/**
	 * Obtener etiqueta null.
	 */
	@Test
	void obtenerEtiquetaNull() {
		assertEquals(Constantes.VACIO, Constantes.obtenerEtiqueta("mensaj.code"));
	}
	
	/**
	 * Validar configuraciones.
	 */
	@Test
	void validarConfiguraciones() {
		Configuracion conf = new Configuracion(100);
		assertNotNull(conf.toString());
	}
	
	
}
