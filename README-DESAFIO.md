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

### Formato 1

Crear un archivo CSV con todos los datos calculados, las columnas deben contemplar el siguiente formato:
 - La primera columna representa el tipo, tipo 1 cabecera y tipo 2 es detalle de las UFs
 - Para las filas de tipo 1 el formato es fecha de inicio y fecha de fin
 - Para las filas de tipo 2 el formato es fecha uf y valor uf
 
 *Ejemplo*
```
1; 2014-04-01; 2015-03-05
2; 2014-01-04; 23.321,57
2; 2014-01-05; 23.324,58
2; 2014-01-06; 23.327,58
2; 2014-01-07; 23.330,58
2; 2014-01-08; 23.333,59
2; 2014-01-09; 23.336,59
    :
2; 2014-04-01; 23.610,77
```

### Formato 2
Crear un archivo XML que contenga el siguiente formato:
 - Debe contener un tag general llamado valores
 - Dentro de tag valores se deben crear los tag inicio, fin y UFs
 - El tag inicio debe contener la fecha de inicio recibida
 - El tag fin debe contener la fecha de fin recibida
 - El tag UFs debe contener un lista de tag UF con el siguiente formato
 - El tag UF debe contener el tag fecha con la fecha inicial y el tag dato con el valor de la UF

*Ejemplo*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<valores>
  <inicio>2014-04-01</inicio>
  <fin>2015-03-05</fin>
  <UFs>
    <UF>
      <fecha>2014-01-04</fecha>
      <dato>23.321,57</dato>
    </UF>
    <UF>
      <fecha>2014-01-05</fecha>
      <dato>23.324,58</dato>
    </UF>
    <UF>
      <fecha>2014-01-06</fecha>
      <dato>23.327,58</dato>
    </UF>
    <UF>
      <fecha>2014-01-07</fecha>
      <dato>23.330,58</dato>
    </UF>
    <UF>
      <fecha>2014-01-08</fecha>
      <dato>23.333,59</dato>
    </UF>
    <UF>
      <fecha>2014-01-09</fecha>
      <dato>23.336,59</dato>
    </UF>

        :

    <UF>
      <fecha>2014-04-01</fecha>
      <dato>23.610,77</dato>
    </UF>
  </UFs>
</valores>
```

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

 - Se deben implementar las soluciones en Java (con maven, gradle u otro).
 - La solución debe ser enviada vía un pull request a este repositorio.
 - La solución debe contener un README.md con:
   - Descripción de la implementación
   - Tecnología y librerías utilizadas
   - Detalles de compilación y ejecución
 - El archivo de salida debe tener como nombre “valores” con su respectiva extensión y debe ser entregado junto con la solución
 - Por ultimo en el detalle del commit debes indicar los siguientes datos:
   - Nombre Completo.
   - Correo Electrónico.
   - Vía por la que te entérate del desafío. Estas pueden ser: Empresa de outsourcing (indicar cuál), twitter, LinkedIn, etc.
 
`NOTA`: Todos los pull requests serán rechazados, esto no quiere decir que ha sido rechazada la solución.
