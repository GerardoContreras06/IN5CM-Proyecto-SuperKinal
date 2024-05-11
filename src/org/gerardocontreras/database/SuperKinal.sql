drop database if exists SuperDB;

create database if not exists SuperDB;

use SuperDB;

create table Clientes(
	clienteId int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono varchar(15) not null,
    direccion varchar(200) not null,
    nit varchar(15) not null,
    primary key PK_clienteId (clienteId)
);

create table Cargos(
	cargoId int not null auto_increment,
    nombreCargo varchar(30),
    descripcionCargo varchar(100),
    primary key PK_cargoId (cargoId)
);
    
create table Empleados(
	empleadoId int not null auto_increment,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    sueldo decimal(10,2) not null,
    horaEntrada time not null,
    horaSalida time not null,
    cargoId int not null,
    encargadoId int,
    primary key PK_empleadoId (empleadoId),
	constraint FK_Empleados_Cargos foreign key (cargoId)
		references Cargos(cargoId),
	constraint Encargad foreign key (encargadoId)
		references Empleados(empleadoId)
);

create table Facturas(
	facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
    total decimal(10,2),
    clienteId int not null,
    empleadoId int not  null,
    primary key PK_facturaId (facturaId),
    constraint FK_Facturas_Clientes foreign key (clienteId)
		references Clientes(clienteId),
	constraint FK_Facturas_Empleados foreign key (empleadoId)
		references Empleados(empleadoId)
);

create table Ticketsoportes(
	ticketsoporteId int not null auto_increment,
    descripcionTicket varchar(250) not null,
    estatus varchar(30) not null,
    clienteId  int not null,
    facturaId int not null,
    primary key PK_ticketsoporteId (ticketsoporteId),
    constraint FK_Ticketsoportes_Clientes foreign key (clienteId)
		references Clientes(clienteId),
	constraint FK_Ticketsoporte_Facturas foreign key (facturaId)
		references Facturas(facturaId)
);

create table Distribuidores(
	distribuidorId int not null auto_increment,
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200) not null,
    nitDistribuidor varchar(15) not null,
    telefonoDistribuidor varchar(15) not null,
    web varchar(50) not null,
    primary key PK_distribuidorId (distribuidorId)
);

create table Categoriaproductos(
	categoriaProductoId int not null auto_increment,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null,
    primary key PK_categoriaProductoId (categoriaProductoId)
);


create table Productos(
	productoId int not null auto_increment,
    nombreProducto varchar(50) not null,
    descripcionProducto varchar(100) not null,
    cantidadStock int,
    precioVentaUnitario decimal(10,2) not null,
    precioVentaMayor decimal(10,2) not null,
    precioCompra decimal(10,2) not null,
    imagenProducto blob,
    distribuidorId int not null,
    categoriaProductoId int not null,
    primary key PK_productoId (productoId),
    constraint FK_Productos_Distribuidores foreign key (distribuidorId)
		references Distribuidores(distribuidorId),
	constraint FK_Productos_Categoriaproductos foreign key (categoriaProductoId)
		references Categoriaproductos(categoriaProductoId)
);

create table Promociones(
	promocionId int not null auto_increment,
    precioPromocion decimal(10,2) not null,
    descripcionPromocion varchar(100) not null,
    fechaInicio date,
    fechaFinalizacion date,
    productoId int not null,
    primary key PK_promocionId (promocionId),
    constraint FK_Promociones_Productos foreign key (productoId)
		references Productos(productoId)
);

create table Compras(
	compraId int not null auto_increment,
    fechaCompra date,
    totalCompra decimal(10,2) not null,
    primary key PK_compraId (compraId)
);

create table Detallefacturas(
	detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    primary key PK_detalleFacturaId (detalleFacturaId),
    constraint FK_DetalleFacturas_Facturas foreign key (facturaId)
		references Facturas(facturaId),
	constraint FK_DetalleFacturas_Productos foreign key (productoId)
		references Productos(productoId)
);

create table Detallecompras(
	detalleCompraId int not null auto_increment,
    cantidadCompra int,
    productoId int not null,
    compraId int not null,
    primary key PK_detalleCompraId (detalleCompraId),
    constraint FK_DetalleCompras_Productos foreign key (productoId)
		references Productos(productoId),
	constraint FK_Detallecompras_Compras foreign key (compraId)
		references Compras(compraId)
);        
    
insert into Clientes(nombre, apellido, telefono, direccion, nit) values
	('Luis', 'Cuxun', '1234-1234', 'Ciudad', '715683-0'),
    ('Alejandro', 'Carrillo', '4123-4123', 'Ciudad', '578426-9'),
    ('Jesus', 'Sis', '4535-6666', 'Ciudad', '724586-3');
    
select * from Clientes;