package cl.zco.desafio3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Clase con el proveedor del contexto.
 * @author Patricio Angel
 *
 */
@Component
public class Proveedor implements ApplicationContextAware{
	
	/** The contexto. */
	@Autowired
	private static ApplicationContext contexto;

	/**
	 * Sets the application context.
	 * @param applicationContext the new application context
	 * @throws BeansException the beans exception
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		contexto = applicationContext;
	}

	/**
	 * Gets the contexto.
	 * @return the contexto
	 */
	public static ApplicationContext getContexto() {
		return contexto;
	}
 
}
