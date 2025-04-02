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

## Estructura BD ##

### Tienda ###

    CREATE TABLE Tienda(
    id int auto_increment primary key,
    id_ubicacion varchar(5) not null,
    id_producto int auto_increment,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) ON DELETE CASCADE
    FOREIGN KEY (id_ubicacion) REFERENCES Ubicacion(id_ubicacion) ON DELETE CASCADE
    );

### Ubicación ###
    CREATE TABLE Ubicacion(
    id_ubicacion varchar(5) primary key,
    pais varchar(50) not null,
    ciudad varchar(50) not null;
    direccion varchar(50) not null,
    );

### Producto ###
    CREATE TABLE Productos(
    id_producto int auto_increment primary key,
    nombre varchar(30) not null
    );

### PrecioProducto ###
    CREATE TABLE PrecioProducto(
    id_producto int auto_increment primary key,
    id_ubicacion varchar(5) primary key,
    precio DECIMAL(10, 2),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) ON DELETE CASCADE
    FOREIGN KEY (id_ubicacion) REFERENCES Ubicacion(id_ubicacion) ON DELETE CASCADE
    )


## ENDPOINTS ##

### Productos ###
**-Obtener datos  del  producto (id, nombre, tiendas en donde está y su precio respectivamente)** <br>
**-Añadir un producto** <br>
**-Dar de baja un producto** <br>
**-Modificar información del producto** <br>
**-Listado ( se puede aplicar filtros )** <br>

## **Tienda:** ##
**-Obtener de la tienda  (id, ubicación, cantidad productos )** <br>
**-Añadir una tienda** <br> 
**-Dar de baja una tienda** <br> 
**-Modificar tienda** <br>
**-Listado ( se puede aplicar filtros )** <br>












