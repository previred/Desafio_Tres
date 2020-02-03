# Desafío 3: Complemento valores UF

## Descripción de la implementación

La implementación del algoritmo solicitado se realiza en tecnología `java 8` sin emplear frameworks pesados. Se dejan explicitas las transformaciones solicitadas en las instrucciones dentro de sus respectivos metodos en cada builder (ver siguiente extracto del código).

```java
public class Desafio_Tres {
public static void main(String[] args) {
    DatosUf datosUf = com.previred.desafio.tres.uf.DatosUf.getInstance();
    UfsBuilder builder = UfsBuilder.from(new com.previred.desafio.tres.uf.Valores().getRango());
    Ufs ufs = builder.rellenarDiasFaltantes(datosUf).ordenarDescendentePorFecha().build();
    UfsJsonSerializer.from(ufs).writeToPath("./valores.json");
}
}
```

| Archivo | Descripción del módulo |
| - | - |
| ./src/main/java/cl/gao/DesafioTresApp.java | Punto de entrada y modulo principal del algoritmo solicitado
| ./src/main/java/cl/gao/UfsJsonSerializer.java | Encapsula el comportamiento asociado al procedimiento de serializado que sufre el objeto Ufs para ser luego almacenado en un archivo `json`
| ./src/main/java/cl/gao/UfsBuilder.java | Encapsula el comportamiento asociado a las transformaciones realizadas sobre la clase Ufs
| ./src/main/java/cl/gao/PairGenerator.java | Encapsula la logica generacion de pares para posteriormente en otro modulo determinar los dias faltantes en una colección ordenada por fecha

Elementos no considerados o fuera de alcance (no solicitados en las instrucciones ni mencionados como deseables):
* Pruebas Unitarias
* Javadoc
* Frameworks como Spring
* Porcentaje de cobertura de código

## Tecnología y librerías utilizadas

| Herramienta o librería | Versión |
| - | - |
| OpenJDK | 8 |
| Gradle | 6.0.1 |
| Eclipse | 2019-06 |
| Google GSON Library | 2.8.5 |

## Detalles de compilación y ejecución (Linux/Unix)

**¡Atención! :** Los siguientes comandos asumen que usted ya se posiciono sobre directorio clonado en su linea de comandos.

| Comando | Descripción
| - | - |
| `./gradlew run` | Ejecutar en frío (Linux/Unix)
| `./gradlew build` | Generar archivo JAR (Linux/Unix) |
| `java -jar build/libs/Desafio_Tres.jar` | Ejecutar archivo JAR (Linux/Unix) |

## Detalles de compilación y ejecución (Windows)

**¡Atención! :** Los siguientes comandos asumen que usted ya se posiciono sobre directorio clonado en su linea de comandos.

| Comando | Descripción
| - | - |
| `gradlew.bat run` | Ejecutar en frío (Windows)
| `gradlew.bat build` | Generar archivo JAR (Windows) |
| `java -jar build\libs\Desafio_Tres.jar` | Ejecutar archivo JAR (Windows) |