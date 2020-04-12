Implementación:

1) Se consume el metodo com.previred.desafio.tres.uf.Valores.getRango().getUfs() para obtener las Ufs entre un rango de fechas aleatoreo.

2) Con este rango de fechas se obtiene la lista completa de Ufs consumiendo el metodo com.previred.desafio.tres.uf.DatosUf.getUfs.

3) Sa analiza la data resultante del punto 1 en busca de los dias sin informacion, y estos dias se rellenan con la data resultante del punto 2.

4) Se ordena la data de forma Descendente.

5) Se da formato de la data solicitada en segun requerimiento, eligiendo como archivo de salida un .json

6) Se crea archivo json, se rellena con la data resultante y se procede a almacenar en diretorio_del_repositorio/desafiotres/OutputFiles/valores.json  

7) Adicionalmente se registraron logs para guardar información acerca de los procesos de la aplicación.


Java 1.8
Maven
Librerias externas utilizadas:

	GroupId: com.previred.desafio.tres
	ArtifactId: Generador_Datos_Desafio_Tres
	Versión: 1.0.0

	GroupId: com.google.code.gson
	ArtifactId: gson
	Versión: 2.8.4

	GroupId: log4j
	ArtifactId: log4j
	Versión: 1.2.17



Instrucciones para ejecución:

1) Ubicar la ruta del repositorio, mas concretamente del proyecto.

Ruta Referencial: C:\Development\Previred\Desafio_Tres\desafiotres

* Donde "Desafio_Tres" es el nombre del directorio del repositorio.
* Donde "desafiotres" es el nombre del directorio del proyecto en si.

2) Abrir una ventan de comandos (CMD) en esa dirección.

3) Ejecutar el siguiente comando:

mvn clean install al proyecto.

4) Ahora ejecutar el siguiente comando:

java -jar target/desafio-tres-1.0-SNAPSHOT-jar-with-dependencies.jar

5) Presionar una tecla para finalizar el programa.

6) Evaluar en el directorio OutputFiles/ ubicado en esa misma ruta, para verificar el resultado del programa.


NOTA: para ejecutar nuevamente el programa, no es necesario ejecutar el paso 3.