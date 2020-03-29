# Descripción de la implementación
	Se descargo el jar solicitado y se declaró como una dependencia de Maven.
	Se usaron los métodos getRango() de la clase Valores, getUfs() de la clase Ufs y getUfs() de la clase DatosUf.

# Tecnología y librerías utilizadas
	Java 1.8
	Spring Boot
	Log4j
	Gson

#  Detalles de compilación y ejecución
	Se creo un Api Rest con los siguiente EndPoint

	http://localhost:8080/previred/ufs
	La url anterior aplica la solución a la problemática planteada, complementa los valores de UF para las fechas faltantes en la lista contenidas.
	retornar un json.

	http://localhost:8080/previred/zipValores
	La url anterior descarga el archivo valores.json dentro de un zip.
