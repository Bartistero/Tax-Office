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

