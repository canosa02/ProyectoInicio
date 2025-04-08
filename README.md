# 🛒 Proyecto - Gestión de Productos y Tiendas  

Este proyecto proporciona una API REST para la gestión de productos y tiendas. Permite realizar operaciones CRUD sobre productos y tiendas, así como filtrado y modificaciones específicas.  

---

## Funcionalidades ##
### 📦 Productos  
- **Alta de productos** → Permite añadir nuevos productos a la base de datos.  
- **Baja de productos** → Elimina un producto de la base de datos.  
- **Modificación de productos** → Permite modificar los valores de un producto.  
- **Filtrar productos** → Permite buscar productos por **name** y **precio**.  


### 🏬 Tienda  
- **Alta de tienda** → Permite registrar una nueva .  
- **Baja de tienda** → Elimina una  existente.  
- **Modificación de tienda** → Permite actualizar los datos de una tienda.  
- **Añadir un producto a tienda** → Asigna un  a una tienda específica.  
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
Ejemplo  Error
```
HTTP/1.1 404 Not found
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
    "productId": 10,
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
    "message": "El campo comida no existe"
}
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
**Ejemplo  Error**
```
HTTP/1.1 400 Bad request
```
Json Respuesta

```json
{
    "message": "El campo nombre no existe"
}
```
#### Filtro de productos
```
http
GET /products
```

Ejemplo busqueda: 
```
GET /products?name=agua&priceMin=9
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
    "address": "Av. Finisterre",
    "Number of products": 30
}
```
Ejemplo  Error
```
HTTP/1.1 404 Not found
```
#### Crear una tienda

```
http
POST `/shop`
```

Json entrada 
```json
{
    "shopId": "1",
    "locationId": "E3",
    "country": "España",
    "city": "Santiago",
    "address":"Av Buenos Aires"
}
```

Salida
```
Status: 201 Created 
```
**Ejemplo error**
```
HTTP/1.1 400 Bad request
```
Json Respuesta

```json
{
    "message": "El campo denominación no existe"
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
**Ejemplo  Error**
```
HTTP/1.1 400 Bad request
```
Json Salida 
```json
{
    "message": "El campo Coste no existe"
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
    "message": "El campo calle no existe"
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
    "message": "El campo id_ubicacion no existe"
}
```
#### Actualizar el precio del producto
```
http
PATCH /shop/{shopId}/products/{productId}/price
```
Json entrada
```json
{
   "precio" : 14.5
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
    "message": "El campo coste no existe"
}
```
#### Listado con filtros de tiendas 
```
http
GET /shop?=
```
Ejemplo
```
Get /shop?country=España&products_min=10
```
Json respuesta
```json
[
    {
        "shopId": 4,
        "locationId": 1,
        "country": "España",
        "city": "Coruña",
        "address": "Av. Finisterre",
        "Number of products": 30
    },
    
    {
        "shopId": 6,
        "locationId": 1,
        "country": "España",
        "city": "Santiago",
        "address": "Av Orense",
        "Number of products": 20
    }
]
```
Ejemplo  Error
```
HTTP/1.1 404 Not found
```



