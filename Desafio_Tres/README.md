# Detalle de la Solución al Desafío 3

- Descripción de la implementación

La aplicación se compone de tres clases y el correspondiente archivo pom.xml para un proyecto Maven.

DesafioTresApplication.java

ComplementoValoresUF.java

ReporteCsv.java

Todas las clases se encuentran ubicadas en el package com.previred.

La clase DesafioTresApplication.java es creada por el IDE al crear una aplicación Spring Boot.

En este caso se creó una aplicación de tipo consola.

En esta clase solo se crea una instancia de la clase ComplementoValoresUF a la cual se le manda el mensaje getRango().

En la clase ComplementoValoresUF se obtiene el objeto de tipo DatosUf que nos retorna el Singleton DatosUf y un objeto Ufs que se obtiene al solicitar un rango

a una instancia de la clase Valores. Estos objetos son usados para crear una instancia de la clase ReporteCsv al pasarlos como parámetros de su constructor.

La clase ReporteCsv es la que realiza el trabajo más importante de la aplicación que es ordenar de forma descendente por fecha las UFs e imprimirlas en un

archivo en formato csv.

En elarchivo pom.xml me ví obligado a incluir la invocación de la librería com.google.code.gson porque al integrar la librería Generador\_Datos\_Desafio\_Tres-1.0.0.jar no se actualizaron automáticamente sus dependencias.

Seguramente lo anterior se debe a que la integración no se hizo apropiadamente dado que no tengo manejo con Maven pues solo lo he usado a través del IDE STS lo que me hace totalmente transparente el uso de Maven.

- Tecnologías y librerías utilizadas

No se hace uso de ninguna librería especial salvo la librería Generador\_Datos\_Desafio\_Tres-1.0.0.jar y sus dependencias además de las librerías propias de java que fueron necesarias para la implementación de la solución.

Para el desarrollo del proyecto se usó el IDE STS (spring Tool Suite).

- Detalles de compilación y ejecución

La compilación fue realizada con el IDE STS para lo cual bastaba con crear un proyecto Spring Boot con Maven a través del IDE mencionado.

Para la ejecución

Directamente con el jar

Basta con ejecutar el jar con un simple dobleclick y se genera automáticamente el archivo valores.csv en la misma carpeta en que se encuentra el jar.

Con el archivo valoresuf.bat

Esta alternativa permite ver los eventuales mensajes de error que se puedan generar en la ejecución de la aplicación los cuales quedarán registrados en el

archivo log.txt que se genera y graba en el mismo directorio en que se encuentra valoresuf.bat.

El archivo log.txt agrega los logs de cada nueva ejecución; pero puede ser borrado para obtener un archivo de logs limpio en la siguiente ejecución.

El archivo valoresuf.bat solo registra el siguiente comando:

&quot;C:\Program Files\Java\jre1.8.0\_144\bin\java.exe&quot; -jar Desafio\_Tres-1.jar \&gt;\&gt; log.txt

Habría que modificar el path que apunta a java.exe correspondiente al ambiente de ejecución.

Tanto el archivo de log como el archivo valores.csv se generan en la misma carpeta en que se encuentra valoresuf.bat.