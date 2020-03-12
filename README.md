Descripción de la implementación
    La solución fue implementada sobre una arquitectura Api - Rest, la cual expone 3 operaciones,
    cada una de estas nos permitira exportar los valores (UF) en formato json, csv y xml. Además 
    se aplica patrón de fachada para proporcionar una interfaz simple y desacoplar la lógica de 
    negocio del controlador
Tecnología y librerías utilizadas
    JDK 13
    Spring boot
    Librerias:
        gson
        jackson-databind
        jackson-dataformat-xml
        opencsv
Detalles de compilación y ejecución
    Compilación:
        Ejecutar en CMD o terminal (Desde la carpeta del proyecto): 
            mvn clean package 
        Lo cual generará el siguiente jar:
            desafio_tres_previred-0.0.1-SNAPSHOT.jar
    Ejecución:
        Para levantar aplicación ejecutar:
            java -jar %DIRECTORIO_LOCAL%/desafio_tres_previred/target/desafio_tres_previred-0.0.1-SNAPSHOT.jar
    Pruebas:
        http://localhost:8080/api/valores/json
        http://localhost:8080/api/valores/csv
        http://localhost:8080/api/valores/xml