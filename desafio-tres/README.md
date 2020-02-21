# Read Me First
The following was discovered as part of building this project:

# Description
Solution for the Desafio Tres of Previred 
This is a solution using SpringBoot to generate fast built in infrastructure
It is using GSON because it was a dependency of provided Generador_Datos_Desafio_Tres-1.0.0.jar library and also used to generate JSON output
This solution was executed using java build 1.8.0_231-b11

There were build with 2 packages
com.previred.common 		: to contain common classes that could be reused in the future (business)
com.previred.desafio_tres	: to contain solution specific classes (front)

for simplicity, it doesn't need to incur in more fine grane package structure (this is just an example test)

# Getting Started

This project depends on Generador_Datos_Desafio_Tres-1.0.0 library this library should be in a Maven repo to allow all projects take newer version. So we will emulate that installing the lib by hand.

1) Install the library executing this command from the 'Desafio_Tres' directory

mvn install:install-file -Dfile=lib\Generador_Datos_Desafio_Tres-1.0.0.jar -DgroupId=com.previred -DartifactId=generador-datos -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true

2) Run next command to build the application, it is skipping test, there is need to add tests and run them on build process on regular procedure

mvn clean compile package -DskipTests

3.1) Execute application using the default file output (valores.json) 

java -jar target\desafio_tres-0.0.1-SNAPSHOT.jar

3.2) Execute application using a custom file output 

java -jar target\desafio_tres-0.0.1-SNAPSHOT.jar <filePath>

	Example: java -jar target\desafio_tres-0.0.1-SNAPSHOT.jar salida.json