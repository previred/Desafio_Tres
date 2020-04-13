# complemento-uf

> Complemento valores UF

## Implementación:

> Para la implementación se definieron y realizaron los siguientes Test:

- **UfsRepositoryTest**: para testear la clase **UfsRepository**, la que contiene la logica para obtener el listado de UF faltantes, igualmente es la que se encarga de realizar las peticiones a una BD si es que los datos ya no se obtuvieran los datos por medio del jar entregado.
- **DateRangeTest**: para testear la clase **DateRange**, la cual me entrega un listado con la cantidad de fechas en un rango especifico, el cual es utilizado como apoyo en la implementacion del **UfsRepository**.
- UtilsTest: para testear la clase **Utils** la cual es de apoyo para la conversión de fechas, tanto de Date a LocaldDate y viceversa.
- **UfsServiceTest**: para testear la clase **UfsService** y **UfsServiceImpl**, las que se encargan de conectar la capa del controller con la del repository.
- **UfsRestControllerTest**: para testear las peticiones de los servicios que provee la clase **UfsRestController**.
- **UfsConverterTest**: para testear la clase **UfsConverter**, la que se encarga de convertir una instancia de Ufs a un UfsDto, para normalizar la salida esperada del JSON.
- **UfConverterTest**: para testear la clase **UfConverter**, la que se encarga de convertir una instancia de Uf a un UfDto, para normalizar la salida esperada del JSON.

> En el algoritmo para resolver el ejercicio, realice los siguientes pasos:
1. La obtención de un listado con todas las fechas en un rango especifico por medio de la clase DateRange, en donde le paso las fecha de inicio y fin que entregaba la instancia que provee el metodo getRango() de la clase Valores. 
2. Luego construi por medio de stream un listado con las fechas que obtuve de cada una de las instancias de Uf que se encontraban en el Set inicial de la instancia obtenida de Ufs.
3. Luego realice la iteración por medio de un forEach del listado de fechas obtenidas por la clase DateRange en donde en cada iteración consultaba si existia la fecha dentro del listado obtenido en el paso 2, de no existir, obtuve el Uf por medio del metodo getUf() de la clase DatosUf, pasandole la fecha de la iteración, para posteriormente ir agregando la instancia de Uf obtenia al Set del Ufs.
4. Una vez realizado lo anterios, desde el servicio le solicito al repository una instancia de Ufs actualizado con las UF faltantes, en donde convertir esta instancia en un DTO para normalizar la salida esperada por el ejercicio.
5. Desde el controller se solicita al servicio dicho DTO para luego proveerlo por medio de una petición GET en formato JSON.
 
**Se incorporo el JAR Generador_Datos_Desafio_Tres-1.0.0.jar, al proyecto en una carpeta lib dentro de resources, donde configure el POM para obtener el recurso desde esta ruta**

## Tecnologias Utilizadas:

- Sprint Boot
- Sprint Boot Rest
- Sprint Boot Test
- Spring Boot Devtools
- Java 8
- JUnit
- Mockito
- Google Gson
- JsonPath
- Git
- GitFlow

## Compilación y Ejecución:

> Para ejecutar la aplicación en Spring Boot con Maven se deben realizar los siguientes comandos por cmd:

 - $ mvn clean – borra el directorio de salida (target)
 - $ mvn install – guarda el proyecto en el repositorio
 - $ mvn test – compila los test y los ejecuta
 - $ mvn compile – compila el proyecto y deja el resultado en target/classes
 - $ mvn package -P build-release – empaqueta el proyecto y lo dejará en taget/complemento-uf-0.0.1-SNAPSHOT.jar
 - $ java -cp taget/complemento-uf-0.0.1-SNAPSHOT.jar jar.ComplementoUfApplication

> Una vez iniciada la aplicación se debe consultar a las siguiente ruta:

> **localhost:8080/ufs**

> Tambien se puede importar el proyecto complemento-uf en algun IDE, como un proyecto Maven y ejecutar desde este mismo, iniciandose en **localhost:8080** y acceder a la ruta **/ufs**
