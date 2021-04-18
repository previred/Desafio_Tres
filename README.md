# Desafío 3: Complemento valores UF

## Nivel 3

### Objetivo

 > El desafío consiste en lo siguiente:

> - Existe la siguiente librería en el directorio "lib\Generador_Datos_Desafio_Tres-1.0.0.jar" que se encuentra en este proyecto. Este debe ser integrado en la solución.
> - Este jar contiene 2 class que debe ser utilizadas para resolver el desafío
>   - La clase com.previred.desafio.tres.uf.Valores con el método getRango, este retorna una estructura con un rango de fechas y un listado de valores de UF
>   - El método getRango retorna el objeto UFs, este contiene fecha de inicio, fecha de fin del rango, ademas contiene un set de UF que tiene como atributos de: valor de UF y la fecha de la UF
>   - La lista de UF están dentro del rango de fechas (inicio y fin)
>   - La cantidad de valores para uf son máximo 100
>   - El listado entregado con los valores UF no son secuenciales (contiene laguna de valores) y no se encuentra ordenado
>   - La clase com.previred.desafio.tres.uf.DatosUf este es un singleton que contiene 2 métodos
>   - El método getUf retorna el valor UF para una fecha
>   - El método getUfs retorna una lista de valores de UF para un rango dado
>   - Consumir la función getRango de la clase com.previred.desafio.tres.uf.Valores
>   - Escribir un algoritmo para complementar los valores de UF para las fechas faltantes en la lista contenidas en la clase Ufs que retorna getRango
>   - Para complementar los valores de UF se pueden utilizar los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf.
>   - La lista de salida debe esta ordenada de forma descendente.
>   - Para la implementación debe elegir uno de los siguientes formatos de salida.

### Librerías utilizadas

- IDE (STS) Spring Tool Suite 4
- Gradle
- Spring Boot 2.4.5
- Gson

### Instrucciones para la ejecución

1. Clonar el proyecto [Desafio_Tres](https://github.com/cristiansepulvedal/Desafio_Tres.git)
2. Abrir el proyecto con STS
3. Click Derecho sobre el proyecto
4. Run As -> Spring Boot App
5. El archivo queda alojado en el directorio C:/valores.json

### Datos de Contacto

- Nombre: Cristian Salvador Sepúlveda Lincheo
- Email: [c.sepulvedal.2016@gmail.com](mailto:c.sepulveda.2016@gmail.com)
- Origen Solicitud: Tech Consult (LinkedIn)