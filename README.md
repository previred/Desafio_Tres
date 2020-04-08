### Descripción de la solución.
La solución consitio en:
 - Inicialmente obtener los valores de getRango().
 - Posterior a ello, se procede a ordernar los valores de getRango() de más antiguo
  al más nuevo con el objetivo de tener una mejor visión de las fechas faltantes.
  - Se genera una variable auxiliar que permite saber el ultimo valor recorrido para
  posteriormente comenzar a validar cual es la diferencia en días entre las 2 fechas y se guardan estos nuevos datos
  en una lista independiente (X).
  - Una vez se detecta la diferencia en fecha se procese a obtener la información de UF de esas 2 fechas y se van
  añadiendo en la lista (X)
  - Una vez se realiza dicha logica, se mergean los datos de la lista obtenida de getRango() y la lista nueva (X), 
  y se procede al ordenamiento descendiente.
     
### Tecnología y librerías utilizadas
Se utilizo:
 - Spring boot 2.2.6
 - Spring-boot-starter-web
 - Lombok
 - Java Util.
 - Java Time.
 
### Detalles de compilación y ejecución
Para desplegar utilizar la siguiente ejecución:
 - mvn spring-boot:run.
 - Y luego consumir la siguiente URL: http://localhost:8080/Valores