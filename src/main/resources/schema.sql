  
create table if not exists Users(

    idUsers varchar(4) not null AUTO_INCREMENT,
    NIP varchar(11) not null,
    eMail varchar(25) not null,
    password varchar(65) not null,
    companyName varchar(45) not null,
    REGON varchar(9) not null,
    address varchar(45) not null,
    idStatus varchar(1) not null,
    createdAt varchar(45) not null,
    primary key(idUsers)

);

create table if not exists UsersStatus (

    idUsers varchar(4) not null,
    status varchar(15) not null,
    primary key(idUsers)
);

create table if not exists activateToken(

    idUsers varchar(4) not null ,
    token varchar(60)
);

alter table Users
    add foreign key(idStatus) references UsersStatus(idUsers);

create table if not exists Receipt(

    idReceipt varchar(6) not null AUTO_INCREMENT,
    invoiceNumber varchar(9) not null,
    idUsers varchar(4) not null,
    CustomerName varchar(45) not null,
    address varchar(45) not null,
    nettoPrice varchar(10) not null,
    vat varchar(4) not null,
    Invoicetype varchar(4) not null,
    "DATE" Date not null,
    customerNIP varchar(11),
	primary key(idReceipt)

);
alter table Receipt
    add foreign key(idUsers) references Users(idUsers);