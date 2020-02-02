# Descripción de la implementación:

> **Resumen:** El algoritmo se basa en dado la lista de Uf parciales (previamente ordenada), extraer los rangos faltantes y ordenarlas al momento, para así ir agregando a la lista definitiva. 
> Luego la lista definitiva se exporta a un archivo CSV.

La solución consiste en dos procesos, el primero completar la data faltante y el segundo en generar el archivo de salida.   
Lo primero es generar y validar el objeto **Ufs** ese mismo objeto se pasa a la clase  **CompletarUfs**.
La clase **CompletarUfs** ordena la lista de Uf parciales, luego se valida si la misma lista está vacía, de estar vacía se generan todos los valores para el inicio y fin de la clase **Ufs**, y luego se ordenan los registros para así retornarlos.
Si la lista de Uf parciales  posee datos se recorre de forma recursiva realizando lo siguiente.
1. se toma la fecha de inicio del objeto Uf y se remueve de la lista el primer objeto Uf.
2. De la fecha se comparan las mismas fechas. Si son iguales se agrega el valor de la Uf a la lista a retornar. Sino se saca el rango tomando la fecha de inicio contra la fecha del Objeto Uf, el rango se ordena y se agrega a la lista a retornar.
3. Si la lista está vacía este caso se retorna el valor de la Uf, que para ese momento es el último registro de la lista de Uf parciales.
4. Sobre la fecha del Objeto Uf se suma un día y se vuelve al paso 1, pero con la nueva fecha.
el programa se vuelve a ejecutar cambiando la fecha de inicio por la nueva fecha generada.

Con la fecha generada se suma un dia para asi comparar  con la fecha de fin de vigencia del objeto **Ufs**, si son iguales se rescata el valor, sino se extrae el rango de fechas y se ordena.
Con la lista de fechas completa se envia a la clase la cual aplica el formato correspondiente.

#Tecnología y librerías utilizadas:
El proyecto fue construido y compilado con maven 3.6.0 en la versión 1.8.0_181 de Java.
	En tanto las librerías usadas para el desarrollo:
* Generador_Datos_Desafio_Tres 1.0.0 Librería indicada en el requerimiento
* [gson](https://github.com/google/gson "gson") - dependencia de la libreria Generador_Datos_Desafio_Tres 1.0.0
* [commons-csv](https://commons.apache.org/proper/commons-csv/ "commons-csv") - para la generacion del archivo CSV, formato seleccionado para generar la salida
* [commons-lang](https://commons.apache.org/proper/commons-lang/ "commons-lang") - libreria para el trabajo con las fechas. 
* [log4j](https://logging.apache.org/log4j/2.x/ "log4j") para el manejo de excepciones y errores

#Detalles de compilación y ejecución:
Lo primero es agregar el Generador_Datos_Desafio_Tres-1.0.0.jar a maven, para eso se debe ejecutar el siguiente comando
	
```sh
$ mvn install:install-file -Dfile=./lib/Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=com.previred.desafio.tres -DartifactId=Generador_Datos_Desafio_Tres -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true
```
Una vez ejecutado usando maven se compila y empaqueta el proyecto usando los siguientes comandos

```sh
$ mvn clean
$ mvn compile
$ mvn package
```
Por último para ejecutar el programa usamos maven
	
```sh
$ mvn exec:java
```
Este generará el archivo de salida en el mismo directorio.	 



