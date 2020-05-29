-- Para cambiar la BD a forma local, buscar el manual de inicio rapido
-- Si utilizara la BD en la nube, el instalador ya tiene los permisos para que pueda ingresar
-- el usuario por defecto es Admin y password:12345
-- Los insert se encuentran en la parte final 


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
	tipo_concepto varchar (20) not null,
	clase_concepto varchar(25) not null,
	Valor_concepto  float not null,
	-- valor float not null,
	
    ID_Usuario varchar(10),
    
    foreign key (ID_Usuario) references Usuarios(ID_Usuario)
    ON UPDATE CASCADE
    ON DELETE SET NULL
)engine = InnoDB;

insert into Concepto_Planilla values ("1","IGGS","Porcentaje","Deducccion","4.83",null);
insert into Concepto_Planilla values ("2","ISR1","Porcentaje","Deducccion","0.05", null);
insert into Concepto_Planilla values ("3","ISR2","Porcentaje","Deducccion","0.06",null);
insert into Concepto_Planilla values ("4","ISR3","Porcentaje","Deducccion","0.08",null);

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
id_planillaDet int primary key auto_increment,
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
id_planillaGen int primary key auto_increment,
id_empleado int,
total_percepsion float not null,
total_deduccion float not null,
total_liquido  float not null,
foreign key(id_empleado) references Empleado(id_empleado)
ON UPDATE CASCADE
ON DELETE CASCADE
)engine InnoDB;

create table actividad(
	titulo_actividad varchar(250),
    descripcion_actividad varchar (250),
    tipo_actividad varchar(250),
    fecha_actividad varchar(250),
    prioridad_actividad varchar(250)
)ENGINE = InnoDB DEFAULT CHARSET=latin1;

-- Insert

insert into Concepto values	(1, "Administrador", 1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
insert into Usuarios values (1, 1, "Administrador", 12345, "Admin@gmail.com", 123456, "192.168.0.110 DESKTOP-D6C55T8", 0);
insert into Puesto values (1, "Informatica", "A");
insert into Puesto values (2, "Representante Legal", "A");

insert into Departamento values (1, "Bases de Datos", "A" );
insert into Empleado values (1, "Josue", "Zapata", 299178420,123456, "A", 9000, 1, 1, 1);

insert into Empleado values (2, "Juan", "Lopez", 2856455, 56585222, "A", 15000, 2, 1, 1);

