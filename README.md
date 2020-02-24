# Desafío 3: Complemento valores UF


Descripci�n de Implementaci�n:

	- Libreria generadorade datos: el archivo .jar se incluye dentro del proyecto
	en el path src/main/resources/previred, luego en el POM se agrega la 
	referencia a esta ubicaci�n.
	
	- Implementaci�n: 
		
		se creo 2 clases Model 
		* UfDiariaLstModel: contiene fecha y valorUF 
		* UfDiariaModel : el rango de fecha y una lista con los valores generados 
			para cada fecha.
		se creo la clase Uftils para realizar las tareas requeridas.	
		
		La clase principal es RellenaUfDiaria:
		
		La logica es la siguiente:
		
		1.- Se convierte el Set<Uf> en una HashMap<String, Uf> donde el 
		key es la fecha formateada y tipo String.
		Esto facilita la busqueda del valor en base a la fecha
		( como el String es inmutable nos asegura la coincidencia de las key ).
		
		Este proceso se realiza en un ciclo for simple.
		
		 	-- UfUtils.setUfToHashmap
			
		2.- Se calcula la cantidad de dias del rango de fechas 
		
				-- UfUtils.rangoDias
		
		3.- En base al HashMap y la cantidad de d�as se crea una lista
		con todas las fechas ya ordenadas y formateadas, esto se hace en 
		un ciclo for y ademas en cada iteraci�n se va a buscar el valor 
		al HashMap y si no se encuentra se rescata de la librer�a.
		
				-- UfUtils.getLstUfDiaria
				
		4.- Se imprime el archivo en formato CSV
		
				--UfUtils.imprimeCSV



Tecnolog�a y librer�as utilizadas

	- Proyecto creado eclipse-maven
	
	- Libreria com.google.code.gson para correr libreria previred 
		(si mo se incluye cae)  
	
Detalles de compilaci�n y ejecuci�n
	
	- Abrir el proyecto con eclipse y compilar con un maven install,
		esto genera el archivo  Desafio_Tres_CVV-0.0.1.jar
	
	OBS: no logre hacer que funcione el  Desafio_Tres_CVV-0.0.1.jar 
	con las librerias dentro, probe empaquetar de varias formas
	y el con comando java -jar  Desafio_Tres_CVV-0.0.1.jar no se
	logro encontrar las clases de la libreria previred 
	( estando estas dentro del .jar)
	
	- Para ejecutar, entrar a la carpeta /taget detro del proyecto
	
	( linea de comandos ) y ejecutar:
	
	java -cp Desafio_Tres_CVV-0.0.1.jar;libs/*;. previred.RellenaUfDiaria
			
	esto generara el archivo uFsDiarias.csv dentro de la misma carpeta
	/target



