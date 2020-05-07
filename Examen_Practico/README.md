# Exámen práctico Previred
Para este examen práctico, se desarrolla una funcionalidad que permite obtener  el valor de la UF para días pasados,
Los periodos de fechas pasadas son generadas de manera aleatoria.
 Como resultado final se obtiene un objeto de salida de tipo JSON, que muestra la fecha de inicio del periodo, fecha fin del periodo,
  y listado de dia consultados, cada uno con el valor de UF respectivo. 


## 1. Descripción

El desafío consiste en lo siguiente:

- Existe la siguiente librería en el directorio "lib\Generador_Datos_Desafio_Tres-1.0.0.jar" que se encuentra en este proyecto. 
  Este debe ser integrado en la solución.
  
- Este jar contiene 2 class que debe ser utilizadas para resolver el desafío.

-  La clase com.previred.desafio.tres.uf.Valores con el método getRango, este retorna una estructura con un rango de fechas
 y un listado de valores de UF.
 
- El método getRango retorna el objeto UFs, este contiene fecha de inicio, fecha de fin del rango, ademas contiene 
un set de UF que tiene como atributos de: valor de UF y la fecha de la UF.

- La lista de UF están dentro del rango de fechas (inicio y fin)
- La cantidad de valores para uf son máximo 100
- El listado entregado con los valores UF no son secuenciales (contiene laguna de valores) y no se encuentra ordenado
- La clase com.previred.desafio.tres.uf.DatosUf este es un singleton que contiene 2 métodos
- El método getUf retorna el valor UF para una fecha
- El método getUfs retorna una lista de valores de UF para un rango dado
- Consumir la función getRango de la clase com.previred.desafio.tres.uf.Valores
- Escribir un algoritmo para complementar los valores de UF para las fechas faltantes en la lista contenidas en la clase Ufs que retorna getRango
- Para complementar los valores de UF se pueden utilizar los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf.
- La lista de salida debe esta ordenada de forma descendente.
- Para la implementación debe elegir uno de los siguientes formatos de salida.


### 2. Desarrollador

  - Responsable: Victor Quezada Soto
  - Contacto: viquezad@gmail.com 
  - Fono: +56951351066

### 3. Librerías utilizadas

Se utiliza librería 'gson', version: '2.8.5 para la generación de salida en formato JSON
Se utiliza librería Generador_Datos_Desafio_Tres-1.0.0.jar



### 4. Compilación
Se realiza compilación mediante Gradle:

./gradle build


 
### 5. Versión 1.0.0 (07/05/2020)
 - Implementación desafío 3 
 