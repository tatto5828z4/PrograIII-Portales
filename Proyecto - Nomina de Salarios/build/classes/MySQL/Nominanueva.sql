create database Nomina_sueldos;
-- drop database nomina_sueldos;
use Nomina_sueldos;


create table Concepto
(
codigo_concepto int primary key,
nombre_concepto varchar(20) not null,
tipo_concepto varchar (10) not null,
clase_concepto varchar(25) not null,
valor float not null,
aplicacion_concepto varchar(20) not null
)engine = InnoDB;


create table Departamento
(
id_departamento int primary key,
nombre_departamento varchar (25) not null,
estado char(1) not null
)engine=InnoDB;

create table Puesto
(
id_puesto int primary key,
nombre_puestos varchar(25) not null,
estado char(1) not null
)engine=InnoDB;

create table Empleado
(
id_empleado int primary key,
nombre_empleado varchar (50) not null,
apellido_empleado varchar(50) not null,
dpi_empleado int8 not null,
telefono_empleado int not null,
estado_empleado char(1) not null,
sueldo float not null,
id_puesto int,
id_departamento int,

-- claves foraneas
foreign key (id_puesto) references Puesto(id_puesto),
foreign key (id_departamento) references Departamento(id_departamento)
)engine = InnoDB;

create table PlanillaDet
(
id_planilla int PRIMARY key,
total_liquido float not null,
total_planilla float not null,
id_empleado int,
codigo_concepto int,

foreign key (id_empleado) references Empleado(id_empleado),
foreign key (codigo_concepto) references Concepto(codigo_concepto)
)engine=InnoDB;