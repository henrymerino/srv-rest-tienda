# srv-rest-tienda
servicio rest de tienda online

###Descripción
Servicio que realiza petición de peticiones de productos, registra al cliente, productos, tiendas y sus productos

###Arquitectura
Servicio rest, maven con una capa de conexión a base de datos postgres,una capa de servicio, archivo de propdiedades, que contiene la conexión a un DataSource EAP 7.4, jwt para autenticación y autorización a las Urls

###Despligue
- Crear base de detaos store, con tablas: client, client_order, detail_order, orden, product, store (ScriptsBDStore.sql)

- Colocar en el servidor EAP la ruta a las properties

  -P D:\Henry\springboot\wokSpcaePPMSrvRestTienda\srv-rest-tienda\config-prop\application.properties
  
 - Crear el DataSources en el standalone.xml del servidor y colocar el nombre del jndi en el archivo application.properties en la variable 
  spring.datasource.primary.jndi-name=
 
 - Generar el compilado y desplegar en el servidor 

###URL
las url del servicio se pueden enocntrar desplegando Swagger http://127.0.0.1:8080/srv-rest-tienda-servicio/open-api/swagger-ui/index.html

- POST 
Guarda store
http://localhost:8080/srv-rest-tienda-servicio/api/store/saveStore

Guarda productos
http://localhost:8080/srv-rest-tienda-servicio/api/product/saveProduct

Guarda Pedido
http://localhost:8080/srv-rest-tienda-servicio/api/orden/saveClientOrden

- GET

Obtiene todos las tiendas
http://localhost:8080/srv-rest-tienda-servicio/api/store/getAllStores

Obtiene todos los productos
http://localhost:8080/srv-rest-tienda-servicio/api/product/getAllProduct

Obtiene tienda
http://localhost:8080/srv-rest-tienda-servicio/api/store/getStore/3

Obtiene producto
http://localhost:8080/srv-rest-tienda-servicio/api/product/getProduct/1

Obtiene token
http://localhost:8080/srv-rest-tienda-servicio/api/persona/login


###Insert para tabla Rol y Persona (JWT)

insert into rol values (1, 'ADMINISTRADOR'); 

INSERT INTO public.persona(
	fecha_nacimiento, id_persona, id_rol, cedula, telefono, usuario, email, apellidos, nombres, direccion, clave)
	VALUES ('2024-01-15', 1, 1, '1713736872', '0998026834', 'henry.aguular', 'haguilarmerino@gmail.com', 'AGUILAR MERINO', 'HENRY AUGUSTO', 'CARAPUNGO', '123456');

 


