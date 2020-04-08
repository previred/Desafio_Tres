# Desarrollo Desafío Tres Previred - Complemento valores UF

El desafío consiste en implementar una solución que obtenga valores de UF de un rango de fechas, que detecte las lagunas de fechas y rellene los valores de la UF para los dias faltantes, presentando finalmente todos los datos en un archivo valores.csv, Para esto se debió utilizar una libreria especial de previred que contenía las funciones necesarias para el desarrollo.

### Prerequisitos

Se necesita bajar la librería del directorio "lib\Generador_datos_Desafio_Tres-1.0.0.jar", tener algun editor de codigos de preferencia con maven instalado y funcionando (en mi caso utilicé Netbeans con Java 8). 

```
Para bajar la librería deberá instalar Git para su sistema operativo. Para esto, desde gitHub se debe hacer un fork del proyecto, para luego bajarlo a disco duro local.
Ubicar directorio en el que se quiere trabajar y con el click derecho del mouse, seleccionar opción "Git Bash Here", usar comando git clone y poner la URL de GitHub provista desde el fork ya realizado.

https://github.com/cartapia-GitHub/Desafio_Tres.git
```

### Instalando

Al hacer un "Git Bash Here" utilizando git clone con la Url antes mencionada, se clonará el proyecto inicial, con las librerias necesarias. Luego crear nuevo proyecto en editor preferido. Importar libreria en el POM.

```
        <dependency>
            <groupId>com.previred.desafio.tres</groupId>
            <artifactId>Generador_Datos_Desafio_Tres</artifactId>
            <version>1.0.0</version>
        </dependency>
```

Luego hacer "Manual Install" sobre la dependencia recien agregada, en la ventana de Proyectos (En mi caso utilicé Netbeans para desarrollar)

```
Se instalará la librería y podrán importarse las clases necesarias para el desarrollo.
```

## Descripción de la Implementación

La implementación está hecha en una sola clase llamada PreviredApp.java, en ella se hace el llamado a la función getRango, de la clase Valores. A continuación las lineas de importación.
```
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
```
A continuación las lineas donde se llaman las funciones mencionadas

```
    Valores val = new Valores();
    Ufs rango = val.getRango();
    DatosUf ufData = DatosUf.getInstance();
```
se recibe la información en forma de Set<Uf>, se pasa a una Lista y se ordena por fecha en forma ascendente:

```
    Set<Uf> ufSet = rango.getUfs();
    List<Uf> ufSetSorted = ufSet.stream().collect(Collectors.toList());
    Collections.sort(ufSetSorted, (o1, o2) -> o1.getFecha().compareTo(o2.getFecha()));
```
Una vez hecho esto, se recorre la lista comparando elemento actual con elemento siguiente y calculando cantidad de dias de diferencia, si esta cantidad es mayor que 1, se procede a extraer los datos en ese rango de fechas faltantes

```
   Uf element= new Uf();
    Uf element2=new Uf();
    List<Uf> ufMissing = new ArrayList();
    List<Uf> totalMissing = new ArrayList();
    for(int i=0; i<ufSetSorted.size()-1; i++){
         element = (Uf)ufSetSorted.get(i);
        i++;
        element2 = (Uf)ufSetSorted.get(i); 
        if(i==ufSetSorted.size()){
            
        }
        else{
            i--;
        }
        
        dias = (int) ((element2.getFecha().getTime()- element.getFecha().getTime())/86400000);
        if(dias>1){
            ufMissing = ufData.getUfs(element.getFecha(), element2.getFecha());
            totalMissing.addAll(ufMissing);
            //System.out.println("Hay un salto de "+dias+" en la info, entre fechas : "+element.getFecha()+" y "+element2.getFecha() );
        }      
    }
```
dejando para cada rango de fechas faltantes, los valores en una lista llamada ufMissing, la cual se iba copiando a la lista auxiliar totalMissing, la cual se agrega al final del recorrido de la lista inicial. Una vez echo esto, se eliminan los elementos duplicados, y se ordena nuevamente, esta vez en formato descendente.

```
    ufSetSorted.addAll(totalMissing);
    ufSetSorted = ufSetSorted.stream().distinct().collect(Collectors.toList());
    Collections.sort(ufSetSorted, (o1,o2) -> o1.getFecha().compareTo(o2.getFecha()));
    Collections.reverse(ufSetSorted);
```
luego se crea el archivo, recorriendo la lista y cuidando el formato, para esto se creó el metodo createFile, que recibe una lista y genera el archivo con el nombre deseado y el formato especificado anteriormente (en este método se utiliza un StringBuffer y un BufferedWritter)
```
    createFile(ufSetSorted);
```

### Tecnología y Librerías Utilizadas

Como se mencionó anteriormente, se utilizó la librería Generador_datos_Desafio_Tres-1.0.0.jar encontrada en el directorio lib y se importó en maven, a través de la edición del archivo pom.xml y de manual install del jar. La solución fue programada en Java.

```
    Netbeans IDE 8.2
	Maven 2.5
	Java 8.2
	Git 2.26.0
```

### Detalles de la Compilación y Ejecución

La clase PreviredApp.java tiene una función main, que hace el llamado a la ejecución de los métodos y funciones implementados, dando como resultado la creación del archivo valores.csv que es un archivo con el siguiente formato:

```
La primera columna representa el tipo, tipo 1 cabecera y tipo 2 es detalle de las UFs
Para las filas de tipo 1 el formato es fecha de inicio y fecha de fin
Para las filas de tipo 2 el formato es fecha uf y valor uf

ejemplo:

1; 2014-04-01; 2015-03-05
2; 2014-01-04; 23.321,57
2; 2014-01-05; 23.324,58
2; 2014-01-06; 23.327,58
```
sólo se debe compilar la clase y se crea el archivo con los datos, el cual es creado en el mismo directorio donde se encuentra la clase PreviredApp.java

