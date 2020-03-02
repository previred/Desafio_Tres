# Desafío 3: Complemento valores UF

- Descripción de la implementación
  
  - Proyecto java standalone compilado con maven que ordena valores de uf pertenecientes a rango de fechas dados.

- Tecnología y librerías utilizadas

  - lombok genera en tiempo de compilación metodos generales (geters, seters, builder)
  - streamex libreria para manipulación de streams.
  - gson librería utilizada para serializacion de clases en formato json.

- Detalles de compilación y ejecución
  
  - para realizar la compilación ejecutar "maven clean package"
  - para ejecutar el jar "java -jar DesafioTres-jar-with-dependencies.jar"

- El archivo de salida debe tener como nombre “valores” con su respectiva extensión y debe ser entregado junto con la solución

  - archivo requerido es generado en la raíz del proyecto "valores.json"
