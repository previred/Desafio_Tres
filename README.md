###Descripción de la implementación:
- Servicio Rest con SpringBoot que genera un archivo Json sobre un listado
de valores de UF junto con un rango de fechas.
 
###Tecnología y librerías utilizadas:
- SpringBoot
- Librerías:
    -Generador_Datos_Desafío_Tres-1.0.0.jar
    -gson-2.8.5.jar
    -json-simple-1.1.1.jar
    
###Detalles de compilación y ejecución:
En caso de ejecución de manera local:
    Se debe importar el proyecto y presionar el botón Run.
- Luego en el navegador, se ingresa el siguiente url:
    http://localhost:8080/obtenerJson/uf
    En caso de que sea exitoso, se muestra tanto un mensaje en la pantalla,
    cómo también se genera un archivo dentro del mismo proyecto llamado
    valores.json.