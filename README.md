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
Nos permite añadir el producto deseado a  la tienda que queramos

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
**POST `/products/new` -Añadir un producto** <br>
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
**DELETE `/products/delete/{idProduct}` -Dar de baja un producto** <br>
Entrada:

DELETE /products/delete/10

Salida:
HTTP/1.1 200 OK
{
  "message": "El producto ha sido eliminado"
}

**PUT/PATCH `/products/{idProduct}/modify` -Modificar información del producto** <br>
Json entrada
```json
{
    "nombre": "Pizza con piña y chocolate"
}
```

Json Respuesta

```json
{
   "message": "Producto modificado"
}
```

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
            "id" : 5
            "precio": "20$"
        }
    }
]
```

### **Tienda** ###
**GET `/shop/{idShop}` -Obtener de la tienda  (id, ubicación, Número de  productos )** <br>
```json
{
    "id": 4,
    "id_ubicacion": 1,
    "país": "España",
    "ciudad": "Coruña",
    "direccion": "Av. Finisterre",
    "Número de productos": 30
}
```
**POST `/shop/add` -Añadir una tienda** <br> 
Json entrada 
```json
{
    "id_ubicacion": "E3",
    "pais": "España",
    "ciudad": "Santiago",
    "direccion":"Av Buenos Aires"
}
```
Json salida 
```json
{
    "message" : "Tienda creada correctamente"
}
```
// Duda -> Está bien estructurado de forma que para dar un alta solo haya que agregar la ubicación de esta?
**POST `/shop/addProduct/{idProducto}` -Añadir un producto a una tienda** <br> 
Json entrada 
```json
{
    "id_producto": 10,
    "id_ubicacion": 2,
    "precio": "10.5€"
}
```
Json salida 
```json
{
    "message" : "Precio añadido correctamente"
}
```
**DELETE `/shop/delete/{idShop}` -Dar de baja una tienda** <br> 
Entrada:
DELETE /shop/delete/10

Salida:
HTTP/1.1 200 OK
{
  "message": "La tienda se ha borrado correctamente"
}



**PUT/PATCH `/shop/modify` -Modificar tienda** <br>
Json entrada 
```json
{
    "id": 10,
    "id_ubicacion": 2,
    "direccion": "Av de Arteixo 6"
}
```
Json salida 
```json
{
    "message" : "Actualizada correctamente"
}
```

**PUT/PATCH `/shop/{idShop}/products/{idProduct}/price` -Modificar precio del producto desde cada tienda** <br>
Json entrada
```json
{
   "precio" : "14.5€"
}
```

Json Respuesta

```json
{
   "message": "Precio del producto  modificado"
}
```

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



Duda: Endpoints como delete, donde solo se necesita borrar mediante el id, es mejor hacerlo por url o pasar esta mediante json por estanderizar? 


