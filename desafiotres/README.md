# Solución realizada con archivo de salida tipo JSON

La solución generará un archivo `.jar` el cual al ejecutarlo creará en el mismo directo de ejecución el archivo `valores.json`

## Detalle de los sistemas

 * Maven 3.6.3
 * Java 8

## Instalacion previa de libreria externa

Para hacer uso de la libreria externa `Generador_Datos_Desafio_Tres-1.0.0.jar` en el desarrollo de la solución, se instalo localmente en el repositorio maven .m2, esto
con la finalidad de referenciarla como dependencia maven en el archivo `pom.xml`

la instalación se realizo con el siguiente comando maven, tomando en cuenta que se de debe ejecutar en el mismo directorio donde se encuanta el archivo `.jar` a instalar

```bash
mvn install:install-file -Dfile=Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=com.previred.desafiotres -DartifactId=generador-datos-desafiotres -Dversion=1.0.0 -Dpackaging=jar
```

## Dependencias

 * org.apache.logging.log4j:log4j-api 2.14.0 para loggin del sistema 
 * org.apache.maven.plugins:maven-jar-plugin 3.2.0
 * com.fasterxml.jackson.core:jackson-core para creacion del archivo en formato JSON
 * com.previred.desafiotres:generador-datos-desafiotres 1.0.0 libreria externa de generacion de datos

## Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio `desafiotres` desde la `cmd` y ejecutar el siguiente comando `maven`

```bash
mvn clean package -DskipTests=true
```

Luego de compilar el proyecto debe ingresar al directorio `target` ya que en este se encuantra el `.jar` generado y el directorio `dependency-jars` el cual contiene todas las dependicias a necesitar al momento de ejecutar el siguiente comando `java`

```bash
java -jar .\desafiotres-1.0.jar
```

*Nota*:
Si copi el archivo `desafiotres-1.0.jar` a otro directorio, debe copiar tambien el directorio `dependency-jars`

## Resultados

Al momento de ejecución se vera en el símbolo del sistema (cmd), mensajes del paso a paso del proceso y si éste termino exitoso o no al crear al archivo `valores.json`