### Desafío 3: Complemento valores UF
#### Nivel 3

### Descripción
El directorio "Desafio_Tres/lib" contiene la librería "Generador_Datos_Desafio_Tres-1.0.0.jar" 

Esta librería contiene dos clases:

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

El método getRango retorna la siguiente estructura de datos

Ejemplo:

```json
{
   "inicio":"2021-11-01",
   "fin":"2021-12-31",
   "Ufs":[
    {
      "fecha":"2021-11-04",
      "valor":"23321.57"
    },
    {
      "fecha":"2021-11-05",
      "valor":"23324.58"
    },

        :

    {
      "fecha":"2021-12-21",
      "valor":"23610.77"
    }
  ]    
}
```
	- La lista de UF están dentro del rango de fechas (inicio y fin)
	- La cantidad de valores para UF son máximo 100
	- El listado entregado con los valores UF no se encuentra ordenado y la secuencia no está completa 

El objetivo del proyecto "Desafio_Tres" es hacer uso de la librería Generador_Datos_Desafio_Tres-1.0.0.jar. Generar y complementar los valores de la lista de UF haciendo uso de los métodos getUf y getUfs.
Ordenar dicha lista por fecha de forma descendente y generar un archivo llamado "valores.json" con una estructura JSON como sigue:

Ejemplo:

```json
{
   "inicio":"2021-11-01",
   "fin":"2021-12-31",
   "Ufs":[
    {
      "fecha":"2021-12-31",
      "dato":"24.420,22"
    },

        :

    {
      "fecha":"2021-11-03",
      "dato":"23.327,59"
    },
    {
      "fecha":"2021-11-02",
      "dato":"23.324,58"
    },
    {
      "fecha":"2021-11-01",
      "dato":"23.321,57"
    }
  ]    
}
```
####Nota: En el directorio '/Desafio_Tres' se encuentra una muestra del archivo "valores.json"

### Detalle de los sistemas
 - Java 8
 - Spring-Boot 2.5.1 
 - Maven 3.6.3

### Compilar y ejecutar el proyecto
1.- Para compilar el proyecto se requiere Java y Maven instalado.

2.- Abra una terminal o consola

3.- Ingrese al directorio 'Desafio_Tres/lib'

4.- Instale la librería "Generador_Datos_Desafio_Tres-1.0.0.jar" en el repositorio Maven local haciendo uso del siguiente comando:

mvn install:install-file -Dfile=Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=generador.datos -DartifactId=desafio-tres -Dversion=1.0.0 -Dpackaging=jar
	
5.- Una vez completado el paso anterior, ingrese al directorio 'Desafio_Tres' para compilar el proyecto ejecutando el siguiente comando de Maven

mvn package

6.- Luego de compilar el proyecto ingrese al directorio "Desafio_Tres/target" para ejecutar el siguiente comando Java (esto ejecutara la aplicacion)

java -jar respinoza_previred_valoresuf-0.0.1.jar

7.- Una vez culminada, la aplicacion genera un archivo llamado "valores.json" en el directorio "Desafio_Tres/target"
