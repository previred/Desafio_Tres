package cl.zco.desafio3;

import javax.swing.JOptionPane;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

import cl.zco.desafio3.negocio.ConsumirJarPrevired;
import cl.zco.desafio3.negocio.ManejadorArchivos;
import cl.zco.desafio3.util.Constantes;
import cl.zco.desafio3.util.Util;

/**
 * The Class Desafio3Application.
 */
@SpringBootApplication
@PropertySource(value = {"classpath:desafio3.properties"})
@EnableConfigurationProperties(value = {Configuracion.class})
public class Desafio3Application {

	/**
	 * The main method.
	 * @param args the arguments
	 * @throws Throwable the throwable
	 */
	public static void main(String[] args) throws Throwable {
		SpringApplicationBuilder constructor = new SpringApplicationBuilder(Desafio3Application.class);
		constructor.headless(false);
		ConfigurableApplicationContext contextoAplicacion = constructor.run(args);
		Proveedor proveedor = new Proveedor();
		proveedor.setApplicationContext(contextoAplicacion);
		iniciarAplicacion();
	}
	
	/**
	 * Iniciar aplicacion.
	 * @throws Throwable the throwable
	 */
	public static void iniciarAplicacion() throws Throwable {
		boolean modoDesarrollador = ManejadorArchivos.existeCarpeta(Constantes.RUTA_WKS_JSON);
		if(modoDesarrollador) {
			Util.controlExcepcion(Constantes.obtenerEtiqueta("mensaj.portada.desa"));
		}	else {
			Util.controlExcepcion(Constantes.obtenerEtiqueta("mensaj.portada.jar"));
		}
		
		ConsumirJarPrevired consumir = new ConsumirJarPrevired();
		boolean completado = consumir.iniciar();
		if(completado) {
			Util.controlExcepcion(Constantes.obtenerEtiqueta("mensaj.final.ok.ini").concat(modoDesarrollador ? Constantes.RUTA_WKS_JSON : Constantes.RUTA_JAR_JSON));
		}else {
			Util.controlExcepcion(Constantes.obtenerEtiqueta("mensaj.final.error"),  JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
