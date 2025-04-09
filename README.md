# 🛒 Proyecto - Gestión de Productos y Tiendas  

Este proyecto proporciona una API REST para la gestión de productos y tiendas. Permite realizar operaciones CRUD sobre productos y tiendas, así como filtrado y modificaciones específicas.  

---

## Funcionalidades ##
### 📦 Productos  
- **Alta de productos** → Permite añadir nuevos productos a la base de datos.  
- **Baja de productos** → Elimina un producto de la base de datos.  
- **Modificación de productos** → Permite modificar los valores de un producto.  
- **Filtrar productos** → Permite buscar productos por **name** y **price**.  


### 🏬 Tienda  
- **Alta de tienda** → Permite registrar una nueva .  
- **Baja de tienda** → Elimina una  existente.  
- **Modificación de tienda** → Permite actualizar los datos de una tienda.  
- **Añadir un producto a tienda** → Asigna un producto a una tienda específica.  
- **Filtrar tiendas** → Permite buscar tiendas por **name, país y ubicación**.  

---



## ENDPOINTS ##

### Productos 
#### Obtener los productos 
```
http
GET /products
```
Ejemplo JSON respuesta
```json
[
  {
    "productId": 2,
    "name": "Agua",
    "shop": [
      {
        "shopId": 1,
        "price": 10.5
      },
      {
        "shopId": 2,
        "price": 9.5
      }
    ]
  },
  {
    "productId": 10,
    "name": "Pizza con piña",
    "shop": [
      {
        "shopId": 1,
        "price": 10.5
      }
    ]
  }
]

```
#### Obtener producto por ID  
```
http
GET /product/{productId}
```
Ejemplo JSON respuesta
```json
{
    "productId": 2,
    "name": "Agua",
    "shop":[
        {
            "shopId" : 1,
            "price": 10.5
        },
        {
            "shopId" : 2,
            "price": 9.5
        }
    ]
}
```
Ejemplo Producto no existente
```
HTTP/1.1 200 OK
```
```json
[]
```

#### Añadir un producto
```
http
POST /product
```
Json entrada
```json
{
    "name": "Pizza con piña"
}
```

Json Respuesta
```json

    {
    "productId":1,
    "name": "Pizza con piña"
}
```
**Ejemplo  Error**
```
HTTP/1.1 409 Conflict
```

```json
  "message": "That product alredy exists"
```

#### Borrar un producto
```
http
DELETE /product/{productId}
```
Respuesta:
```
HTTP/1.1 200 OK
```
Ejemplo  Error
```
HTTP/1.1 404 Not found
```
#### Actualizar producto 
```
http
PUT `/product/{productId}
```

Json entrada
```json
{
    "name": "Pizza con piña y chocolate"
}
```
Respuesta:
```
HTTP/1.1 200 OK
```
```json

    {
    "productId":1,
    "name": "Pizza con piña"
}
```
**Ejemplo  Error**
```
HTTP/1.1 400 Bad request
```
Json Respuesta

```json
{
    "message": "The field doesn`t exist"
}
```
#### Filtro de productos
```
http
GET /products/filter?=
```

Ejemplo busqueda: 
```
GET /product/filter?name=agua&priceMin=9
```
Salida
```json
[
      {
        "productId": 2,
        "name": "Agua",
        "shop": [
              {
              "shopId": 1,
              "price": 10.5
            },
            {
              "shopId": 2,
              "price": 9.5
            }
        ]  
      },
    
    {
        "productId": 4,
        "name": "Agua con gas",
        "shop":[
            {
                "shopId" : 1,
                "price": 14
            },
            {
                "shopId" : 5,
                "price": 20
            }
        ]
    }
]
```

Ejemplo  Error
```
HTTP/1.1 404 Not found
```

### **Tienda** ###
#### Obtener todas las tiendas

```
http
GET /shops
```
Ejemplo salida
```json
[
    {
        "shopId": 1,
        "locationId": "E2",
        "country": "España",
        "city": "A Coruña",
        "address": "Los Mallos, 10"
    },
    {
        "shopId": 2,
        "locationId": "A2",
        "country": "Argentina",
        "city": "Buenos Aires",
        "address": "Dirección inventada"
    },
    {
        "shopId": 3,
        "locationId": "E3",
        "country": "España",
        "city": "Santiago",
        "address": "Av. Toledo"
    }
]
```
#### Obtener una tienda por id

```
http
GET /shop/{shopId}
```
Ejemplo salida
```json
{
    "shopId": 1,
    "locationId": "E3",
    "country": "España",
    "city": "Coruña",
    "address": "Av. Finisterre"
}
```
Ejemplo si no encuentra tiendas
```
HTTP/1.1 200 OK
```

```json
[]
```
#### Crear una tienda

```
http
POST `/shop`
```

Json entrada 
```json
{
    "locationId": "E3",
    "country": "España",
    "city": "Santiago",
    "address":"Av Buenos Aires"
}
```
Salida
```
Status: 200 OK 
```
```json
{
    "shopId": 1,
    "locationId": "E3",
    "country": "España",
    "city": "Santiago",
    "address":"Av Buenos Aires"
}
```
**Ejemplo error**
```
HTTP/1.1 409 Conflict
```
Json Respuesta

```json
{
    "message": "That locationId alredy exists"
}
```
#### Añadir un producto a la tienda 

```
http
POST /shop/addProduct/{productId}
```
Json entrada 
```json
{
    "locationId": "E3",
    "price": 10.5
}
```
Respuesta
```
HTTP/1.1 200 OK
```
```json
{
    "productId" : 1,
    "locationId": "E3",
    "price": 10.5
}
```
**Ejemplo  Error**
```
HTTP/1.1 409 Conflict
```
Json Salida 
```json
{
    "message": "The product alredy exists"
}
```
#### Dar de baja una tienda
```
http
DELETE /shop/{shopId}
```

Salida:
```
HTTP/1.1 200 OK
```

Ejemplo  Error

```
HTTP/1.1 404 Not found
```
#### Actualizar tienda
```
http
PUT `/shop/{shopId}
```

Json entrada 
```json
{
    "locationId": "E3",
    "country": "España",
    "city":" A Coruña",
    "address": "Av de Arteixo 6"
}
```
Salida:
```
HTTP/1.1 200 OK
```

```json
{
    "shopId": 5,
    "locationId": "C9",
    "country": "Colombia",
    "city": "A Coruña",
    "address": "Los Mallos, 12"
}
```

Ejemplo  Error
Salida
```
HTTP/1.1 404 Not found
```
Ejemplo error
```
HTTP/1.1 400 Bad request
```
Json salida 
```json
{
    "message": "The field doesn´t exist"
}
```
#### Actualizar parcialmente la tienda 
```
http
PATCH /shop/{shopId}
```
Json entrada 
```json
{
    "locationId": "E3",
    "city":" Santiago"
}
```
Salida:
```
HTTP/1.1 200 OK
```
```json

```
Ejemplo  Error
Salida
```
HTTP/1.1 404 Not found
```
Ejemplo error
```
HTTP/1.1 400 Bad request
```
Json salida 
```json
{
    "message": "The field doesn´t exist"
}
```
#### Actualizar el precio del producto
```
http
PATCH /shop/{shopId}/product/{productId}
```
Json entrada
```json
{
   "price" : 14.5
}
```

Salida:
```
HTTP/1.1 200 OK
```
Ejemplo  Error
Salida
```
HTTP/1.1 404 Not found
```
Ejemplo error
```
HTTP/1.1 400 Bad request
```
Json salida 
```json
{
    "message": "The field doesn´t exist"
}
```
#### Listado con filtros de tiendas 
```
http
GET /shop/filter?=
```
Ejemplo
```
Get /shop/filter?country=España&products_min=10
```
Json respuesta
```json
[
    {
        "shopId": 4,
        "locationId": 1,
        "country": "España",
        "city": "Coruña",
        "address": "Av. Finisterre"
    },
    
    {
        "shopId": 6,
        "locationId": 1,
        "country": "España",
        "city": "Santiago",
        "address": "Av Orense"
    }
]
```
Ejemplo  Error
```
HTTP/1.1 404 Not found
```



