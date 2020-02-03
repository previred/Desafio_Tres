# Desafio Tres Previred


## Descripcion de la Implementacion
El proposito de esta implementacion fue complementar la lista de ufs dado por el metodo getRango de la clase valores. Para ello, se creó la clase UfDataAgreggator, cuya principal función es complementar los valores de las ufs en las fechas faltantes y ademas, cosultar para dichas fechas el valor en la clase DatosUf. Para la salida del archivo en el formato adecuado se ocupó la interfaz GenerateOutputFile y la implementacion en especifica para este caso será la clase GenerateJsonFile.


## Tecnologias y liberias Utilizadas

- Para la gestión de depencias se utlizo maven. 
- como lenguaje de programación se utilizo java 8.
- Se utilizó la libreria de GSON para el formato de salida JSON. 


## Detalles de compilación y ejecución

Para ejecutar la applicación: mvn spring-boot:run sobre el directorio donde se encuentra el aplicativo