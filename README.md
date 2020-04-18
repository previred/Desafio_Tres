# Desafío 3: Complemento valores UF

## Link de Intrucciones para desafio:
- https://github.com/previred/Desafio_Tres/blob/master/README.md

## Intruciones para correr y levantar aplicación
Para poder levantar la aplicacion primero se deve installar las depenencias y luego correr
- primero hacemos un mvn install -U
    ```
    mvn install -U 
  ```

- Luego levantamos la aplicación
 ```
  mvn spring-boot:run
```

- La Aplicación quedará corriendo en el puerto 9090 que se definio en el archivo propertie, si se desea cambiar puede hacer desde este archivo o bien cuando ingrese la linear de comando ` mvn spring-boot:run` puede ingresar el puerto con la porpertie correspondiente.

## entrega de Salidas

### Salida Formato 1
- Endpoint: http://localhost:9090/challenge-3/v1/api/uf/csv

### Salida Formato 2
- Endpoint: http://localhost:9090/challenge-3/v1/api/uf/xml

### Salida Formato json
- Endpoint: http://localhost:9090/challenge-3/v1/api/uf/csv

### Salida de prueba por api-rest
- Endpoint: http://localhost:9090/challenge-3/v1/api/uf/
