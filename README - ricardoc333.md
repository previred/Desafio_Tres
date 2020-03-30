# Desafío 3: Complemento valores UF. Completado por Ricardo Carrillo

El desafío fue completado con los siguientes resultados:
1.  Se intentó emplear la librería "lib\Generador_Datos_Desafio_Tres-1.0.0.jar" que se encuentra en este proyecto, pero hubo errores que no pude solventar a tiempo y decidí trasladar el código al proyecto sin alterarlo. Intenté con proyecto en Gradle y Maven y con varias versiones de Java pero no fue posible utilizar el JAR como tal.
2.  Se consumió la función getRango de la clase com.previred.desafio.tres.uf.Valores (incorporada manualmente al proyecto).
3.  Se escribió el algoritmo para complementar los valores de UF para las fechas faltantes.
4.  Se utilizaron los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf (incorporada manualmente al proyecto).
5.  Se ordenó la lista de salida de forma descendente y se puede alterar el código para ordenarla ascendentemente.
6.  Se eligió el formato de salida número 3: JSON.
7.  Se emplearon las librerías jackson-dataformat-xml (versión 2.9.0) y jackson-databind (versión 2.9.0).
8.  Se empleó Java 8, Maven 3.6.0, Codificación UTF-8, IntelliJ IDEA 2019.3.4 Comunity Edition sobre Windows 10 Home versión 1909, 64 Bits.
9.  Detalles de compilación:

```
"C:\Program Files\Java\jdk1.8.0_241\bin\java.exe" -Dmaven.multiModuleProjectDirectory=C:\Users\RICAR\Documents\GitHub\Desafio_Tres "-Dmaven.home=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\plugins\maven\lib\maven3" "-Dclassworlds.conf=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\plugins\maven\lib\maven3\bin\m2.conf" "-Dmaven.ext.class.path=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\plugins\maven\lib\maven-event-listener.jar" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\lib\idea_rt.jar=55705:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\plugins\maven\lib\maven3\boot\plexus-classworlds-2.6.0.jar" org.codehaus.classworlds.Launcher -Didea.version2019.3.4 compile
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------< com.ricardoc333.desafio.tres.uf:ricardoc333 >-------------
[INFO] Building ricardoc333 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ ricardoc333 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.6.0:compile (default-compile) @ ricardoc333 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 8 source files to C:\Users\RICAR\Documents\GitHub\Desafio_Tres\target\classes
[INFO] /C:/Users/RICAR/Documents/GitHub/Desafio_Tres/src/main/java/com/ricardoc333/desafio/tres/Ufs.java: Some input files use unchecked or unsafe operations.
[INFO] /C:/Users/RICAR/Documents/GitHub/Desafio_Tres/src/main/java/com/ricardoc333/desafio/tres/Ufs.java: Recompile with -Xlint:unchecked for details.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.942 s
[INFO] Finished at: 2020-03-29T22:54:36-03:00
[INFO] ------------------------------------------------------------------------
```

.10.  Detalles de ejecución:

```
"C:\Program Files\Java\jdk1.8.0_241\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\lib\idea_rt.jar=55725:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.3.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_241\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_241\jre\lib\rt.jar;C:\Users\RICAR\Documents\GitHub\Desafio_Tres\target\classes;C:\Users\RICAR\Documents\GitHub\Desafio_Tres\lib\Generador_Datos_Desafio_Tres-1.0.0.jar;C:\Users\RICAR\.m2\repository\com\google\code\gson\gson\2.8.5\gson-2.8.5.jar;C:\Users\RICAR\.m2\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-xml\2.9.0\jackson-dataformat-xml-2.9.0.jar;C:\Users\RICAR\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.0\jackson-core-2.9.0.jar;C:\Users\RICAR\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\RICAR\.m2\repository\com\fasterxml\jackson\module\jackson-module-jaxb-annotations\2.9.0\jackson-module-jaxb-annotations-2.9.0.jar;C:\Users\RICAR\.m2\repository\org\codehaus\woodstox\stax2-api\3.1.4\stax2-api-3.1.4.jar;C:\Users\RICAR\.m2\repository\com\fasterxml\woodstox\woodstox-core\5.0.3\woodstox-core-5.0.3.jar;C:\Users\RICAR\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.0\jackson-databind-2.9.0.jar" com.ricardoc333.desafio.tres.uf.Main

Process finished with exit code 0
```

.11.  Una de las salidas del archivo JSON luego de ejecutar el programa es la siguiente:

```json
{
   "inicio":"2010-08-06",
   "fin":"2012-10-26",
   "ufs":[
      {
         "fecha":"2010-08-06",
         "dato":21227.57
      },
      {
         "fecha":"2010-08-07",
         "dato":21227.57
      },
      {
         "fecha":"2010-08-08",
         "dato":21227.57
      },
      {
         "fecha":"2010-08-09",
         "dato":21227.57
      },
      {
         "fecha":"2010-08-10",
         "dato":21231.67
      },
      {
         "fecha":"2010-08-11",
         "dato":21235.76
      },
      {
         "fecha":"2010-08-12",
         "dato":21239.86
      },
      {
         "fecha":"2010-08-13",
         "dato":21243.96
      },
      {
         "fecha":"2010-08-14",
         "dato":21248.06
      },
      {
         "fecha":"2010-08-15",
         "dato":21252.16
      },
      {
         "fecha":"2010-08-16",
         "dato":21256.26
      },
      {
         "fecha":"2010-08-17",
         "dato":21260.37
      },
      {
         "fecha":"2010-08-18",
         "dato":21264.47
      },
      {
         "fecha":"2010-08-19",
         "dato":21268.57
      },
      {
         "fecha":"2010-08-20",
         "dato":21272.68
      },
      {
         "fecha":"2010-08-21",
         "dato":21276.78
      },
      {
         "fecha":"2010-08-22",
         "dato":21280.89
      },
      {
         "fecha":"2010-08-23",
         "dato":21285.0
      },
      {
         "fecha":"2010-08-24",
         "dato":21289.1
      },
      {
         "fecha":"2010-08-25",
         "dato":21293.21
      },
      {
         "fecha":"2010-08-26",
         "dato":21297.32
      },
...
      {
         "fecha":"2012-10-26",
         "dato":22703.59
      }
   ]
}
```

### Formato Seleccionado :3
Se creó un archivo JSON que contiene el formato:
 - Contiene la fecha de inicio “inicio”.
 - Contiene la fecha de fin “fin”.
 - Contiene La lista de valores de “UFs” con los valores de fecha de uf “fecha” y valor de la uf “dato”.

 - Se implementó las solución en Java (con maven).
 - La solución fue enviada vía un pull request al repositorio.
 - La solución contiene este "README - ricardoc333.md" con:
   - Descripción de la implementación
   - Tecnología y librerías utilizadas
   - Detalles de compilación y ejecución
 - El archivo de salida tiene como nombre “valores.json” y es entregado junto con esta solución
 - Por último, el detalle del commit indica los siguientes datos:
   - Nombre Completo: Ricardo Alberto Carrillo Chirinos.
   - Correo Electrónico: ricardo_c3@hotmail.com
   - Vía por la que me enteré del desafío: LinkedIn: Leonardo Miranda (lmp.techconsult@gmail.com)
