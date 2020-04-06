Detalle de la implentacion del desafio “Complemento de Valores UF”

Se construyo la aplicación con framework spring con arquitectura MVC
Para resolver los desafios se consumio el jar que fue proporcionado por uds.
Priemro se consume el getRango el cual trae una lista de valores de forma aleatoria 
Para complementar los valores que faltan en la estructura:
Primero = se ordena el set de valores en forma ascendente, esto para obtener el primer y ultimo valor de fechas utilizando Collection.sort
Segundo = se consume el metodo getUfs con parametros de fecha inicio y hasta, con esto obtenemos el set completo entre esas fechas.
Como es una implementacion web se tomo la decisión de implementar una vista que muestre este set de fechas y valores, el cual de despliega en orden descendente.
Para la vizaulaizacion dinamica de la tabla se utilizo thymeleaf, y para mejorar la vista el framework boostrap.
Para la contruccion de la  descarga de documentos se implementaron 3 botones, uno para cada tipo de archivo (CSV, JSON, XML). Utilizando el getUfs (inicio, fin).

Librerias Utilizadas
Opencsv
Boostrap
Jackson

IDE
Spring Tool Suite 4

PD:el jar proporcinado por uds se agrego en pom.xml pero no se logra cargar la dependencia al jar, por lo tanto se agrega el jar tradicionalmente.
