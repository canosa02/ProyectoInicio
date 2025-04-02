# Proyecto #

## Funcionalidades ##
A continuación explicaremos las funcionalidades de la aplicación
### Productos ###
#### Alta de productos
Funcionalidad que nos permita añadir nuevos productos a la base de datos

#### Baja de productos
Funcionalidad que nos permita quitar productos de la base de datos

#### Modificación de productos
Funcionalidad que nos permita modificar valores de los diferentes productos.

#### Filtrar productos
Esta parte del programa nos permitirá hacer una búsqueda de los productos que queremos según su nombre y precio

### Tienda ###
#### Alta Tienda
Funcionalidad que nos permita añadir tiendas

#### Baja Tienda
Funcionalidad que nos permita dar de baja una tienda

#### Modificación de tiendas
Nos permite modificar las tiendas que tengamos en la base de datos

#### Filtrar tiendas
Funcionalidad con la cual podremos filtrar las tiendas según su nombre, país y ubicación



## ENDPOINTS ##

### Productos ###
**GET `/products/{id}` -Obtener datos del producto (id, nombre, tiendas en donde está y su precio respectivamente)** <br>

**Ejemplo JSON respuesta**
```json
{
    "id": "2",
    "nombre": "Agua",
    {
        "tienda" : 1,
        "precio: "10.5"
    }
    {
        "tienda" : 2,
        "precio: "9.5"
    }
}
```
**POST `/products` -Añadir un producto** <br>
**DELETE `/products/{id}` -Dar de baja un producto** <br>
**PUT/PATCH `/products/{id}` -Modificar información del producto** <br>
**GET `/products?=` -Listado ( se puede aplicar filtros )** <br>


### **Tienda** ###
**GET `/shop` -Obtener de la tienda  (id, ubicación, Número de  productos )** <br>
```json
{
    "id": "4",
    "id_ubicacion": "1",
    "país": "España",
    "ciudad": "Coruña",
    "direccion": "Av. Finisterre",
    "Número de productos": "30"
}
```
**POST `/shop` -Añadir una tienda** <br> 
**DELETE `/shop` -Dar de baja una tienda** <br> 
**PUT/PATCH `/shop` -Modificar tienda** <br>
**GET `/shop?=` -Listado ( se puede aplicar filtros )** <br>
```json
{
    "id": "4",
    "id_ubicacion": "1",
    "país": "España",
    "ciudad": "Coruña",
    "direccion": "Av. Finisterre",
    "Número de productos": "30"
}
{
    "id": "6",
    "id_ubicacion": "1",
    "país": "España",
    "ciudad": "Santiago",
    "direccion": "Av Orense",
    "Número de productos": "20"
}
```











