package cl.zco.desafio3.negocio;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import cl.zco.desafio3.dto.FormatoJsonDTO;
import cl.zco.desafio3.util.Constantes;

/**
 * The Class ManejadorArchivosTest.
 */
@SuppressWarnings({ "unused", "deprecation" })
class ManejadorArchivosTest {
	
	/** The json DTO. */
	private FormatoJsonDTO jsonDTO;
	
	/**
	 * Sets the up.
	 */
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		ManejadorArchivos ma = new ManejadorArchivos();
		jsonDTO = new FormatoJsonDTO();
		jsonDTO.setInicio("");
		jsonDTO.setFin("");
		
	}
	
	/**
	 * Caso cuando se quiere crear un fichero pero con ruta nula.
	 */
	@Test
	void crearFicheroJsonRutaNula() {
		assertThrows(NullPointerException.class, () -> 	ManejadorArchivos.crearFicheroJson(jsonDTO, null));
	}
	
	/**
	 * Caso cuando se quiere crear un fichero pero con ruta inexistente.
	 */
	@Test
	void crearFicheroJsonRutaInexistenteNoValidacion() {
		ManejadorArchivos.crearFicheroJson(jsonDTO, "/nopath",true);
		assertThatNoException(); //Excepcion controlada
	}
	
	/**
	 * Crear fichero json ruta inexistente.
	 */
	@Test
	void crearFicheroJsonRutaInexistente() {
		ManejadorArchivos.crearFicheroJson(jsonDTO, "/nopath",false);
		assertThatNoException(); //Excepcion controlada
	}
	
	/**
	 * Caso cuando va sin el jsonDTO.
	 */
	@Test
	void crearFicheroJsonSinJsonDTO() {
		ManejadorArchivos.crearFicheroJson(null, Constantes.RUTA_WKS_JSON);
		assertThatNoException();
	}
	
	/**
	 * Caso con todos los parametros.
	 */
	@Test
	void crearFicheroJson() {
		ManejadorArchivos.crearFicheroJson(jsonDTO, Constantes.RUTA_WKS_JSON);
		assertThatNoException();
	}
	
}
