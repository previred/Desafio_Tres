# Descripción de la implementación

Se creo un metodo llamado valoresUf el cual es llamado a travez de un Main.

En el motodo valorUf, se consume la función getRango como se indica, esta retorna este retorna una estructura con un rango de fechas y un listado de valores de UF.

Se pasa el listado de valores de un HashSet a una lista y luego se recorre esta lista agregando los dias y valores faltantes a una lista auxiliar.

Una vez se tienen todos los dias faltantes con sus respectivos valores en la lista auxiliar, se cosolidan los datos entregados por la libreria mas los faltantes que se encuentran en la lista auxiliar.

Luego se genera un archivo .json con el resultado, en la carpeta "Archivo Salida"..

El metodo responde "Ok" en caso de excito o "Ocurrio un Error" mas el detalle de la excepcion en caso de fallo.


# Tecnología y librerías utilizadas

tecnologias Java, Maven.

se utiliza la libreria jackson-databind para la generacion del archivo .json


# Detalles de compilación y ejecución

El programa se ejecuta como una aplicacion Java.

Se deja un ejemplo del archivo de salida en la carpeta "Archivo Salida".
