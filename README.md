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

#### Añadir producto a la tienda
Nos permite añadir el producto deseado a la tienda que queramos

#### Filtrar tiendas
Funcionalidad con la cual podremos filtrar las tiendas según su nombre, país y ubicación



## ENDPOINTS ##

### Productos ###
**GET `/products/{idProduct}` -Obtener datos del producto (id, nombre, tiendas en donde está y su precio respectivamente)** <br>

**Ejemplo JSON respuesta**
```json
{
    "id": 2,
    "nombre": "Agua",
    "tienda":{
        "id" : 1,
        "precio": "10.5€"
    },
    "tienda":{
        "id" : 2,
        "precio": "9.5$"
    }
}
```
**Ejemplo JSON Error**

Salida:
HTTP/1.1 404 Not found

**POST `/products` -Añadir un producto** <br>
Json entrada
```json
{
    "nombre": "Pizza con piña"
}
```

Json Respuesta

```json
{
    "id": 10,
    "nombre": "Pizza con piña"
}
```
**Ejemplo  Error**
Salida:
HTTP/1.1 409 Conflict
```json

{
    "error": "El producto ya existe",
}
```

**DELETE `/products` -Dar de baja un producto** <br>
Json entrada
```json
{
    "id": 3
}
```
Salida:
HTTP/1.1 200 OK

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found

**PUT `/products/{idProduct}` -Modificar información del producto** <br>
Json entrada
```json
{
    "nombre": "Pizza con piña y chocolate"
}
```
Salida:
HTTP/1.1 200 OK

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found

**GET `/products?=` -Listado ( se puede aplicar filtros )** <br>
Ejemplo: 
GET /products?name=agua&price_min=9
```json
[
      {
        "id": 2,
        "nombre": "Agua",
        "tienda": {
          "id": 1,
          "precio": "10.5€"
        },
        "tienda": {
          "id": 2,
          "precio": "9.5€"
        }
      },
    
    {
        "id": 4,
        "nombre": "Agua con gas",
        "tienda":{
            "id" : 1,
            "precio": "14€"
        },
        "tienda":{
            "id" : 5,
            "precio": "20$"
        }
    }
]
```

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found

### **Tienda** ###
**GET `/shop/{idShop}` -Obtener de la tienda  (id, ubicación, Número de  productos )** <br>
```json
{
    "id_ubicacion": "E3",
    "país": "España",
    "ciudad": "Coruña",
    "direccion": "Av. Finisterre",
    "Número de productos": 30
}
```
**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found


**POST `/shop` -Añadir una tienda** <br> 
Creas un ID para la ubicación. 
Json entrada 
```json
{
    "id_ubicacion": "E3",
    "pais": "España",
    "ciudad": "Santiago",
    "direccion":"Av Buenos Aires"
}
```
Salida:
HTTP/1.1 200 OK
**POST `/shop/addProduct/{idProducto}` -Añadir un producto a una tienda** <br> 
Json entrada 
```json
{
    "id_ubicacion":E3,
    "precio": "10.5€"
}
```
Salida:
HTTP/1.1 200 OK
**DELETE `/shop` -Dar de baja una tienda** <br> 
Json entrada
```json
{
    "id": "E3"
}
```
Salida:
HTTP/1.1 200 OK


**PUT `/shop/{idShop}` -Modificar tienda** <br>
Json entrada 
```json
{
    "id_ubicacion": "E3",
    "pais": "España",
    "ciudad":" A Coruña",
    "direccion": "Av de Arteixo 6"
}
```
Salida:
HTTP/1.1 200 OK

**PATCH `/shop/{idShop}` -Modificar tienda** <br>
Json entrada 
```json
{
    "id_ubicacion": "E3",
    "ciudad":" Santiago",
}
```
Salida:
HTTP/1.1 200 OK

**PATCH `/shop/{idShop}/products/{idProduct}/price` -Modificar precio del producto desde cada tienda** <br>
Json entrada
```json
{
   "precio" : "14.5€"
}
```

Salida:
HTTP/1.1 200 OK

**GET `/shop?=` -Listado ( se puede aplicar filtros )** <br>
Ejemplo
Get /shop?pais=España&products_min=10
```json
[
    {
        "id": 4,
        "id_ubicacion": 1,
        "pais": "España",
        "ciudad": "Coruña",
        "direccion": "Av. Finisterre",
        "Número de productos": 30
    },
    
    {
        "id": 6,
        "id_ubicacion": 1,
        "pais": "España",
        "ciudad": "Santiago",
        "direccion": "Av Orense",
        "Número de productos": 20
    }
]
```

