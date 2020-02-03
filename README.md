## Descripción de la implementación
La implementación tiene la finalidad de generar un json con el resultado obtenido del procesamientos de los datos de 
UFs para un rango de fecha definido, el cual al momento de consultar la url **'http://localhost/obtieneUf'** se generara 
automáticamente una respuesta en el navegador, dando a la vista el detalle de cabecera (fecha inicio y fecha fin), además 
de los datos 'fecha' y 'valor' de la Ufs.

## Tecnología y librerías utilizadas
* [IDE de desarrollo] - Eclipse Foundation (plugins Spring Tools Suit 4)
* [Arquitectura desarrollo] - Spring Boot 2.2.4, Maven 3.0.0, Java 1.8, Sring Web 5.2.3
* [Libreria y Dependencias] - Generador_Datos_Desafio_Tres-1.0.0, Gson 2., mas autogeneradas.

## Detalles de compilación y ejecución
* [Compilación JAR] - Click Derecho (Sobre el proyecto) -> Run As - Maven Build... -  Goals: package - Run
* [Compilación WEB] - Click Derecho (Sobre el proyecto) -> Run As - Spring Boot App
* [Ejecución] - Ingresar en la url  'http://localhost/obtieneUf'

## Validación Resultado
* [response](https://jsonformatter.curiousconcept.com/) - Para dar formato json al resulado, visitar la pagina y pegar lo obtenido del navegador

Creado por David Carreño Martinez - 2020