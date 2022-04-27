package cl.zco.desafio3.negocio;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsumirJarPreviredTest.
 */
@SuppressWarnings("deprecation")
class ConsumirJarPreviredTest {
	
	/** The jar previred. */
	private ConsumirJarPrevired jarPrevired;
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		jarPrevired = new ConsumirJarPrevired();
	}
	
	/**
	 * Obtener fecha formato.
	 */
	@Test
	void obtenerFechaFormato() {
		jarPrevired.iniciar();
		assertThatNoException();
	}
	
}
