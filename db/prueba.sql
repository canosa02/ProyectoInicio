CREATE DATABASE IF NOT EXISTS Proyect1;

USE Proyect1;


CREATE TABLE products (
    productId SERIAL primary key,
    name varchar(30) not null
);

CREATE TABLE location(
    locationId varchar(5) primary key,
    country varchar(50) not null,
    city varchar(50) not null,
    address varchar(50) not null
);

CREATE TABLE shop (
    shopId SERIAL primary key,
    locationId varchar(5) not null,
    FOREIGN KEY (locationId) REFERENCES location(locationId) ON DELETE CASCADE
);

CREATE TABLE productPrice(
    productId int not null,
    locationId varchar(5) not null,
    price varchar(15) not null,
    primary key (productId, locationId),
    FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE CASCADE,
    FOREIGN KEY (locationId) REFERENCES location(locationId) ON DELETE CASCADE
);

INSERT INTO location(locationId, country, city, address) values
('A1', 'Argentina', 'Buenos Aires',  'Alfredo R. Bufano 2701-2799'),
('A2', 'Argentina', 'Córdoba', 'Av. Vélez Sarsfield'),
('E1', 'España', 'A Coruña', 'Rúa Río Brexa, 5 '),
('F1', 'Francia', 'Marsella', 'Grands-Carmes 13002 Marsella'),
('P1', 'Portugal', 'Lisboa', 'Rua da Bempostinha 1');

INSERT INTO shop(locationId) values
('A1'),
('A2'),
('E1'),
('F1'),
('P1');

INSERT INTO products(name) values
('Zumos'),
('Bebidas energéticas'),
('Agua'),
('Patatas'),
('Fruta');

INSERT INTO productPrice (productId, locationId, price) VALUES
(1, 'A1', 7.9),
(1, 'A2', 8.9),
(2, 'A2', 10.0),
(3, 'E1', 3.9),
(4, 'F1', 5.5),
(5, 'P1', 3.2);
    
