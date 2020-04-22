create database Nomina_de_Empleados;
use Nomina_de_Empleados;
-- Drop database Nomina_de_Empleados;

create table Concepto(
ID_Concepto varchar(10) primary key,
NombreConcepto varchar(60),
IngresarUser boolean, 
ModificarUser boolean, 
EliminarUser boolean, 
ConsultarUser boolean,
IngresarUserTipo boolean, 
ModificarUserTipo boolean, 
EliminarUserTipo boolean, 
ConsultarUserTipo boolean,
IngresarEmpleado boolean,
ModificarEmpleado boolean,
EliminarEmpleado boolean,
ConsultarEmpleado boolean,
IngresarDepartamento boolean,
ModificarDepartamento boolean,
EliminarDepartamento boolean,
ConsultarDepartamento boolean,
IngresarConcepto boolean,
ModificarConcepto boolean,
EliminarConcepto boolean,
ConsultarConcepto boolean,
Tabla boolean, 
Barras boolean, 
Pastel boolean
)engine = InnoDB;

create table Usuarios(
ID_Usuario varchar(10) primary key,
ID_Concepto varchar(10),
NombreUsuario varchar(60) not null,
Contraseña varchar(60) not null,
CorreoElectronico varchar(60) not null,
Telefono varchar(60),
Direcciones varchar(60),
Sesiones varchar(60),
foreign key (ID_Concepto) references Concepto(ID_Concepto)
ON UPDATE CASCADE
ON DELETE SET NULL
)engine = InnoDB;

-- select * from Concepto;
-- select * from Usuarios;

-- Partes anteriores de la BD
create table Concepto_Planilla
(
	id_conceptoPlanilla int primary key,
	nombre_concepto varchar(20) not null,
	tipo_concepto varchar (10) not null,
	clase_concepto varchar(25) not null,
	Valor_concepto  float not null,
	-- valor float not null,
	aplicacion_concepto varchar(20) not null,
    ID_Usuario varchar(10),
    
    foreign key (ID_Usuario) references Usuarios(ID_Usuario)
    ON UPDATE CASCADE
    ON DELETE SET NULL
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
ID_Usuario varchar(10),
    
-- claves foraneas
foreign key (id_puesto) references Puesto(id_puesto),
foreign key (id_departamento) references Departamento(id_departamento),
foreign key (ID_Usuario) references Usuarios(ID_Usuario)
ON UPDATE CASCADE
ON DELETE SET NULL
)engine = InnoDB;

create table PlanillaDet
(
id_planillaDet int primary key,
id_conceptoPlanilla int ,
id_empleado int,
valor_conceptoDet float,
foreign key(id_conceptoPlanilla) references Concepto_Planilla(id_conceptoPlanilla),
foreign key(id_empleado) references Empleado(id_empleado)
ON UPDATE CASCADE
ON DELETE CASCADE
)engine InnoDB;

create table PlanillaGen
(
id_planillaGen int primary key,
id_empleado int,
total_percepsion float not null,
total_deduccion float not null,
total_liquido  float not null,
foreign key(id_empleado) references Empleado(id_empleado)
ON UPDATE CASCADE
ON DELETE CASCADE
)engine InnoDB;