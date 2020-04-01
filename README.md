Descripción de la implementación:
Se agregó la libería otorgada por previred, se consumió la función
getRango() para obtener las fechas de inicio y fin,
posterior a esto se obtuvieron los datos de las UFs de cada día,
según este rango. Dado estos datos, se reemplazó la lista original, ya que este ya estaba
ordenado y no requería implementar un algoritmo de ordenamiento
en base a las fechas. Finalmente, estos mismos datos fueron mapeados
y agregados a un documento json en base a la herramienta gson
(las fechas de inicio y fin se imprimen al final del documento
automaticamente provista por gson).
De todas maneras, el código está comentado para su seguimiento

Tecnologías y librerías utilizadas:
Se utilizó intellij IDEA como entorno de desarrollo, 
con la utilización de maven, creando un proyecto quickstart
agregando las librerías otorgadas por previred, cabe destacar
que se añadió además y nuevamente, la dependencia "gson" (com.google.code.gson)
ya que compilaba con errores

Detalles de compilación y ejecución
Ejecutar "mvn clean install -u"
Ejecutar App del proyecto src/main/java/com.previred/App
