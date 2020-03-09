# Descripción de la implementación

- Implementación de Beans Spring Boot para la interacción entre cada componente.
- El modo de runtime es a través de un archivo ejecutable JAR.
- Para el formato del archivo de volcado, se utilizó el formato JSON.

Clases
--
- Se crearon dos clases que heredan de Uf (*ValorUF.java*) y UFs (*UfsRango.java*) respectivamente para los tratamientos internos que han sido implementados en el algoritmo.

Servicios
--
- **GestionUf.java**: Clase configurada como servicio que realiza la interacción entre la aplicación y los métodos proporcionados por la librería entregada. Además, devuelve los objetos que heredan de las clases originales de la librería.

Componentes
--
- **?**: Implementación del algoritmo que realiza las tareas solicitadas en el requerimiento
- **?**: Crea y exporta el String JSON que será volcado a través de un archivo en el almacenamiento permanente del sistema operativo.

Utilidades
--
- Clase de utilidades que implementa la transformación y formateo de datos para los fines solicitados en el requerimiento.

Algoritmo
--
- Descripción del algoritmo

# Tecnología y librerías

Tecnología
--
- Spring Boot 2.2.5
- Java 1.8
- Maven 3.3.9
- IntelliJ 2017.1.5

Librerías
--
- Gson 2.8.5 para archivo JSON
- Generador_Datos_Desafio_Tres-1.0.0 para obtención de datos y funcionalidades


# Detalles de compilación y ejecución
*Sintaxis Windows*

1. Descargar el proyecto desde Github en directorio local:
```bash
https://github.com/previred/Desafio_Tres.git
```
2. Ingresar al directorio "uf" (raiz del proyecto uf)

3. Ejecutar comando Maven en el directorio raíz del proyecto:
```bash
> mvn clean 
```
4. Ejecutar archivo .jar creado en paso anterior
```bash
> mvn spring-boot:run
```
5. Una vez ejecutado el archivo, aparecerá un archivo llamado **valores.json** en el mismo directorio raíz que contendrá la lista UFs y sus periodos tal como se describe en el requerimiento.`

