- Desafio Tres Previred

  - Descripcion de la implementación. 
    . Se ha desarrollado una api, que consume la libreria Generador_Datos_Desafio_Tres entregada por Previred, la cual retorna 
      un rango de fechas y un listado de valores de Ufs que están dentro de este rango. 
    . En primera instancia, se recupera con el metodo getUf de la clase DatosUf los valores de las Uf del inicio y fin del rango
      entregado anteriormente. Una vez hecho esto, se ordena la lista que se tiene, para posteriormente bsucar los rangos faltantes 
      para completar el listado. Si al momento de buscar una fecha el rango faltante es de un solo dia, se utiliza el metodo getUf, si es mas de un día se utiliza el metodo getUfs. 
    . Una vez teniendo la lista completa se arma el json de salida para ser mostrado al ejecutar esta api. 
    . Además de ser mostrado en pantalla, se genera un archivo llamado valores.json en la carpeta export ubicada en la raiz del proyecto.
    . Esta api puede ser ejecutada directamente desde el navegador u otro aplicativo como postman o soapui.

  -Tecnología y librerías utilizadas.
    . Se ha utilizado la tecnología springboot para la construcción y junit para realizar el test.
    . Las librerías utilizadas son las básicas de springboot, junit para realizar el test, gson requerida para la libreria de previred

  - Detalles de compilación y ejecución.
    . Para la compilación se instala el jar externo de previred mediante el comando 
      mvn install:install-file -Dfile=Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=com.previred.desafio -DartifactId=uf -Dversion=1.0 -Dpackaging=jar
      y luego se realiza un mvn clean install.
    . Para ejecutar la api, se debe bajar el proyecto, y en la carpeta raiz de éste, ejecutar el comando 'mvn spring-boot:run', una vez iniciado
      spring, se debe entrar a la url http://localhost:8080/api/uf mediante un navegador, o bien en un programa como soapui o postman mediante un GET.