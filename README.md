# Descripción de la solución

Primera parte de la solución consistió en obtener los datos de la clase Valores, esta fue transformada a un map
 cuya llave era la fecha a buscar. Luego se itera desde la fecha inicial hasta la final y se chequea si en esa fecha existe el valor de la UF. Si no existe entonces se utiliza el método DatosUf.getUf() para obtener el valor de la UF para ese día en particular. Si existe el valor, entonces se utiliza el lo que está guardado en el map.
Todos los valores de la UF encontrados son agregados a una lista que después es ordenada en forma descendiente. Para esto se usa un comparador customizado donde el criterio de orden viene dado por la fecha del valor de la UF.

Con la lista de todos los valores completada se utilizó una biblioteca externa para generar el archivo csv solicitado.


# Tecnología utilizada
Junit: Se utilizó junit para generar test unitarios que probaran cada parte de la aplicación antes de integrarla.

commons-csv: Esta biblioteca se utilizó para generar el archivo csv

gson: No se utiliza directamente esta biblioteca pero es necesaria para la clase DatosUf.


# Detalles de compilación y ejecución

Se requiere de maven 3 y java 8 para construir y ejecutar la aplicación.

Para construir la aplicación basta solo con ejecutar: 
`mvn package`

En caso de que se desee correr los tests se debe ejecutar:
`mvn test`

Para ejecutar la aplicación, se debe construir primero y luego ejecutar:
`mvn exec:java -Dexec.mainClass="com.previred.desafio.DesafioMain" -Dexec.classpathScope=compile`

