# previred

## Descripción de la implementación
La implementación realizada resuelve el problema planteado con respecto a las lagunas de datos entregados.  Para poder dar solución a este problema se ordenaron los valores obtenidos y en base a los faltantes se completaron.

## Tecnología y librerías utilizadas

### tecnologias. 
* Spring boot
* Maven
* Java 8

### Librerias

* GSON: Para generar el json que luego se imprime en el archivo. 
* LOG4J: Para poder loguear información. 
* Previred: Libreria proporcionada para generar datos.

## Detalles de compilación y ejecución
La compilación del proyecto se realiza mediante el comando `mvn clean install`, con esta ejecución se genera el archivo desafio3.jar el cual queda disponible para poder ejecutarlo mediante java (8).
Es importante remarcar que para que compile el proyecto se debe instalar la libreria proporcionada en el repositorio m2 de maven.  para ello ejecutar `mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=Generador_Datos_Desafio_Tres-1.0.0.jar`.

La ejecución de la applicación se realiza mediante el comando `java -jar desafio3.jar`. Una vez ejecutado el comando la applicación genera el archivo Valores.json el cual esta ordenado por fecha.

Creado por Gabriel Casanova
