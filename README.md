# 🛒 Proyecto - Gestión de Productos y Tiendas  

Este proyecto proporciona una API REST para la gestión de productos y tiendas. Permite realizar operaciones CRUD sobre productos y tiendas, así como filtrado y modificaciones específicas.  

---

## Funcionalidades ##
### 📦 Productos  
- **Alta de productos** → Permite añadir nuevos productos a la base de datos.  
- **Baja de productos** → Elimina un producto de la base de datos.  
- **Modificación de productos** → Permite modificar los valores de un producto.  
- **Filtrar productos** → Permite buscar productos por **nombre** y **precio**.  


### 🏬 Tienda  
- **Alta de tienda** → Permite registrar una nueva tienda.  
- **Baja de tienda** → Elimina una tienda existente.  
- **Modificación de tienda** → Permite actualizar los datos de una tienda.  
- **Añadir producto a tienda** → Asigna un producto a una tienda específica.  
- **Filtrar tiendas** → Permite buscar tiendas por **nombre, país y ubicación**.  

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
{
    "id": 10,
    "nombre": "Pizza con piña",
    "tienda":{
        "id" : 1,
        "precio": "10.5€"
    }
}
]
```
#### Obtener producto por ID  
```
http
GET /products/{idProduct}
```
Ejemplo JSON respuesta
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
Ejemplo  Error
```
Salida:
HTTP/1.1 404 Not found
```

#### Añadir un producto
```
http
POST /products
```
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
```
HTTP/1.1 409 Conflict
```
#### Borrar un producto
```
http
DELETE /products
```
Json entrada

```json
{
    "id": 3
}
```

Salida:
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
PUT `/products/{idProduct}
```

Json entrada
```json
{
    "nombre": "Pizza con piña y chocolate"
}
```
Salida:
```
HTTP/1.1 200 OK
```
Ejemplo  Error
```
HTTP/1.1 404 Not found
```
#### Filtro de productos
```
http
GET /products
```

Ejemplo busqueda: 
```
GET /products?name=agua&price_min=9
```
Salida
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

Ejemplo  Error
```
HTTP/1.1 404 Not found
```

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
Creas un ID para la ubicación. <br> 
Json entrada 
```json
{
    "id_ubicacion": "E3",
    "pais": "España",
    "ciudad": "Santiago",
    "direccion":"Av Buenos Aires"
}
```
Salida:<br> 
Status: 201 Created <br> 
**POST `/shop/addProduct/{idProducto}` -Añadir un producto a una tienda** <br> 
Json entrada 
```json
{
    "id_ubicacion": "E3",
    "precio": "10.5€"
}
```
Salida:
HTTP/1.1 200 OK

**Ejemplo  Error**
Salida:
HTTP/1.1 409 Conflict

**DELETE `/shop` -Dar de baja una tienda** <br> 
Json entrada
```json
{
    "id": "E3"
}
```
Salida:
HTTP/1.1 200 OK

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found



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

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found

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

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found



**PATCH `/shop/{idShop}/products/{idProduct}/price` -Modificar precio del producto desde cada tienda** <br>
Json entrada
```json
{
   "precio" : "14.5€"
}
```

Salida:
HTTP/1.1 200 OK

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found



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

**Ejemplo  Error**
Salida:
HTTP/1.1 404 Not found



