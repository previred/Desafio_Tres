# Descripción de la implementación
La implementación de la solución está desarrollada con spring boot y tiene la siguiente arquitectura:
- Controllers: Controladores rest.
- Services: Definición de los servicios utilizados (interfaces). Implementa las funcionalidades en el pck .Impl.
- DTO: Pojos utilizados.
- Utils: Librerias útiles.

# Tecnología y librerías utilizadas
- Maven.
- Spring Boot.
- Gson.
- Lombok.

# Detalles de compilación y ejecución
- Descarge el repositorio con git clone.
- cd Desafio_tres/previred.
- mvn clean install.
- java -jar target/previred-0.0.1-SNAPSHOT.jar
- curl http://localhost:10290/uf
- el archivo generado se llama valores.json y está en la raiz del proyecto.
