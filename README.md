# Descripción de la solucion

Primera parte de la solucion consistio en obtener los datos de la clase Valores, esta fue transformada a un map cuya llave era 
la fecha a buscar. Luego se itera desde la fecha inicial hasta la final y se chequea si en esa fecha existe el valor de la UF.
Si no existe entonces se utliza el metodo DatosUf.getUf() para obtener el valor de la UF para ese dia en particular. Si existe
el valor, entonces se utiliza el lo que esta guardado en el map.
Todos los valores de la UF encontrados son agregados a una lista que despues es ordenada en forma descendiente. Para esto se usa
un comparador customizado donde el criterio de orden viene dado por la fecha del valor de la UF.

Con la lista de todos los valores completada se utilizo una biblioteca externa para generar el archivo csv solicitado.


# Tecnologia utilizada
Junit: Se utilizo junit para generar test unitarios que probaran cada parte de la aplicacion antes de integrarla.

commons-csv: Esta biblioteca se utilizo para generar el archivo csv

# Detalles de compilación y ejecución

Para construir la aplicacion basta solo con ejecutar: 
mvn package

En caso de que se desee correr los tests se debe ejecutar:
mvn test 

Para ejecutar la aplicacion, se debe construir primero y luego ejecutar:
mvn exec:java -Dexec.mainClass="com.previred.desafio.DesafioMain" -Dexec.classpathScope=compile








# Detalles de compilación y ejecución
    Para el uso de el jar del repositorio enviado utilice la ayuda de un plugin de maven
    que permite agregarlo como repositorio local y de esa forma utilizarlo dentro del proyecto, 
    lo cual esta configurado dentro del pom.xml.
    Utilicé otro plugin para poder  definir la clase principal del proyecto y de esa forma
    no tener problemas para ejecutar el jar, este plugin al final genera un fat jar con las 
    dependencias necesarias llamado : desafio-1.0-SNAPSHOT-jar-with-dependencies.jar, el cual es 
    el que se debe ejecutar.

    Se debe ejecutar mvn clean package para regenerar el jar.

    El fichero de salida se encuentra dentro de la raiz del proyecto llamado valores.json