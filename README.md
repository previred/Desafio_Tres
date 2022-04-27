# DESAFIO 3

La siguiente app permite consumir una libreria de previred, obtener las uf

## Descripcion de la implementación:

Se realiza en base al requerimiento un aplicativo que permite consumir la libreria jar de previred, en el cual por medio de el metodo getRange(), se obtienen valores de uf y la fecha a la que pertenece, estos valores vienen desordenados y no secuenciales hay lagunas muchas veces entre ellos, en base al requerimiento entregado se entiende que en base al rango entregado hay que completar la secuencia hasta obtener todos los valores comprendidos en ese rango, lo que se hace es por medio del metodo getUfs, se obtienen todos los valores, luego  se valida que los encontrados previamente existan en la lista obtenida por rango luego se ordena de manera descendente y finalmente se exporta a un archivo con nombre valores.json que se encuentra en la carpeta files, la libreria esta en la carpeta lib y el detalle de la jerarquia y estructura de carpetas se detalla a continuacion, se agrega logger para ver el trace del aplicativo. se documenta el codigo, se agregan pruebas unitarias y se realiza un scan con sonarlint para asegurar la calidad de codigo.

## Tecnologia utilizada:
 - Springboot
 - Java 1.8
 - Libreria Gson version 2.9.0 (para la gestion de json)
 - Libreria commons-collections y commons-beansutils ( para el ordenamiento)
 - Libreria lof4j-springboot (para todo el registro, seguimiento)
 - Libreria springboot configuration-processor para los configurationProperties.
 - Libreria mockito y powermock-module (este incorpora alguno assert parael control de excepciones)
 - Maven para la gestion de repositorio. 
 - Libreria jar proporcionada por previred para el desafio(Generador_Datos_Desafio_Tres-1.0.0.jar)
 
## Pruebas unitarias y calidad de codigo:
 - Se realizan pruebas unitarias, y ejecucion de analisis para verificar cobertura, se cumple con los standares minimos de un 90% de cobertura del proyecto, se adjunta evidencia en carpeta /evidencia con el detalle de cobrtura.
 - Se realiza analisis con Plugin SonarLint, para reducir deuda tecnica. y cumplir con los standares minimos.

## Comenzando 🚀
La jerarquía de carpetas del proyecto es de la siguiente manera.
```
src/main/java
	cl.zco.desafio3 -> lanzador, configuraciones, proveedor de contexto
	cl.zco.desafio3.dto -> los dto que se utilizaran para la generacion con la estructura deseada
	cl.zco.desafio3.negocio -> Definicion logica del requerimiento
	cl.zco.desafio3.util-> las utilidades que se crearon para que el codigo estuviera limpio y fuera mantenible
src/test/java
	cl.zco.desafio3 -> prueba unitaria lanzador
	cl.zco.desafio3.negocio -> pruebas unitarias negocio	
	cl.zco.desafio3.util -> pruebas unitarias de las utilidades
lib/
	Generador_Datos_Desafio_Tres-1.0.0.jar
doc/
	javadoc > index.html 
resumen/cobertura
	index.html > aqui esta el detalle y resultado de junit + coverage
files/
	valores.json (archivo con la salida en json)
evidencia/
	imagen1 -> evidencia de cobertura junit
	imagen2, imagen3 y imagen4 -> ejecucion desde IDE
	imagen5, imagen6 y imagen7 -> ejecucion desde CMD
	mvn_install.log -> log de maven install build success
	valores.json -> archivo generado
jar/
	desafio3-0.0.1-SNAPSHOT.jar ->ultimo jar compilado con todas las librerias 
```

## Pre-requisitos. 📋

Java 1.8
Maven configurado

## Configuraciones para arrancar:  📦
En el problema si indicaba que solo mostraba 100 valores de UF, como la salida es mas extensa se crea una propiedad en el fichero desafio3.properties donde se puede colocar la cantidad maxima de UF de la lista en el json de salida, si se quiere cambiar simplemente se debe modificar el property, si se deja en valor 0 (cero) se mostrará todos los valores de UF dentro del rango.

Los datos entregados para desplegar la aplicación son:

### Arrancar directamente desde el jar: 
1. Ir a la consola (CMD)
2. Ir a la ruta donde esta el jar
3. Escribir esta sentencia-> java -jar desafio3-0.0.1-SNAPSHOT.jar
4. Aparecerá mensaje dando la bienvenida, indicando que es necesario tener una carpeta en C:\files\ porque en esa ubicacion se guardara el archivo 'valores.json'
5. Al terminar mostrará un mensaje indicando que el proceso a finalizado.

### Arranchar desde el ambiente de desarrollo (eclipse > SpringToolSuite4)
1. Sincronizar repositorio
2. Realizar un maven install
3. Ir a la clase 'Desafio3Application.java' boton derecho > Run As > Spring Boot App
4. Aparecerá mensaje dando la bienvenida, indicando que se creará el archivo json en la carpeta /files dentro del workspacecon el nombre solicitado 'valores.json'
5. Al terminar mostrará un mensaje indicando que el proceso a finalizado.

## Autor ✒️
**Patricio Angel Astorga Toledo** - *Ingeniería informática* - [mail] pastorgatoledo@gmail.com

---

Patricio Angel Astorga Toledo
pastorgatoledo@gmail.com
me enteré del desafio luego de hablar con Maria Paz Coloma
