* Descripción de la implementación
 Para la implementación se realizó una aplicacion java que consta de 3 clases:
 1.- CompletarValores.java: esta contiene los referente a completar los valores uf faltante asi como ordenar la lista de UF.
 2.- ExportarCsv.java: esta clase contiene todo lo referente a la generación del archivo de salida.
 3.- App.Java: esta clase es la clase que contiene el main por tanto es el punto de partida para correr la aplicación, esta importa las dos clases anteriores y organiza la ejecución de los metodos para generar el archivo de salida, obteniendo y pasando a travès de los parametros los datos necesarios. 
 
 para la implementación se partido de la premisa que el metodo getRango de la clase Valores retornaria una lista cuyo tamaño maximo es 100 o menor que 100,
 para agregar los UF faltante se creo un mètodo en la clase CompletarValores,  este método genera un rango de fecha a travès de la clase RamdonDate con los valores de inicio y fin de la lista de UF y a partir de allì se obtiene una fecha aleatoria dentro del rango y con la fecha un UF que se agregan a la lista.
 
 La aplicación genera un archivo log llamado desafio.log donde se imprime los log de nivel INFO, DEBUG Y ERROR
 La aplicación cuenta con un archivo config.properties donde se puede cambiar el nombre del archivo de salida, el mismo se encuentra en la ruta  /src/main/resource

* Tecnología y librerías utilizadas
- Java 1.8 
- Maven
- log4j para la generación de los log
- Apache Poi y OpenCsv para la escritura del archivo csv.
- Gson y Generador_Datos_Desafio_Tres.jar para obtener y generar los Ufs
- IDE a utilizado  Eclipse Java EE IDE for Web Developers. Version: Luna Service Release 2 (4.4.2)


* Detalles de compilación y ejecución

1. Para la compilación se utilizó Maven, se debe agregar el .jar Generador_Datos_Desafio_Tres-1.0.0.jar suministrado para el desafio en el repositorio Maven
2. Para compilar y ejecutar el proyecto se usó Eclipse Java EE IDE for Web Developers. Version: Luna Service Release 2 (4.4.2) siguiendo los siguientes:
	a.- Abrir Eclipse
	b.- Seleccionar Import ->Maven->Existing Maven Projects
	c.- Ubicar y seleccionar el archivo pom.xml
	d.- En eclipse dentro de Project Explore ( en la estructura del proyecto) ubicamos en la carptea preverid, presionamos clip derecho  Run As-> Maven Install
	e.- En eclipse dentro de Project Explore ( en la estructura del proyecto) el archivo que tiene por nombre App, ubicado en la siguiente ruta /src/main/java/com/desafio/previred
	f.- Hacer click derecho sobre el archivo Principal y seleccionar Run As -> 2 Java Application , o dirigirse al icono del IDE  correspondiente a Run App


La salida  es un archivo llamando desafio.csv se visualizara en la carpeta donde esta ubicado el archivo pom.xml
