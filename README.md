# Desafio 3

## Instalacion

El proyecto posee las siguientes dependencias

* Maven 3.6
* JDK 11

Adicionalmente, la unica dependencia externa que posee el projecto es de `gson`

## Compilacion

Se debe registrar el jar en el repositorio maven

```
mvn install:install-file -Dfile="./lib/Generador_Datos_Desafio_Tres-1.0.0.jar" -DgroupId=com.previred.desafio.tres -DartifactId=base -Dversion=1.0.0 -Dpackaging=jar
```

Crear el jar con el sistema

```
mvn package
```

## Ejecucion

Se debe ejecutar el siguiente comando en la raiz del projecto

```
java -jar target/tres-0.0.1-SNAPSHOT.jar
```

Esto va a generar un archivo `output.json` en la raiz del proyecto con el resultado de la ejecucion

## Descripcion de la solucion

### Clase contenedora

La clase contenedora `UFRange` se encarga de recibir una instancia de `UFs`, de ella se extraen los datos de la fecha de inicio, fecha de termino y una lista de `UF`. Para las fechas se utilizan `LocalDate`, la cual permite manipular de mejor manera los tipos de dato `Date` que no poseen hora.

Para el calculo de las `UFs`, se utilizan los registros obtenidos por la instancia de `Ufs` como cache dentro de un `HashMap`, luego se itera desde la fecha de final hasta la fecha de inicio, si el dia se encuentra en el cache, se utiliza este, en caso contrario, se va a buscar el valor de la uf desde `DatosUf.getUf`, posteriormente el valor de la `UF` es ingresado a la lista de `UFs`.


### Serializacion

Para la serializacion se crean unos `Adapters` para que sean utilizados por la libreria `gson`. Los adapters son los siguientes:

* LocalDateAdapter: Clase encargada de serializar los datos de tipo `LocalDate` a un formato de fecha `YYYY-MM-DD`
* UFAdapter: Se debe reconfigurar la estructura de salida utilizada para serializar la `UF`  con las llaves `fecha` y `dato` con los formatos solicitados


