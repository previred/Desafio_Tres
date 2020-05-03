# Desafío Previred


Este desafío consiste en un pequeño desarrollo para generar un archivo en formato json con datos referentes a valores de Ufs obtenido para un rango de fecha.

La implementación fue realizada usando el lenguanje de programación Java, para eso se realizó una clonación del repositorio indicado y se realizó la conversión del proyecto a Maven para el manejo de las dependencias.

Se crearon los repectivos paquetes para la incluisión de clases tratando de aplicar como patrón de diseño los DAOs y DTOs.

De acuerdo a los lineamientos iniciales se hizo el consumo del método <code>getRango()</code> y posteriormente para generar y/o completar los datos faltantes se utilizaron los métodos <code>getUf()</code> y <code>getUfs</code> de la clase <code>DatosUf</code>. Para este desarrollo, en la clase inicial del proyecto están dos funciones para generar el archivo de valores usando cada uno de métodos indicados por separado: <code>generarJsonValoresGetUf()</code> y <code>generarJsonValoresGetUfs()</code>. 

#### Tecnología y librerías utilizadas
- Java 13
- Librería Generadora de datos provista por Previred
- Maven
- Gson

#### Detalles de compilación y ejecución
Una vez revisado y aceptado el pull request (como fue solicitado) se debe clonar el código a un repositorio local (por ejemplo). Deberá tener una máquina virtual Java para ejecutar la aplicación, una vez realizado esto en la consola del IDE o editor de código utilizado tendrá un registro log similar a:
~~~
INFO: Salida Desafio Previred - Generar archivo de valores UFs
Fecha Inicio: 2010-07-27
Fecha Fin 2013-12-07
El archivo valores.json fue generado con éxito usando el método getUfs
Fecha Inicio: 2011-01-16
Fecha Fin 2013-09-18
El archivo valores.json fue generado con éxito usando el método getUf
~~~

En el directorio raíz del proyecto, se encuentra una carpeta "data" donde estarán los dos archivos generados por los métodos indicados.

- valores.json
- valoresUf.json

Para cambiar esta ruta debe abrir el archivo de la clase <code>com.previred.desafio.persistence.dao.impl.ValoresDAOImpl</code> y editar cada uno de los métodos.

