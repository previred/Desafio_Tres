
Servicio Rest endpoint = http://localhost:8080/desafioTres/getComplementoValoresUf 
El cual retorna un arhivo json (Formato 3) con las fechas y valores de las ufs entre  el rango dado en orden ascendente.
se utilizo Spring boot para los servicios Rest.
Java 8
Dependencias 
Gson
Generador_Datos_Desafio_Tres-1.0.0

para la compilacion abrir consola y desde la carpeta que contiene el pom hacer mvn clean install
no olvidar agregar el jar del Generador_Datos_Desafio_Tres-1.0.0 al repositorio de m2 si es necesario modifique groupId y el artifactId
una vez compilado posicionarse en la carpeta target y hacer java -jar previredDesafioTres-0.0.1.jar
La solucion en un archivo rar, en se encuentra el jar listo para ser ejecutado mas el archivo que retorna.


abrir su navegador favorito y ir a http://localhost:8080/desafioTres/getComplementoValoresUf 





agregar jar externo al repositorio m2(Comandos)
mvn install:install-file -Dfile=Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=previred -DartifactId=desafio.tres -Dversion=1.0.0 -Dpackaging=jar