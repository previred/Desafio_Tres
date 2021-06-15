# Desafío 3: Complemento valores UF

Descripción de la implementación

- Se creó una clase Desafio que implementa una interfaz llamada IDesafio, donde se implementan los métodos: 
A) public Ufs getRango(), que devuelve el rango de valores a considerar para el resto de las implementaciones.
B) public void completeListUfs(Ufs ufs), que toma el rango de Ufs considerado y lo completa con los que faltan en el mismo, que son tomados del repositorio DatosUfs.
C) public List<Uf> listUfs(Ufs ufs, String order), devuelve la lista de Ufs ya ordenada, en dependencia del orden considerado, en este caso para el orden se declaró una constante ORDER_FECHA,
para que ordene por fecha, siempre descendentemente.
D) public String stringFromListUfs(List<Uf> listUfs), obtiene el String a partir de la lista, que sera convertido a JSON.
E) public String jsonFromListUfs(List<Uf> listUfs), obtiene el JSON a partir del String (método anterior)

- Se creó una clase SalidaJSON que implementa la interfaz ISalidaJSON, donde se implementan los métodos:
A) public String createJsonFile(), donde se crea el archivo en formato JSON que es copiado en la raíz del proyecto y devuelve el JSON que es mostrado en el navegador.

- Se creó una clase de tipo RestController llamada DesafioController que contiene el método public String createJsonFile() que llama al objeto de tipo ISalidaJSON, atributo de esta clase y a su método del mismo nombre.
(Esta funcionalidad es un RequestMapping de método GET y que devuelve un JSON).

- Se creó la clase Main, como clase principal a iniciar Spring Boot.

Tecnología y librerías utilizadas

- Lenguaje: Java
- Framework: Spring Boot
- Tecnologías: Maven
- Librerías: Swagger

Detalles de compilación y ejecución

- Para la instalación debe iniciarse el servicio del Spring Boot, para esto se le da RUN a la clase Main, en la raíz de la carpeta desafio.
- Automáticamente levantará el swagger de la app.
- Debe irse al link con desafio-controller, y ahí en método GET de la URL /valoresJSON
- Como Response de la ejecución se obtendrá un JSON como el esperado y en la carpeta del proyecto se creará un archivo llamado valores.json, con el mismo formato.
- Igualmente, por la URL del navegador se puede acceder a: http://localhost:8010/valoresJSON, que mostrará en el navegador el JSON esperado e igualmente se creará el archivo 
valores.json en la raíz del proyecto.

