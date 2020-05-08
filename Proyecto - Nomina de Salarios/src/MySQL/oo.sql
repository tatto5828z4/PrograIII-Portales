create database parcial;
use parcial;

create table cliente
(
id_cliente int primary key,
nombre_cliente varchar(50)not null,
apellido_cliente varchar(50)not null,
edad int not null,
direccion varchar (50)
)engine = InnoDB;

create table producto
(
id_producto int primary key,
nombre_producto varchar(25)not null,
precio_producto float not null,
stock_producto int not null
)engine = innoDB;

create table factura
(
id_factura int primary key,
fecha_factura date,
total float not null,
id_cliente int,
foreign key (id_cliente) references cliente(id_cliente)
)engine = InnoDB;

create table detallefact
(
id_detalle int primary key,
cantidad int not null,
id_factura int,
id_producto int,
precio_producto float,
subtotal float not null,

foreign key(id_factura) references factura (id_factura),
foreign key(id_producto) references producto (id_producto)
)engine = InnoDB;

