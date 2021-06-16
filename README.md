**IMPLEMENTACION DE DESAFIO**

El proyecto con el que dimos solucion al desafio sera una aplicación web en el que añadiremos una controller, que muestra como queda el archivo valores.json. Por lo tanto, tenemos una aplicacion echa en maven y donde vamosa utilizar Spring Boot.

Para la implementacion se agregan dependencias como gson, jackson.datatype y spring-boot-starter-thymeleaf, dependencias necesarias para poder trabajar con los datos, convertir los objetos a JSON y poder utilizar el template al cual le insertamos el archivo json resultante.

**Detalles de ejecucion**

Para poder utilizar la aplicacion se necesita tener instalados maven 3.6.3 y el jdk 11 en el equipo.

Una vez que ya esten instalados, clone el proyecto Desafio_Tres desde el repositorio.
 
Abra la carpeta del proyecto una terminal, si nos encontramos trabajando en ubuntu (abrimos la carpeta Desafio_Tres y apretamos a la misma vez ctrl+alt+t) o un cmd, si nos ecnotramos trabajando en windows (abrimos la carpeta Desafio_Tres y borramos todo lo que tenga la barra de direcciones y escribimos cmd).
 
Una vez abierta la terminal ejecutamos el siguiente comando mvnw spring-boot:run para correr la aplicacion.
 
Esperamos a que se ejecute el servidor y no cerramos la terminal sino se cerraria el servidor.
 
Ahora abra el navegador y escribimos en la barra de direcciones http://localhost:8080/ para obtener el resultado de correr el algoritmo. 

**Observaciones**

La aplicacion cuenta con un swagger que puede ser visualizado en http://localhost:8080/swagger-ui.html#.

Los errores de HttpServlet se capturan y se mustran en la cosola con ayuda de la clase GlobalExceptionHandler,ademas se guardan en un archivo aplication.log.