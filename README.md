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



###Descripción de la implementación
La implementación de la solución hace uso de las clases Collections, aprovechando la clase Set en que vienen las UFs. Luego de realizar el llamado a getRango(),  se utilizan las fechas de Inicio y Fin que vienen en la respuesta para obtener la totalidad de Ufs. Luego se agrega la Lista de UFs (Collection List) al Set de UFs obtenido anteriormente (Collection Set de retorno de getRango()), aprovechando que en la Collection Set no existen duplicados, lo que agrega las fechas faltantes al Set sin duplicar UFs.
Una vez que se cuenta con este resultado, se debe realizar el ordenamiento y formateo de la respuesta. Para esto, primero se obtiene una List de UFs desde el Set, luego se ordena utilizando un Comparator que utiliza el método getFecha de la clase Uf, para finalmente agregarle una reversa con reversed().
Como último paso, se utilizan dos clases para entregar el formato adecuado en valores de tipo String (UfOutputJsonFormat y ListaUfsOutputJson) y dos clases del tipo Format: SimpleDateFormat y DecimalFormat, para entregar la fecha y el valor de la UF en el formato correcto, respectivamente.  

###Tecnología y librerías utilizadas
La solución se implementó en lenguaje Java utilizando el framework Spring Boot. Para la gestión de dependencias se utiliza Gradle.
Las librerías incorporadas en el framework Spring Boot permiten el uso de log en el proyecto sin agregar dependencias adicionales, por ejemplo.

###Detalles de compilación y ejecución
Dentro del commit se incorpora el jar generado de ejecutar el goal jar de Gradle. Se encuentra en build/libs/uf-0.0.1-SNAPSHOT.jar. Este archivo puede ser utilizado para levantar el servidor local con el microservicio REST utilizando el comando 
```cmd
java -jar  build/libs/uf-0.0.1-SNAPSHOT.jar (si se está en el directorio raíz del proyecto)
java -jar  uf-0.0.1-SNAPSHOT.jar (si se está dentro del directorio build/libs)
```

Una vez el servidor Tomcat ya ha levantado localmente, se debe ir a la URL localhost utilizando el puerto informado en el log de la aplicación (por defecto se levanta en el puerto 8080). Después de localhost:puerto, se debe agregar /api/uf/completo para ver el resultado ya complementado, ordenado y formateado; o /api/uf/rango para ver el listado de UFs antes de completar, ordenar y formatear. 

```html
http://localhost:8080/api/uf/completo
http://localhost:8080/api/uf/rango
```
