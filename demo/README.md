# Desafío 3: Complemento valores UF Juan Clavero


- Descripción de la implementación

          -Problema: Existe un método "getRango()" de la clase Valores que entrega los valores de uf para un rango de fechas.
                    Estos valores no son secuenciales ni están ordenados.

          -Solución: Crear un json con los valores faltantes y ordenados de forma descendente.

                    Este proyecto llamado "demo" que contiene 4 clases:

                    1.-App: Contiene un método "main" para ejecutar el programa.
                    2.-UfService: Contiene el método generateJson()" que crea el archivo json en la carpeta "C:\Users\Public\Documents"
                    3.-UfObject: Esta clase contiene la estructura para los "datos" y "fechas" de las ufs.
                    4.-UfResponse: Esta clase contiene la estructura del json final.



- Tecnología y librerías utilizadas
   
         -Maven para la estructura de proyecto.

         -Componente Generador_Datos_Desafio_Tres  para obtener los valores Ufs. 

         -Librería gson para generar el json.



- Detalles de compilación y ejecución

         -Después de actualizar el proyecto, actualizo el maven con la opción de Update Maven.
         -Luego voy a la clase App y ejecuto el método "main".
         -Reviso si el archivo valores.json se genero en la carpeta "C:\Users\Public\Documents".
