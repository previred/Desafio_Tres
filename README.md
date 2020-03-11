Autor: Gabriel Spalletta

DESCRIPCIÓN DE LA SOLUCION
--------------------------
Se implementó la solución como REST de acuerdo al ejemplo 3. A través de un JSON que se obtendrá al consumir el servicio http://localhost:9000/api/v1/rango

Internamente tiene el controller DesafioController que llama al servicio DesafioService encargado de toda la lógica del negocio. Finalmente se han definido dos entidades Rango y UnidadFomento que actúan como pojo en la solución.

TECNOLOGIA UTILIZADA Y LIBRERIAS
--------------------------------
Para la resolución del ejercicio se utilizó:
-SpringBoot 2.2
-Gradle
-La librería entregada (Generador_Datos_Desafio_Tres-1.0.0.jar)
-Log4j
-Json
-Java 1.8

COMPILACIÓN Y EJECUCION
-----------------------
Para la compilación, luego de hacer un "Refresh Gradle Project", lo ejecuto como Run As... Spring Boot App.... 


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.0.RELEASE)

2020-03-11 03:07:41.913  INFO 6724 --- [           main] desafio3.DesafioTres                     : Starting DesafioTres on Lorena-HP with PID 6724 (C:\git\desafio3\bin\main started by Lorena in C:\git\desafio3)
2020-03-11 03:07:41.938  INFO 6724 --- [           main] desafio3.DesafioTres                     : No active profile set, falling back to default profiles: default
2020-03-11 03:07:49.414  INFO 6724 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9000 (http)
2020-03-11 03:07:49.558  INFO 6724 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-03-11 03:07:49.558  INFO 6724 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
2020-03-11 03:07:50.421  INFO 6724 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-03-11 03:07:50.421  INFO 6724 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 8241 ms
2020-03-11 03:07:51.592  INFO 6724 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-03-11 03:07:52.965  INFO 6724 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9000 (http) with context path ''
2020-03-11 03:07:52.975  INFO 6724 --- [           main] desafio3.DesafioTres                     : Started DesafioTres in 13.181 seconds (JVM running for 43.994)
2020-03-11 03:07:56.348  INFO 6724 --- [nio-9000-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2020-03-11 03:07:56.349  INFO 6724 --- [nio-9000-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2020-03-11 03:07:56.411  INFO 6724 --- [nio-9000-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 62 ms
2020-03-11 03:07:56.541  INFO 6724 --- [nio-9000-exec-2] desafio3.controller.DesafioController    : Inicio de cálculo de rango
2020-03-11 03:07:56.542  INFO 6724 --- [nio-9000-exec-2] desafio3.service.DesafioServiceImpl      : Inicio de generación de datos
2020-03-11 03:07:57.043  INFO 6724 --- [nio-9000-exec-2] desafio3.service.DesafioServiceImpl      : Inicio de llenado de datos faltantes
2020-03-11 03:07:57.220  INFO 6724 --- [nio-9000-exec-2] desafio3.service.DesafioServiceImpl      : Orden decreciente de los datos generados
2020-03-11 03:07:57.223  INFO 6724 --- [nio-9000-exec-2] desafio3.controller.DesafioController    : Generacion de rango finalizada
2020-03-11 03:09:16.674  INFO 6724 --- [on(2)-127.0.0.1] inMXBeanRegistrar$SpringApplicationAdmin : Application shutdown requested.
2020-03-11 03:09:16.681  INFO 6724 --- [on(2)-127.0.0.1] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'


Y posteriormente uso postman para consumir el servicio http://localhost:9000/api/v1/rango
Dentro de la carpeta "postman" existe una exportación del metodo GET. La salida del mismo es el siguiente:

{
    "inicio": "2010-04-05",
    "fin": "2013-12-04",
    "ufs": [
        {
            "fecha": "2013-12-04",
            "dato": "23.239,75"
        },
        {
            "fecha": "2013-12-03",
            "dato": "23.238,97"
        },
        {
            "fecha": "2013-12-02",
            "dato": "23.238,2"
        },
        {
            "fecha": "2013-12-01",
            "dato": "23.237,43"
        },
        {
            "fecha": "2013-11-30",
            "dato": "23.236,65"
        },
        {
            "fecha": "2013-11-29",
            "dato": "23.235,88"
        },
        {
            "fecha": "2013-11-28",
            "dato": "23.235,1"
        },
        {
            "fecha": "2013-11-27",
            "dato": "23.234,33"
        },
        {
            "fecha": "2013-11-26",
            "dato": "23.233,56"
        },
        {
            "fecha": "2013-11-25",
            "dato": "23.232,78"
        },
        {
            "fecha": "2013-11-24",
            "dato": "23.232,01"
        },
        {
            "fecha": "2013-11-23",
            "dato": "23.231,23"
        },
        {
            "fecha": "2013-11-22",
            "dato": "23.230,46"
        },
        {
            "fecha": "2013-11-21",
            "dato": "23.229,69"
        },
        
        .
        .
        .
 }
        
