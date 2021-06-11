### Desafío 3: Complemento valores UF
#### Nivel 3

Existe la librería "lib\Generador_Datos_Desafio_Tres-1.0.0.jar" que se encuentra en el repositorio GitHub: https://github.com/previred/Desafio_Tres

Esta librería consta de dos clases:
	- com.previred.desafio.tres.uf.DatosUf (Singleton) que contiene dos métodos getUf y getUfs
	- com.previred.desafio.tres.uf.Valores que contiene al método getRango
	
El método getUf retorna el valor UF para una fecha

Ejemplo:

```json
{
   "fecha":"2021-12-31",
   "valor":2536.68
}
```

El método getUfs retorna una lista de valores de UF para un rango de fechas dado

Ejemplo:

```json
[
    {
      "fecha":"2014-01-04",
      "valor":"23321.57"
    },
    {
      "fecha":"2014-01-05",
      "valor":"23324.58"
    },

        :

    {
      "fecha":"2014-04-01",
      "valor":"23610.77"
    }
  ]
```

El método getRango retorna el siguiente objeto

Ejemplo:

```json
{
   "inicio":"2021-01-01",
   "fin":"2021-12-31",
   "Ufs":[
    {
      "fecha":"2021-01-04",
      "valor":"23321.57"
    },
    {
      "fecha":"2021-01-05",
      "valor":"23324.58"
    },

        :

    {
      "fecha":"2021-11-01",
      "valor":"23610.77"
    }
  ]    
}
```
	- La lista de UF están dentro del rango de fechas (inicio y fin)
	- La cantidad de valores para UF son máximo 100
	- El listado entregado con los valores UF no se encuentra ordenado y la secuencia no está completa 

El objetivo de este proyecto "valoresuf" es complementar los valores de la lista de UF, haciendo uso de los métodos getUf y getUfs.
Ordenar dicha lista por fecha de forma descendente y generar un archivo llamado "valores.json" con una estructura JSON como sigue:

Ejemplo:

```json
{
   "inicio":"2021-01-01",
   "fin":"2021-12-31",
   "Ufs":[
    {
      "fecha":"2021-12-31",
      "dato":"24.420,22"
    },

        :

    {
      "fecha":"2021-01-03",
      "dato":"23.327,59"
    },
    {
      "fecha":"2021-01-02",
      "dato":"23.324,58"
    },
    {
      "fecha":"2021-01-01",
      "dato":"23.321,57"
    }
  ]    
}
```

### Detalle de los sistemas
Java 8 Spring-Boot 2.4.6 Maven 3.6.3

### Compilar y ejecutar el proyecto
1.- Para compilar el proyecto se requiere Java y Maven instalado. 

2.- Antes de iniciar la ejecución de esta solución, debe realizar la descarga de la librería "lib\Generador_Datos_Desafio_Tres-1.0.0.jar" que se encuentra en el repositorio GitHub: https://github.com/previred/Desafio_Tres

3.- Instale la librería en el repositorio local haciendo uso de Maven, con el siguiente comando:

mvn install:install-file -Dfile=<ruta-de-libreria> -DgroupId=generador.datos -DartifactId=desafio-tres -Dversion=1.0.0 -Dpackaging=jar

#### Nota: Reemplace <ruta-de-libreria> por la ruta donde se encuentre la libreria: Generador_Datos_Desafio_Tres-1.0.0.jar
	
4.- Ingrese al directorio 'valoresuf' para ejecutar el siguiente comando Maven

mvn package

5.- Luego de compilar el proyecto ingrese al directorio "target" para ejecutar el siguiente comando Java

java -jar valoresuf-0.0.1.jar

6.- La solución genera un archivo llamado "valores.json" en el directorio "valoresuf/target"
