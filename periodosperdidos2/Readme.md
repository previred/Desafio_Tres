# Desafío 3: Complemento valores UF
El desafío consiste en lo siguiente:
- Existe la siguiente librería en el directorio "lib\Generador_Datos_Desafio_Tres-1.0.0.jar" que se encuentra en este proyecto. Este debe ser integrado en la solución.
- Este jar contiene 2 class que debe ser utilizadas para resolver el desafío
    - La clase com.previred.desafio.tres.uf.Valores con el método getRango, este retorna una estructura con un rango de fechas y un listado de valores de UF
    - El método getRango retorna el objeto UFs, este contiene fecha de inicio, fecha de fin del rango, ademas contiene un set de UF que tiene como atributos de: valor de UF y la fecha de la UF
        - La lista de UF están dentro del rango de fechas (inicio y fin)
        - La cantidad de valores para uf son máximo 100
        - El listado entregado con los valores UF no son secuenciales (contiene laguna de valores) y no se encuentra ordenado
    - La clase com.previred.desafio.tres.uf.DatosUf este es un singleton que contiene 2 métodos
        - El método getUf retorna el valor UF para una fecha
        - El método getUfs retorna una lista de valores de UF para un rango dado


1.  Consumir la función getRango de la clase com.previred.desafio.tres.uf.Valores
2.  Escribir un algoritmo para complementar los valores de UF para las fechas faltantes en la lista contenidas en la clase Ufs que retorna getRango
3.  Para complementar los valores de UF se pueden utilizar los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf.
4.  La lista de salida debe esta ordenada de forma descendente.
5.  Para la implementación debe elegir uno de los siguientes formatos de salida.

### Formato 3
Crear un archivo JSON que contenga el siguiente formato:
- Debe contener la fecha de inicio “inicio”
- Debe contener la fecha de fin “fin”
- La lista de valores de “UFs” con los valores de fecha de uf “fecha” y valor de la uf “dato”

*Ejemplo*

```json
{
  "inicio":"2014-04-01",
  "fin":"2015-03-05",
  "UFs":[
    {
      "fecha":"2014-01-04",
      "dato":"23.321,57"
    },
    {
      "fecha":"2014-01-05",
      "dato":"23.324,58"
    },
    {
      "fecha":"2014-01-06",
      "dato":"23.327,58"
    },
    {
      "fecha":"2014-01-07",
      "dato":"23.330,58"
    },
    {
      "fecha":"2014-01-08",
      "dato":"23.333,59"
    },
    {
      "fecha":"2014-01-09",
      "dato":"23.336,59"
    },

        :

    {
      "fecha":"2014-04-01",
      "dato":"23.610,77"
    }
  ]
}
```
Implementacion:
-   Se utilizo Java 8, con las librerias de Gson de google, Log4j y la libreria proporcionada para el ejercicio

## Instalacion:

Para la instalacion se debe hacer lo siguiente (Si desea cambiar el puerto lo puede hacer en el archivo application.properties):

```
    mvn clean install
```
Esto generara los artefactos en la carpeta /target

## Ejecucion:

Para la Ejecucion se debe hacer lo siguiente:

```
    java -jar periodosperdidos2-1.0-SNAPSHOT.jar
```