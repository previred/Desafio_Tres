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

gson: No se utiliza directamente esta biblioteca pero es necesaria para la clase DatosUf.


# Detalles de compilación y ejecución

Para construir la aplicacion basta solo con ejecutar: 
mvn package

En caso de que se desee correr los tests se debe ejecutar:
mvn test 

Para ejecutar la aplicacion, se debe construir primero y luego ejecutar:
mvn exec:java -Dexec.mainClass="com.previred.desafio.DesafioMain" -Dexec.classpathScope=compile

