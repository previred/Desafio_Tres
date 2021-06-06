Se abre el proyecto (yo lo trabaje con IDE IntelliJ), ideal borrar carpeta target, compilar proyecto (pueden ajustar la ruta del Log de manera opcional en el archivo log4j.properties), esto genera una nueva carpeta target, el cual también genera dentro de nuestra carpeta un archivo con extensión .war, este es el proyecto compilado para subir a un servidor tomcat 
<br>Este archivo .war se puede levantar en cualquier servidor tomcat desde la version 8 en adelante.
<br><br>
Importante<br>
Si tiene problemas levantando el ambiente mencionado, deje un ambiente en Amazon con un servidor tomcat 8.5 y la aplicacion cargada en la siguiente url:
<br><br>
Previredprueba1-env.eba-ekhmsjs2.us-east-1.elasticbeanstalk.com/uf/detalle

<br>
Yo en lo personal utilizo Postman para realizar pruebas REST, dejare el archivo json.collection de postman incluido en el proyecto (carpeta postman) por si lo quieren cargar de forma directa.

<br><br>
Nota: En caso de no tener registros de valores en la libreria otorgada, el servicio informara la fecha seleccionada con valor 0;
***
API REST<br><br>
URL servicio servido local<br>
Tipo PUT: {url local}/uf/detalle

JSON formato parametros de entrada (body):

Ejemplo:
<pre><code>{
 "inicio":"2014-04-01",
 "fin":"2015-03-05"
}
</code></pre>


Salida:

Ejemplo:
<pre><code>{
   "inicio": "2014-04-01",
   "fin": "2015-03-05",
   "ufs": [
       {
           "valor": 24038.01,
           "fecha": "2014-07-06"
       },
       {
           "valor": 24040.41,
           "fecha": "2014-07-07"
       },
       {
           "valor": 24042.81,
           "fecha": "2014-07-08"
       },
       {
           "valor": 24045.21,
           "fecha": "2014-07-09"
       },
       {
           "valor": 24045.99,
           "fecha": "2014-07-10"
       }
   ],
   "ufsFaltante": [
          {
              "valor": 23931.69,
              "fecha": "2014-05-31"
          },
          {
              "valor": 23936.31,
              "fecha": "2014-06-01"
          },
          {
              "valor": 23940.93,
              "fecha": "2014-06-02"
          },
          {
              "valor": 23945.55,
              "fecha": "2014-06-03"
          }
       ]
}</code></pre>

