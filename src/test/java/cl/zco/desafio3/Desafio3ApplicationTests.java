package cl.zco.desafio3;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The Class Desafio3ApplicationTests.
 */
@SpringBootTest
class Desafio3ApplicationTests {

	/**
	 * Context loads.
	 * @throws Throwable 
	 */
	@Test
	void contextLoads() throws Throwable {
		String[] str = {"str1","str2"};
		assertThrows(Throwable.class, () -> Desafio3Application.main(str) );
	}

}
