# Desafío 3: Complemento valores UF
Descripción de la implementación La solución fue implementada en Java usando Maven, siguendo los pasos a continuación 1.- Obtener fecha inicio, fecha fin y rango generado por la clase Valores 2.- Generar todos los Ufs para el rango indicado por las fechas previamente obttenidas 3.- Concatenar la lista obtenida el paso 2 con la lista (lista usada fuente para completar las "lagunas") de la clase Valores 4.- Eliminar duplicados en la lista obtenida en el paso 3 y ordenar el resultado 5.- Guardar en el archivo de salida valores.json

Tecnología y librerías utilizadas

Java 1.8 y Maven

Librerias: 
import com.google.gson.JsonArray; 
import com.google.gson.JsonObject; 
import com.previred.desafio.tres.uf.DatosUf; 
import com.previred.desafio.tres.uf.Valores; 
import com.previred.desafio.tres.uf.vo.Uf; 
import com.previred.desafio.tres.uf.vo.Ufs; 
import java.io.FileWriter; import java.util.*; 
import java.util.stream.Collectors; 
import java.util.stream.Stream;

Detalles de compilación y ejecución Para compilar y ejecutar el proyecto se usó Intellij IDEA Community siguiendo los siguientes: 
1.- Abrir Intellij IDEA 
2.- Seleccionar Import Project/Importar Proyecto 
3.- Ubicar y seleccionar el archivo pom.xml 
4.- Dentro del Intellij IDEA, ubicar en la estructura del proyecto el archivo que tiene por nombre Principal, ubicado en la carperta /src/main/java/com/acl/challenger 
5.- Hacer click derecho sobre el archivo Principal y seleccionar Run 'Principal.main()' 
6.- Seleccionar la pestaña de Maven ubicada arriba a la derecha de la ventana Intellij IDEA 7.- Hacer click en el icono Play para compilar y ejecutar el proyecto
La salida valores.json se visualizara en la carpeta donde esta ubicado el archivo pom.xml

La compilación y ejecución se hizo a través de Maven y usando la herramienta Intellij IDEA Community

PD: Una solución alternativa es reemplazar lo que esta desde la linea 32 hasta la linea 45 con el siguiente fragmento de código: List listOfUfs2 = new ArrayList<>(); listOfUfs2.addAll(datos.getUfs(fechaInicio, fechaFin).stream().sorted(Comparator.comparing(Uf::getFecha).reversed()).collect(Collectors.toCollection(LinkedHashSet::new))); ufsArray = getJsonArrayUfs(listOfUfs2);
