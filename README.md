# Desafío 3: Complemento valores UF

- Descripción de la implementación:
	Creo 3 clases:
		DesafioTres -> Clase principal;
			Obtengo valores con el método getRango de la clase Valores, de aqui rescato la fecha inicio y la fecha fecha fin de la consulta.
			El set de UF lo implemento como List por comodidad.
			Llamo al método completarFechas de la Clase Utiles
			Una vez está la lista con todas las fechas y UFs, ordeno la lista de forma descendente.
			Para finalmente generar el archivo XML.
			
		Utiles		-> Clase que contiene los métodos para ordenar, completar datos y validar fecha.
			En el método completarFechas, con el rango de fechas (obtenido en el método DesafioTres.procesaDatos) genero una lista "fechasUfLista" 
			con datosUf.getUfs, la cual se recorrera y se irá comparando con la lista que entra como parametro "fechasUfsRango" (que se generó con getRango)
			Se irá completando una lista final fechasUfsFin según los datos comparados, respetando los valores de  fechasUfsRango, 
			que se complementan con los de fechasUfLista.
			
			Tuve mis dudas aquí, pues simplemente se podía utilizar la lista que entrega el método datosUf.getUfs y con esto continuar 
			con los siguentes pasos, o incluso solo hacer un cruce de listas, pero como se solicitaba escribir un algoritmo, eso hice.
			
			El método que ordena una lista se realizó con Collections.sort (reversed).
			
		CreaXML		-> Clase para generar archivo valores.xml
			El método generarXML recibe como parámetros la fechaInicio, fechaFin y la listaDatos previamente ordenada de forma descendente.
			Con DocumentBuilderFactory se avanza la configuración del archivo, sus tags y el formato requerido hasta generar el fichero valores.xml.
	
	
	
- Tecnología y librerías utilizadas
	Java 1.8 - NetBeans 11.3 - Log4j para mensajes en caso de Exceptions
	com.google.code.gson 2.8.5 (requerida por la librería Generador_Datos_Desafio_Tres-1.0.0.jar)
	
- Detalles de compilación y ejecución	
	importar proyecto en eclipse o netbeans, sobre la clase DesafioTres ejecutar como aplicación java 