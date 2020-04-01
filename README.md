1.- Descripción de la implementación

Se rescatan los datos proveidos por la clase Valores y DatosUf (ambas forman parte del Jar proveido por ustedes)
Dentro de los datos obtenidos, se rescata la fecha de inicio y fecha de fin del periodo a procesar y un set con fechas y valores de uf.
Se procede a convertir el objeto Set y transformarlo en un objeto de tipo List, esto para trabajar con objetos iguales, ya que la lista que se obtiene de la clase DatosUf es de tipo List.
Luego se procede a realizar el ordenamiendo de la lista en forma descendente, según las especificaciones.
Se procede a obtener los datos de la clase DatosUf, para el periodo (fecha inicio y fecha fin) que se obtuvo desde la clase Valores.
Teniendo ambas listas, se procede a realizar una comparación con el fin de encontrar los datos que faltantes dentro de la lista obtenida de la clase Valores.
Una vez identificados los datos faltantes, se proceden a agregar a la lista obtenida desde la clase Valores.
Al finalizar el recorrido de ambas listas, la lista "inicial" (obtenida desde la clase Valores), se encuentra completa con los datos restantes, obtenidos de la segunda lista (lista clase DatosUf).
Se procede nuevamente a ordenar la lista "inicial" (ya completa con los valores del periodo) en forma descendente.
Luego se procede a crear una lista final sólo con los 100 primeros registros obtenidos de la lista "inicial" (ordenados de forma descendente), para conformar el cuerpo del archivo XML de salida.
Finalmente, se procede a generar el archivo XML de salida, según lo indicado en las especificaciones.

Nota: Todo lo anteriormente detallado, se encuentra comentado en el código fuente.
 
2.- Tecnología y librerías utilizadas

Tecnologías:

Java versión 1.8
Springboot versión 2.2.6
Gradle

Librerías externas:

Generador_Datos_Desafio_Tres-1.0.0.jar - Librería proporcionada por ustedes.
gson-2.8.2.jar - Esta librería fue necesaria incluirla para compilar el fuente proporcionado por ustedes.

También se utilizaron librerías contenidas en la versión Java 1.8
 
3.- Detalle de la compilación y ejecución
 
Tanto la compilación como la ejecución se realizaron por medio de Eclipse.
Se ejecuta la aplicación como una Spring Boot App