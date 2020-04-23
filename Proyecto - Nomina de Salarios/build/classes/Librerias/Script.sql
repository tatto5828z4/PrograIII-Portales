-- create database nomina;
use nomina;
create table Usuarios(
    ID int primary key auto_increment not null,
	Username varchar(50) not null,
    Password_Usuario varchar(50) not null,
    HostUsuario varchar(50)  not null,
    IP varchar(50)  not null,
    Correo varchar(50) not null
    
)Engine= InnoDB, default char set= latin1;


