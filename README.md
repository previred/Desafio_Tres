### Tecnologías

Java 8 streams\
Gson\
Maven

### UFs faltantes

Se itera un rango de fechas creado a partir de las fechas de inicio y de fin.\
Se aplica un filtro solicitando sólo las fechas que no existen en el set de UFs.\
A partir de esta fecha se obtiene la UF utilizando el singleton DatosUf.\
Todas las UFs obtenidas se recolectan en una lista.

Finalmente se juntan los datos de la UFs que ya eran conocidas con las que se lograron determinar utilizando streams. Las UFs están ordenadas de forma descendente según su fecha.

### Archivo JSON
El archivo JSON se crea con la misma librería Gson que existe como dependencia en la librería proporcionada para el desafío.

Con GsonBuilder se realizan cambios sobre la seralización, en lugar de utilizar etiquetas en clases nuevas.

### Compilación
Lo mejor es instalar la dependencia entregada en el repositorio local:

mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=ruta_generador.jar

En el archivo POM del proyecto se declara la dependencia al generador. Así se pueden obtener la dependencias transitivas
y así se evitan dependencias externas.

mvn package -> empaqueta y con el plugin declarado en el POM de la solución, se agregan todas la dependencias en el archivo JAR, para así poder ser ejecutado por sí solo.

### Ejecución
java -jar .\desafio_tres-1.0.jar

El archivo JSON se genera en el directorio donde está ubicado el archivo JAR