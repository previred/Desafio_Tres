package cl.zco.desafio3.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cl.zco.desafio3.dto.FormatoJsonDTO;
import cl.zco.desafio3.dto.FormatoUfJsonDTO;

/**
 * The Class UtilTest.
 */
class DtoTest {
	
	/** The formato json DTO. */
	private FormatoJsonDTO formatoJsonDTO;
	
	/** The formato uf json DTO. */
	private FormatoUfJsonDTO formatoUfJsonDTO;
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		formatoJsonDTO = new FormatoJsonDTO();
		formatoUfJsonDTO = new FormatoUfJsonDTO(null, null);
	}
	
	/**
	 * Construye json.
	 */
	private void construyeJson() {
		formatoUfJsonDTO.setFecha("01/01/1990");
		formatoUfJsonDTO.setDato("22222.11");
		List<FormatoUfJsonDTO> listUf = new ArrayList<FormatoUfJsonDTO>();
		listUf.add(formatoUfJsonDTO);
		
		formatoJsonDTO.setInicio("01/01/1990");
		formatoJsonDTO.setFin("01/01/2000");
		formatoJsonDTO.setUFs(listUf);
	}
	
	/**
	 * Obtener fecha formato.
	 */
	@Test
	void construirJsonDto() {
		construyeJson();
		assertNotNull(formatoJsonDTO);
	}
	
	/**
	 * Obtener datos json dto.
	 */
	@Test
	void obtenerDatosJsonDto() {
		construyeJson();
		assertNotNull(formatoJsonDTO.getInicio());
		assertNotNull(formatoJsonDTO.getFin());
		assertNotNull(formatoJsonDTO.getUFs());
		assertNotNull(formatoUfJsonDTO.getDato());
		assertNotNull(formatoUfJsonDTO.getFecha());
	}
	
}
