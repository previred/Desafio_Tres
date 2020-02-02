# Descripción
    La solución que he implementado realiza un recorrido a la lista de UFs
    para comparar las fechas y encontrar los dias que faltan (tomar la fecha mas antigua y la mas 
    nueva para consultar el rango completo  fue una opción que pensé pero decidí buscar las faltantes en su lugar), 
    para luego hacer uso del metodo getUfs() y obtener los valores de Uf que corresponden.
    Luego realizo un ordenamiento por fecha descendente, seguido de una iteracion de la lista de ufs para
    lograr generar la salida JSON, creé 2 objetos distintos con el objetivo de que la salida pudiera ser en el orden 
    del formato solicitado (la serializacion JSON no mantiene el orden de los fields y no estaba segura de que tan 
    importante era mantener el formato) luego los concatené.
    Por ultimo realizo la generación del fichero valores.json

# Tecnología y librerías utilizadas
    Hice uso de LocalDate de joda-time para el manejo de las fechas:
        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.10.5</version>
        </dependency>
    Comparator para hacer ordenamiento.
    Stream para convertir en List el Set y ordenar e iterar lista.
    JSONObject para generar json a partir de objetos:
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20190722</version>
        </dependency>(javax.json-api).

# Detalles de compilación y ejecución
    Para el uso de el jar del repositorio enviado utilice la ayuda de un plugin de maven
    que permite agregarlo como repositorio local y de esa forma utilizarlo dentro del proyecto, 
    lo cual esta configurado dentro del pom.xml.
    Utilicé otro plugin para poder  definir la clase principal del proyecto y de esa forma
    no tener problemas para ejecutar el jar, este plugin al final genera un fat jar con las 
    dependencias necesarias llamado : desafio-1.0-SNAPSHOT-jar-with-dependencies.jar, el cual es 
    el que se debe ejecutar.

    Se debe ejecutar mvn clean package para regenerar el jar.

    El fichero de salida se encuentra dentro de la raiz del proyecto llamado valores.json