CREATE DATABASE IF NOT EXISTS Proyecto1;

USE Proyecto1;


CREATE TABLE productos (
    id_producto SERIAL primary key,
    nombre varchar(30) not null
);

CREATE TABLE ubicacion(
    id_ubicacion varchar(5) primary key,
    pais varchar(50) not null,
    ciudad varchar(50) not null,
    direccion varchar(50) not null
);

CREATE TABLE tienda (
    id SERIAL primary key,
    id_ubicacion varchar(5) not null,
    FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion) ON DELETE CASCADE
);

CREATE TABLE precioProducto(
    id_producto int  not null,
    id_ubicacion varchar(5) not null,
    precio varchar(15) not null,
    primary key (id_producto,id_ubicacion),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE,
    FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion) ON DELETE CASCADE
);

INSERT INTO ubicacion(id_ubicacion, pais, ciudad, direccion) values
('A1', 'Argentina', 'Buenos Aires',  'Alfredo R. Bufano 2701-2799'),
('A2', 'Argentina', 'Córdoba', 'Av. Vélez Sarsfield'),
('E1', 'España', 'A Coruña', 'Rúa Río Brexa, 5 '),
('F1', 'Francia', 'Marsella', 'Grands-Carmes 13002 Marsella'),
('P1', 'Portugal', 'Lisboa', 'Rua da Bempostinha 1');

INSERT INTO tienda(id_ubicacion) values
('A1'),
('A2'),
('E1'),
('F1'),
('P1');

INSERT INTO productos(nombre) values
('Zumos'),
('Bebidas energéticas'),
('Agua'),
('Patatas'),
('Fruta');

INSERT INTO precioProducto (id_producto, id_ubicacion, precio) VALUES
(1, 'A1', 7.9),
(2, 'A2', 10.0),
(3, 'E1', 3.9),
(4, 'F1', 5.5),
(5, 'P1', 3.2);
    