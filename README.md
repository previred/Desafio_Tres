# Desafío 3: Complemento valores UF #


### Descripción de la implementación ###

La estructura utilizada es la siguiente:

src/main/java
* **com.previred.desafio.tres**
* **com.previred.desafio.tres.dto**
* **com.previred.desafio.tres.service**


src/test/java
* **com.previred.desafio.tres.service**


Donde la base, contiene la clase GenerarListaApplication, que permite ejecutar la aplicación.
En dto, se encuentran los objetos que permite generar el archivo de salida de la aplicación.
En service, se encuentra la implementación de negocio asociada a la generación del archivo json.


### Tecnología y librerías utilizadas ###

Para esta implementación, se utilizó: 
* java 8
* maven 3.6.3
* gson 2.8.6
* junit 4.13
* mockito 1.10.19



### Detalles de compilación y ejecución ###

Para compilar esta aplicación, es necesario ejecutar en el directorio raíz de la misma, la
siguiente instrucción:

```mvn clean compile package```

Al ejecutar lo anterior, se generará el archivo **Desafio_Tres-0.0.1-SNAPSHOT.jar**

Una vez compilado, se pueden ejecutar las pruebas con la siguiente instrucción:

```mvn test -Dtest=ComplementoValoresServiceTests```


Por último, para ejecutar la aplicación, se debe ejecutar la siguiente instrucción:

```java -jar target\Desafio_Tres-0.0.1-SNAPSHOT.jar```

