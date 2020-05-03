## Desafío 3: Complemento valores UF

**Autor:** 
- Gabriel Roades Cavalieri
- gabrielroades@gmail.com
- Head Hunter: Leonardo Miranda Pavez - Tech Consult - LinkedIn

**Importante:** El archivo "valores.json" resultante se escribe en la raiz del proyecto y también se retorna en la respuesta http del servicio, ambos al realizar la peticion http GET.

**Formato elegido para el resultado:** Formato 3 - JSON.

###Tecnología y librerías utilizadas

La implementación de los requerimientos la construí mediante web service API Rest con Spring boot y estructura MVC para organizar el código en el IDE Eclipse Version: 2020-03 (4.15.0).

Construí el proyecto con Spring Boot 2.2.6 y gradle como administrador de dependencias.

###Descripción de la implementación

El proyecto contiene dos capas, en el controlador UFontroller se encuentra la implementación del endpoint mediante el cual se realiza la petición http GET desde el cliente y retorna un ResponseEntity con un arreglo de byte (archivo), el http header y el http status 200 , en el controlador se inyecta la dependencia a la clase de la capa de servicios que implementa mediante interfaz UFService la función getUfs en donde se realizan llamadas a las funciones con el algoritmo solicitado haciendo las consultas mediante la librería indicada Generador_Datos_Desafio_Tres-1.0.0.

Para complementar los valores UF faltantes, llamé al método getUfs de la clase DatosUf con los parámetros de Fecha de inicio y fin que retorna el metodo getRango de la clase Valores. Se debe garantizar que estas fechas son consistentes.

En UfUtil implementé dos funciones estáticos, dateFormat (recibe un Date y un patrón, devuelve un String con el formato indicado en el patrón) y valueFormat (recibe un valor Double, retorna un String con el formato indicado en la expresión regular de la constante VALUEREGEX).

En UFConstant declaré constantes para centralizar algunos valores reutilizables.

Cree dos POJOS UF y UFSResult para crear los objetos con los datos recibidos desde la librería y escribirlos en el archivo enviado al cliente. En al clase UF ademas de los setter and getter, agregué un Comparator donde implementé la función compare para aplicar un sort con la clase de tipo Collections a la lista de UFs por el atributo fecha (String con el formato yyyy-MM-dd).

UFSResult contiene un arreglo de UF.

###Detalles de compilación y ejecución

El proyecto compila y se ejecuta mediante el motor de eclipse para Java aplications. Una vez ejecutado, el servicio utiliza el puerto 8080 para las peticiones http y el endpoint en el contexto api/v1. 

Para consultar el servicio, recomiendo utilizar el cliente API llamado Postman. crear una coleción con la petición GET al endpoint localhost:8080/api/v1/uf/download estableciendo como encabezado el Content-Type: application/json. El resultado JSON se mostrará en el cuerpo de la respuesta http y se podrá guardar el archivo valores.json usando la opcion de salvar respuesta como un archivo.

Desde un cliente Web implementado en cualquier lenguaje se podría descargar automaticamente el archivo json indicando el MIME application/octet-stream.

**En el código fuente no debe incluirse comentarios, pero sólo para este caso, escribí algunos de los requerimientos indicando la ubicación de la implementación.**
 