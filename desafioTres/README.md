# Desafío 3: Solucion

Este proyecto brinda una solucion al "Desafio Tres", integrando el "Generador_Datos_Desafio_Tres" como fuente de datos a consumir, la aplicaion solo consta de un archvio principal "Main.java" y una clase para las lagunas de valores "Uf" segun lo retornado por la clase Valores( getRango()).
### Tenologias Usadas

 - Gradle: Gradle para manejo de dependencias y contruccion del proyecto.
 - GSON: libreria de google para menjo de json en java (https://github.com/google/gson).
 - Generador_Datos_Desafio_Tres(.Jar): Libreria para la generacion de datos a resolver.

### Pasos para compilar o ejecutar

1.  Abrir el proyecto usando netbeans(1.1) o algun Ide(1.2).
  - 1.1  Si Escogio netbeans entonces solo de click sobre el nombre de proyecto en la barra lateral de proyecto, y click en build.
  - 1.2  Si Escogio otro Ide puede hacer uso de la consola/terminal, se dirige a la ruta de la carpeta del proyecto y ejecuta gradle `gradle -q build`.
2.  se genera un archivo .jar en la carpeta build/libs/ el cual es ele ejcutable de la aplicacion.
