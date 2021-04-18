# Valores Uf

- Proyecto java construido con maven, que tiene como finalidad, completar los valores de uf's para un rango de fechas y valores uf's, entregados por una libreria externa, y asi exportar un archivo "valores.json" con todas las fechas y valores de uf dentro de ese rango de fechas.
- Cave destacar que en la ruta src/main/resources/valores.json ya se encuentra el archivo "valores.json" 

# Teclonologas y librerias 

- Java 8
- Maven 3
- gson
- jackson-databind
- jackson-datatype-jsr310 
- desafioTres (libreria externa que entrega los valores de uf's junto al rango de fechas)

# Compilacion y Ejecucion

- Primero se debe instalar la libreria externa como dependencia dentro del repositorio local de maven, para ello realizar los siguiente pasos:

	1) Se recomienda copiar y pegar la libreria externa (Generador_Datos_Desafio_Tres-1.0.0.jar) en algun directorio que no tenga restricciones de usuario 	administrador
	
	2) Luego abrir una terminal dentro de esa misma direccion en donde se pego el .jar y ejecutar el siguiente comando maven
		
		mvn install:install-file -Dfile=${baseDir}\Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=com.previred.desafio.tres -DartifactId=desafioTres -Dversion=1.0.0 -Dpackaging=jar
		
		IMPORTANTE -> ${baseDir} se debe reemplazar por la ruta en donde se encuentra la libreria 
	
	3) Luego posicionarse en el directorio Desafio_Tres (donde debe estar el archivo pom.xml del proyecto) y ejecutar los siguientes comandos:
		
		----
		mvn clean install
		---- 
		
		y luego...
		
		----
		mvn exec:java -Dexec.mainClass=com.previred.valoresuf.app.App
		----
		
		Este ultimo comando ejecutara la aplicacion, dejando en la ruta src/main/resources/valores.json el archivo .json con las fechas y valores uf's, ordenadas de mayor a menor 
		

# Datos desarrollador

	Cristian Rubén Vásquez Portugal
	cristian.rubenv6@gmail.com
