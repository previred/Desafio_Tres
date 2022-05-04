## Solución

### Primer paso

Se utiliza el método getUfs con las fechas de inicio y termino que genera el método getRango para agregar los valores de las fechas restantes.
Considerando que el Set es para collections sin duplicaciones solo habrá valores de fechas distintas.

### Segundo paso

Se escogió el formato de json. Se traspasa a ese formato usando JSONObject de la dependencia de json-simple.
Se ordenó de manera descendente usando el método estático sort de la clase Collections del package java.util, se consideró que usar la fecha en tipo de String para el ordenamiento

### Tercer paso

se utilizo la clase FileWriter de java.io para la creación del archivo pasando el objeto json como un string

## Tecnologías y librerías utilizadas

Dentro del pom.xml se agregaron solo una dependencia, que es la siguiente:

```xml
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.9.0</version>
        </dependency>
```
Dado que lo pidió el proyecto al momento de agregar la librería y ejecutar el código para probar los métodos a usar en él desafió, y la vez que se usó para armar json que se imprimió en el archivo final.

Además se usaron librerías internas del jdk de java como java.io para el manejo del archivo, java.util para el almacenamiento y procesamiento en ejecución como son las clases de ArrayList y Collections respectivamente.

## Detalles de la compilación y ejecución

En cuanto a la compilación y ejecución sé utilizó el openjdk 11 de Amazon Corretto a través del IDE IntelliJ IDEA.
