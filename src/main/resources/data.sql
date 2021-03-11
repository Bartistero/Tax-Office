Insert into UsersStatus(idUsers, status)
VALUES ('0', 'non-activated');

Insert into UsersStatus(idUsers, status)
VALUES ('1', 'activated');

 Insert into UsersStatus(idUsers, status)
 VALUES ('2', 'suspended');

insert into Users(NIP,EMAIL,PASSWORD,COMPANYNAME,REGON,ADDRESS,IDSTATUS, CREATEDAT) VALUES('7131774585','bartek114@autograf.pl','$2a$10$f7IsyMxbUECKFAhbeTxsHeVJHZBMWJhVfa0Pk4O2/E/uE9xte4ipq','Bartosz Sterniczuk','430591583','MINKOWICE 194B, 21-007 MINKOWICE','1','2020-07-02');

Insert Into Receipt(INVOICENUMBER,IDUSERS,CUSTOMERNAME ,ADDRESS ,NETTOPRICE,VAT,INVOICETYPE,DATE,CUSTOMERNIP) VALUES('1','1','Politechnika Lubelska', 'Nadbystrzycka','500','23','0','2020-03-01','12345678991');
