
create database Nomina_de_Empleados;
use Nomina_de_Empleados;

create table Concepto(
ID_Concepto varchar(10) primary key,
NombreConcepto varchar(60),
IngresarUser boolean, ModificarUser boolean, EliminarUser boolean, ConsultarUser boolean,
IngresarUserTipo boolean, ModificarUserTipo boolean, EliminarUserTipo boolean, ConsultarUserTipo boolean,
IngresarEmpleado boolean,ModificarEmpleado boolean,EliminarEmpleado boolean,ConsultarEmpleado boolean,
IngresarDepartamento boolean,ModificarDepartamento boolean,EliminarDepartamento boolean,ConsultarDepartamento boolean,
IngresarConcepto boolean,ModificarConcepto boolean,EliminarConcepto boolean,ConsultarConcepto boolean,
Tabla boolean, Barras boolean, Pastel boolean
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

select * from Concepto;
select * from Usuarios;