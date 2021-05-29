# Desafío 3: Complemento valores UF

Para darle solución al desafío el formato de salida Formato 3 (JSON), se genera el rango de fechas, se ordenan y se escribe en un fichero JSON

https://github.com/previred/Desafio_Tres

```json
{
  "inicio":"2014-04-01",
  "fin":"2015-03-05",
  "UFs":[
    {
      "fecha":"2014-01-04",
      "dato":"23.321,57"
    },
    {
      "fecha":"2014-01-05",
      "dato":"23.324,58"
    },
    {
      "fecha":"2014-01-06",
      "dato":"23.327,58"
    },
    {
      "fecha":"2014-01-07",
      "dato":"23.330,58"
    },
    {
      "fecha":"2014-01-08",
      "dato":"23.333,59"
    },
    {
      "fecha":"2014-01-09",
      "dato":"23.336,59"
    },

        :

    {
      "fecha":"2014-04-01",
      "dato":"23.610,77"
    }
  ]
}
```

## Implementación:
- El resultado de la ejecución del programa se guarda en la carpeta resultado\response.json

## Tecnologías y librerias:

- Java Marven
- Librerias: Generador_Datos_Desafio_Tres-1.0.0, Gson

## Instalación:

Para la instalción ejecutar el comando en la raiz del proyecto
```
    mvn clean install
```
El ejecutable de la aplicación se encuentra en la carpeta /target

## Ejecución:

```
    java -jar desafio2-final.jar
```

