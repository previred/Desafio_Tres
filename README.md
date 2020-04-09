# Desafio técnico - PREVIRED

_Descripción del proyecto_

## Comenzando 🚀

_Se realizaron 3 funcionalidades, dado que comencé primero mostrando los resultados en pantalla con salida de JSON, y el procedimiento que entendí primero fue que había que rescatar las UF que existían en el rango de fechas random que te arrojaba el servicio y luego con la fecha de inicio y fin del rango de fechas completar las lagunas.  Luego por el recorrido que le apliqué al algoritmo noté que se demoraba un par de segundos más, y pensé que quizás podría hacer un método alternativo, dado que al final de cuentas lo que se quería saber era de igual forma la cantidad de datos integros en el rango de fechas provisto y esa funcionalidad resultó ser más rápida.  Al último me quedé en la duda si sería permitido mostrar el tema en pantalla porque hablaba de archivo JSON, entonces hice una tercera funcionalidad, la cual era que descargaba finalmente la data en CSV como se indica en la tarea.
Finalmente como no se especificaba ninguna limitante adicional, generé una página index simple sin diseño, sólo con la idea de mostrar las 3 funcionalidades que realicé segmentadas_

Mira **Deployment** para conocer como desplegar el proyecto.


## Construido con 🛠️

_El proyecto fue desarrollado con Spring Boot_

_Las librerías que ocupé principales fueron_

* [SpringBoot](https://spring.io/projects/spring-boot) - Como framework web
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Thymeleaf](https://www.thymeleaf.org/) - Para la vista web sencilla que se utilizó
* [Gson](https://maven.apache.org/) - Para la serialización/deserialización en Java de archivos formato JSON
* [SuperCsv](http://super-csv.github.io/super-csv/index.html) - Para la gestión y creación del CSV de salida

_Los métodos en el servicio implementado viene con java doc para explicar cada funcionalidad_

### Instalación 🔧

_Se puede ejecutar a través de un IDE como Eclipse.  Importar el proyecto como un proyecto maven existente, luego que se termine de exportar en las opciones del proyecto, en la sección MAVEN se selecciona la opción update project y se actualizarán las dependencias y todo lo necesario para poder ejecutarlo_

### Ejecución ⚙️

_Si se ejecuta desde el IDE Eclipse, una vez que se instaló todo, se puede seleccionar la vista JAVA y debería mostrar una sección de herramientas de desarrollado de spring boot, que permite lanzar el servidor con el boot dashboard_
_La otra opción es por línea de comando, ejecutar el comando:_

```
java -jar ruta_archivo.jar  (previred-0.0.1-SNAPSHOT.jar)

Ya que viene con un tomcat embebido.
```

## Detalles de compilación y despliegue 📦

_Para ejecutar la compilación tuve que que agregar el JAR enviado a los archivos maven locales del proyecto y configurar la ruta del pom para que definiera la ruta local para los archivos necesarios.  Se definieron los datos respectivos del archivo en la dependencia y luego se ejecutó el comando maven_

```
mvn install:install-file -Dfile=lib/Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=com.previred.desafio.tres -DartifactId=Generador_Datos_Desafio_Tres -Dversion=1.0.0 -Dpackaging=jar

Sin embargo no me quedó compilado todo correctamente, pero si al menos se incorporó el JAR y en las herramientas del IDE apliqué "run as maven install" y comenzó a descargar dependencias y procesar.  Luego probé el JAR recién creado y levantó sin problemas
```


## Autor ✒️

* **Benjamín Enrique Silva López**
* **benjamin@nexoit.com** 
* **Fui contactado y entrevistado inicialmente por Leonardo Miranda (Reclutador IT de Tech Consult)** 
