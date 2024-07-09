create database autoricambi;
use autoricambi;
/*
//SCRIPT TABELLE
*/
create table account (
username varchar(16) not null,
nome varchar(15) not  null,
cognome varchar(15) not  null,
indirizzo varchar(120) not null, 
password varchar(16) not null, 
num_acquisti int not null,
id_carrello smallint not null,
primary key(username)
); 

create table telefono (
numero varchar(20) not null,
username varchar(16) not null,
primary key(numero),
foreign key(username) references account(username) on update cascade on delete cascade
);

create table auto (
id_auto smallint not null,
marca varchar(20) not null, 
modello varchar(20) not null, 
versione varchar(20) not null,
primary key(id_auto)
);

create table ricerca (
codice smallint not null,
data date not null,
username varchar(16) not null,
id_auto smallint not null,
primary key(codice),
foreign key(username) references account(username) on update cascade on delete cascade,
foreign key(id_auto) references auto(id_auto) on update cascade on delete cascade

);

create table categoria (
tipo varchar(50) not null, 
codice smallint not null,
primary key(codice)
);

create table ricambio (
codice_articolo smallint not null,
descrizione varchar(250) not null,
quantita smallint not null,
sconto numeric(10,2) not null,
tipo varchar(5) not null,
prezzo numeric(10,2) not null,
cod_categoria smallint not null,
primary key(codice_articolo),
foreign key(cod_categoria) references categoria(codice) on update cascade on delete cascade
);

create table carrello (
id_carrello smallint not null,
id_cliente varchar(16) not null,
importo_parziale numeric(10,2) not null,
costo_spedizione numeric(10,2) not null,
primary key(id_carrello),
foreign key (id_cliente) references account(username) on update cascade on delete cascade
);

create table fattura(
num_fatt smallint not null,
num_ordine smallint not null, 
nome_cliente varchar(16) not null,
primary key(num_fatt),
foreign key(nome_cliente) references account(username) on update cascade on delete cascade,
foreign key(num_ordine) references carrello(id_carrello) on update cascade on delete cascade
);

create table possiede(
num_poss smallint not null,
username varchar(16) not null,
num_ordine smallint not null,
data_acquisto date not null,
primary key(num_poss),
foreign key(username) references account(username) on update cascade on delete cascade,
foreign key(num_ordine) references fattura(num_ordine) on update cascade on delete cascade
);

create table cerca (
username varchar(16) not null,
id_auto smallint not null,
primary key(username, id_auto),
foreign key(username) references account(username) on update cascade on delete cascade,
foreign key(id_auto) references auto(id_auto) on update cascade on delete cascade
);

create table trova (
id_auto smallint not null,
codice smallint not null,
primary key(codice, id_auto),
foreign key(codice) references categoria(codice) on update cascade on delete cascade,
foreign key(id_auto) references auto(id_auto) on update cascade on delete cascade
);

create table inserito (
id_ins smallint not null,
codice_articolo smallint not null,
id_carrello smallint not null,
quantita smallint not null,
primary key (id_ins),
foreign key(codice_articolo) references ricambio(codice_articolo) on update cascade on delete cascade,
foreign key(id_carrello) references carrello(id_carrello) on update cascade on delete cascade
);

create table compare(
id_comp smallint not null,
num_ordine SMALLINT NOT NULL,
codice_articolo SMALLINT NOT NULL,
quantita SMALLINT NOT NULL,
primary key (id_comp),
foreign key (codice_articolo) references ricambio(codice_articolo) on delete cascade on update cascade,
foreign key(num_ordine) references fattura(num_ordine) on update cascade on delete cascade
);
/*
//CATEGORIA
*/
insert into categoria (tipo,codice) values ('pneumatici',001);
insert into categoria (tipo,codice) values ('olio e filtri',002);
insert into categoria (tipo,codice) values ('freni',003);
insert into categoria (tipo,codice) values ('accessori auto',004);
insert into categoria (tipo,codice) values ('carrozzeria',005);

/*
//AUTO
*/

insert into auto (id_auto,marca, modello, versione) values ('1','FIAT','Panda','2003');
insert into auto (id_auto,marca, modello, versione) values ('2','FIAT','Panda','2012');
insert into auto (id_auto,marca, modello, versione) values ('3','FIAT','Punto','1999');
insert into auto (id_auto,marca, modello, versione) values ('4','FIAT','Punto','2008');
insert into auto (id_auto,marca, modello, versione) values ('5','FIAT','Punto','2012');
insert into auto (id_auto,marca, modello, versione) values ('6','FIAT','500','2007');
insert into auto (id_auto,marca, modello, versione) values ('7','FIAT','500 cabrio','2009');
insert into auto (id_auto,marca, modello, versione) values ('8','FIAT','500e','2020');
insert into auto (id_auto,marca, modello, versione) values ('9','FIAT','500X','2014');
insert into auto (id_auto,marca, modello, versione) values ('10','FIAT','500L','2012');
insert into auto (id_auto,marca, modello, versione) values ('11','FIAT','Tipo','2015');
insert into auto (id_auto,marca, modello, versione) values ('12','FIAT','Tipo','2016');
insert into auto (id_auto,marca, modello, versione) values ('13','FIAT','Freemont','2011');
insert into auto (id_auto,marca, modello, versione) values ('14','FIAT','Grande Punto','2005');

insert into auto (id_auto,marca, modello, versione) values ('15','AUDI','A3','2012');
insert into auto (id_auto,marca, modello, versione) values ('16','AUDI','A3','2020');
insert into auto (id_auto,marca, modello, versione) values ('17','AUDI','A1','2010');
insert into auto (id_auto,marca, modello, versione) values ('18','AUDI','A1','2018');
insert into auto (id_auto,marca, modello, versione) values ('19','AUDI','Q4','2020');
insert into auto (id_auto,marca, modello, versione) values ('20','AUDI','Q4','2021');
insert into auto (id_auto,marca, modello, versione) values ('21','AUDI','Q5','2016');
insert into auto (id_auto,marca, modello, versione) values ('22','AUDI','e-tron','2019');
insert into auto (id_auto,marca, modello, versione) values ('23','AUDI','e-tron GT','2020');

insert into auto (id_auto,marca, modello, versione) values ('24','Volkswagen','Up','2011');
insert into auto (id_auto,marca, modello, versione) values ('25','Volkswagen','Up Van','2014');
insert into auto (id_auto,marca, modello, versione) values ('26','Volkswagen','Golf VI','2011');
insert into auto (id_auto,marca, modello, versione) values ('27','Volkswagen','Golf VII','2012');
insert into auto (id_auto,marca, modello, versione) values ('28','Volkswagen','Golf VIII','2019');
insert into auto (id_auto,marca, modello, versione) values ('29','Volkswagen','Tiguan 2','2016');
insert into auto (id_auto,marca, modello, versione) values ('30','Volkswagen','T-Roc','2017');

insert into auto(id_auto, marca, modello, versione) values(31,'Renault','Captur','2013');
insert into auto(id_auto, marca, modello, versione) values(32,'Renault','Captur','2020');
insert into auto(id_auto, marca, modello, versione) values(33,'Renault','Kadjar','2015');
insert into auto(id_auto, marca, modello, versione) values(34,'Renault','Koleos I','2008');
insert into auto(id_auto, marca, modello, versione) values(35,'Renault','Koleos II','2016');
insert into auto(id_auto, marca, modello, versione) values(36,'Renault','Megane III','2008');
insert into auto(id_auto, marca, modello, versione) values(37,'Renault','Megane IV','2015');
insert into auto(id_auto, marca, modello, versione) values(38,'Renault','Twingo I','2007');
insert into auto(id_auto, marca, modello, versione) values(39,'Renault','Twingo II','2014');
insert into auto(id_auto, marca, modello, versione) values(40,'Renault','Clio V','2019');
insert into auto(id_auto, marca, modello, versione) values(41,'Renault','Clio IV','2014');
insert into auto(id_auto, marca, modello, versione) values(42,'Renault','Clio III','2005');

insert into auto(id_auto, marca, modello, versione) values(43,'Peugeot','108','2014');
insert into auto(id_auto, marca, modello, versione) values(44,'Peugeot','208','2012');
insert into auto(id_auto, marca, modello, versione) values(45,'Peugeot','208','2019');
insert into auto(id_auto, marca, modello, versione) values(46,'Peugeot','308 II','2014');
insert into auto(id_auto, marca, modello, versione) values(47,'Peugeot','308 III','2021');
insert into auto(id_auto, marca, modello, versione) values(48,'Peugeot','2008 II','2019');
insert into auto(id_auto, marca, modello, versione) values(49,'Peugeot','2008 I','2013');
insert into auto(id_auto, marca, modello, versione) values(50,'Peugeot','3008','2016');
 
insert into auto(id_auto, marca, modello, versione) values(51,'Mini','Clubman','2014');
insert into auto(id_auto, marca, modello, versione) values(52,'Mini','Cabrio','2014');
insert into auto(id_auto, marca, modello, versione) values(53,'Mini','Countryman','2010');
insert into auto(id_auto, marca, modello, versione) values(54,'Mini','Countryman','2016');
insert into auto(id_auto, marca, modello, versione) values(55,'Mini','Coupe','2015');
insert into auto(id_auto, marca, modello, versione) values(56,'Mini','Roadster','2011');

insert into auto (id_auto,marca, modello, versione) values ('57','Hyundai','i10','2013');
insert into auto (id_auto,marca, modello, versione) values ('58','Hyundai','i10','2019');
insert into auto (id_auto,marca, modello, versione) values ('59','Hyundai','i20','2015');
insert into auto (id_auto,marca, modello, versione) values ('60','Hyundai','i20','2020');
insert into auto (id_auto,marca, modello, versione) values ('61','Hyundai','i30','2011');
insert into auto (id_auto,marca, modello, versione) values ('62','Hyundai','i30','2016');
insert into auto (id_auto,marca, modello, versione) values ('63','Hyundai','ix35','2009');
insert into auto (id_auto,marca, modello, versione) values ('64','Hyundai','ix55','2006');
insert into auto (id_auto,marca, modello, versione) values ('65','Hyundai','Matrix','2001');
insert into auto (id_auto,marca, modello, versione) values ('66','Hyundai','Tucson','2020');

insert into auto (id_auto,marca, modello, versione) values ('67','Lancia','Delta','2008');
insert into auto (id_auto,marca, modello, versione) values ('68','Lancia','Musa','2004');
insert into auto (id_auto,marca, modello, versione) values ('69','Lancia','Ypsilon II','2003');
insert into auto (id_auto,marca, modello, versione) values ('70','Lancia','Ypsilon III','2011');

insert into auto (id_auto,marca, modello, versione) values ('71','Chevrolet','Captiva','2007');
insert into auto (id_auto,marca, modello, versione) values ('72','Chevrolet','Matiz','2005');
insert into auto (id_auto,marca, modello, versione) values ('73','Chevrolet','Spark Ls','2009');
insert into auto (id_auto,marca, modello, versione) values ('74','Chevrolet','Spark Lt','2015');
insert into auto (id_auto,marca, modello, versione) values ('75','Chevrolet','Trax','2013');
insert into auto (id_auto,marca, modello, versione) values ('76','Chevrolet','Aveo','2011');

insert into auto (id_auto,marca, modello, versione) values ('77','Alfa Romeo','Giulia','2015');
insert into auto (id_auto,marca, modello, versione) values ('78','Alfa Romeo','Giulietta','2010');
insert into auto (id_auto,marca, modello, versione) values ('79','Alfa Romeo','Mito','2008');
insert into auto (id_auto,marca, modello, versione) values ('80','Alfa Romeo','Stelvio','2016');
insert into auto (id_auto,marca, modello, versione) values ('81','Alfa Romeo','159','2006');
insert into auto (id_auto,marca, modello, versione) values ('82','Alfa Romeo','156','2000');

insert into auto (id_auto,marca, modello, versione) values ('83','Mercedes','Classe E','2018');
insert into auto (id_auto,marca, modello, versione) values ('84','Mercedes','CLS Coupè','2011');
insert into auto (id_auto,marca, modello, versione) values ('85','Mercedes','CLS','2017');
insert into auto (id_auto,marca, modello, versione) values ('86','Mercedes','GLS','2015');
insert into auto (id_auto,marca, modello, versione) values ('87','Mercedes','GLS','2019');
insert into auto (id_auto,marca, modello, versione) values ('88','Mercedes','GLA','2020');
insert into auto (id_auto,marca, modello, versione) values ('89','Mercedes','EQA','2021');
insert into auto (id_auto,marca, modello, versione) values ('90','Mercedes','EQS','2021');

insert into auto (id_auto, marca, modello, versione) values('91','Opel','Meriva','2003');
insert into auto (id_auto, marca, modello, versione) values(92,'Opel','Meriva','2010');
insert into auto (id_auto, marca, modello, versione) values(93,'Opel','Astra','2015');
insert into auto (id_auto, marca, modello, versione) values(94,'Opel','Astra','2009');
insert into auto (id_auto, marca, modello, versione) values(95,'Opel','Corsa','2019');
insert into auto (id_auto, marca, modello, versione) values(96,'Opel','Mokka I','2012');
insert into auto (id_auto, marca, modello, versione) values(97,'Opel','Mokka II','2020');
insert into auto (id_auto, marca, modello, versione) values(98,'Opel','Zafira','2019');

insert into auto (id_auto, marca, modello, versione) values(99,'BMW','Serie 3 GT','2012');
insert into auto (id_auto, marca, modello, versione) values(100,'BMW','Serie 4 Coupè','2020');
insert into auto (id_auto, marca, modello, versione) values(101,'BMW','Serie 5 Touring','2017');
insert into auto (id_auto, marca, modello, versione) values(102,'BMW','Serie 6 GT','2017');
insert into auto (id_auto, marca, modello, versione) values(103,'BMW','Serie 7','2014');
insert into auto (id_auto, marca, modello, versione) values(104,'BMW','Serie 8 Coupè','2018');
insert into auto (id_auto, marca, modello, versione) values(105,'BMW','X1','2014');
insert into auto (id_auto, marca, modello, versione) values(106,'BMW','X3','2017');
insert into auto (id_auto, marca, modello, versione) values(107,'BMW','X5','2018');
insert into auto (id_auto, marca, modello, versione) values(108,'BMW','X7','2019');
insert into auto (id_auto, marca, modello, versione) values(109,'BMW','Z4','2016');

insert into auto (id_auto, marca, modello, versione) values(110,'Ford','C-Max II','2010');
insert into auto (id_auto, marca, modello, versione) values(111,'Ford','Ecosport Mk2','2011');
insert into auto (id_auto, marca, modello, versione) values(112,'Ford','Fiesta Mk6','2008');
insert into auto (id_auto, marca, modello, versione) values(113,'Ford','Fiesta Mk7','2017');
insert into auto (id_auto, marca, modello, versione) values(114,'Ford','Focus Mk4','2018');
insert into auto (id_auto, marca, modello, versione) values(115,'Ford','Kuga Mk3','2019');

insert into auto (id_auto, marca, modello, versione) values(116,'Jeep','Cherokee','2013');
insert into auto (id_auto, marca, modello, versione) values(117,'Jeep','Compass','2016');
insert into auto (id_auto, marca, modello, versione) values(118,'Jeep','Renegade','2014');
insert into auto (id_auto, marca, modello, versione) values(119,'Jeep','Wrangler IV','2017');
insert into auto (id_auto, marca, modello, versione) values(120,'Jeep','Grand Cherokee IV','2010');



/*
//RICAMBI
*/


/*gomme fiat*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(1,'treno di gomme estive per auto FIAT: Panda, Punto, Freemont, Grande Punto, Tipo',10,0,'nuovo',360.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(2,'treno di gomme invernali per auto FIAT: Panda, Punto, Freemont, Grande Punto, Tipo',10,0,'nuovo',390,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(3,'treno di gomme invernali per tutti i modelli FIAT 500: 500, 500e, 500X, 500L',10,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(4,'treno di gomme estive per tutti i modelli Fiat 500: 500, 500e, 500X, 500L',10,0,'nuovo',410.00,1);
/*gomme audi*/

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(5,'treno di gomme invernali per AUDI: A1, A3, e-tron, e-tron GT',10,0,'nuovo',600.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(6,'treno di gomme invernali per AUDI: Q5 e Q4',10,0,'nuovo',600.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(7,'treno di gomme estive per AUDI: A1, A3, e-tron, e-tron GT',10,0,'nuovo',480.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(8,'treno di gomme estive per AUDI: Q5 e Q4',10,0,'nuovo',480.00,1);

/*gomme volkswagen*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(9,'treno di gomme invernali per Volkswagen: Up, Up Van, Golf',10,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(10,'treno di gomme invernali per Volkswagen:Tiguan e T-Roc',10,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(11,'treno di gomme estive per Volkswagen: Up, Up Van, Golf',10,0,'nuovo',400.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(12,'treno di gomme estive per Volkswagen: Tiguan e T-Roc',10,0,'nuovo',400.00,1);

/*gomme renault*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(13,'treno di gomme invernali per Renault: Captur, Kadjar, Koleos',5,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(14,'treno di gomme invernali per Renault:Captur, Kadjar, Koleos',5,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(15,'treno di gomme estive per Renault: Twingo, Clio, Megane',3,0,'nuovo',400.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(16,'treno di gomme estive per Renault:Clio, Twingo, Megane',4,0,'nuovo',400.00,1);

/*gomme peugeot*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(17,'treno di gomme invernali per Peugeot: 108, 208, 308 II, 308 III',7,0,'nuovo',380.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(18,'treno di gomme invernali per Peugeot:2008 I, 2008 II, 3008',5,0,'nuovo',380.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(19,'treno di gomme estive per Peugeot: 108, 208, 308 II, 308 III',3,0,'nuovo',350.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(20,'treno di gomme estive per Peugeot:2008 I, 2008 II, 3008',4,0,'nuovo',350.00,1);

/*gomme mini*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(21,'treno di gomme invernali per tutti i modelli di Mini:Clubman,Cabrio, Countryman, Countryman, Coupe, Roadster',3,0,'nuovo',550.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(22,'treno di gomme estive per tutti i modelli di Mini:Clubman,Cabrio, Countryman, Countryman, Coupe, Roadster',4,0,'nuovo',420.00,1);

/*gomme hyundai*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(23,'treno di gomme invernali per Hyundai: i10, i20, i30',7,0,'nuovo',320.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(24,'treno di gomme invernali per Hyundai:ix35, Matrix, Tucson',5,0,'nuovo',350.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(25,'treno di gomme estive per Hyundai: ix35, Matrix, Tucson',3,0,'nuovo',300.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(26,'treno di gomme estive per Hyundai:i10, i20, i30',4,0,'nuovo',310.00,1);

/*gomme lancia*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(27,'treno di gomme invernali per tutti i modelli di Lancia: Delta, Musa, Ypsilon III, Ypsilon II',2,0,'nuovo',370.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(28,'treno di gomme estive per tutti i modelli di Lancia: Delta, Musa, Ypsilon III, Ypsilon II',3,0,'nuovo',320.00,1);

/*gomme chevrolet*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(29,'treno di gomme invernali per Chevrolet:Captiva, Trax ',7,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(30,'treno di gomme invernali per Chevrolet: Spark Ls, Spark Lt Aveo, Matiz',5,0,'nuovo',360.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(31,'treno di gomme estive per Chevrolet: Spark Ls, Spark Lt Aveo, Matiz',3,0,'nuovo',300.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(32,'treno di gomme estive per Chevrolet:Captiva, Trax ',4,0,'nuovo',360.00,1);

/*gomme alfa romeo*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(33,'treno di gomme invernali per Alfa Romeo Stelvio',5,0,'nuovo',650.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(34,'treno di gomme estive per Alfa Romeo Stelvio',3,0,'nuovo',600.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(35,'treno di gomme estive per Alfa Romeo: Mito, 156, 159, Giulia, Giulietta',6,0,'nuovo',400.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(36,'treno di gomme estive per Alfa Romeo:Mito, 156, 159, Giulia, Giulietta',4,0,'nuovo',480.00,1);

/*gomme mercedes*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(37,'treno di gomme invernali per Mercedes: Classe E, CLS Coupè, CLS, EQS',5,0,'nuovo',650.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(38,'treno di gomme estive per Mercedes: Classe E, CLS Coupè, CLS, EQS',3,0,'nuovo',600.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(39,'treno di gomme estive per Mercedes: GLS, GLA, EQA',6,0,'nuovo',700.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(40,'treno di gomme invernali per Mercedes: GLS, GLA, EQA',4,0,'nuovo',650.00,1);

/*gomme opel*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(41,'treno di gomme invernali per Opel: Mokka I, Mokka II, Zafira, Meriva',3,0,'nuovo',550.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(42,'treno di gomme estive per Opel: Mokka I, Mokka II, Zafira, Meriva',6,0,'nuovo',490.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(43,'treno di gomme estive per Opel: Astra, Corsa',3,0,'nuovo',350.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(44,'treno di gomme invernali per Opel: Astra, Corsa',2,0,'nuovo',410.00,1);

/*gomme bmw*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(45,'treno di gomme invernali per BMW: Z4, Serie 3 GT, Serie 4 Coupè, Serie 5 Touring, Serie 6 GT, Serie 7, Serie 8 coupè',2,0,'nuovo',850.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(46,'treno di gomme estive per BMW: Z4, Serie 3 GT, Serie 4 Coupè, Serie 5 Touring, Serie 6 GT, Serie 7, Serie 8 coupè',3,0,'nuovo',790.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(47,'treno di gomme estive per BMW: X1, X3, X5, X7',3,0,'nuovo',950.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(48,'treno di gomme invernali per BMW: X1, X3, X5, X7',2,0,'nuovo',780.00,1);

/*gomme ford*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(49,'treno di gomme invernali per Ford:C-Max, Ecosport Mk2, Kuga Mk3',2,0,'nuovo',550.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(50,'treno di gomme estive per Ford:C-Max, Ecosport Mk2, Kuga Mk3',3,0,'nuovo',490.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(51,'treno di gomme estive per Ford:Fiesta Mk6, Fiesta Mk7, Focus Mk4',4,0,'nuovo',450.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(52,'treno di gomme invernali per Ford:Fiesta Mk6, Fiesta Mk7, Focus Mk4',2,0,'nuovo',390.00,1);

/*gomme jeep*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(53,'treno di gomme estive per Jeep:Cherokee, Compass, Renegade, Wrangler IV, Grand Cherokee IV',4,0,'nuovo',750.00,1);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(54,'treno di gomme invernali per Jeep:Cherokee, Compass, Renegade, Wrangler IV, Grand Cherokee IV',5,0,'nuovo',680.00,1);





/*olio e filtri*/
/*filtri standard*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(55,'Filtro aria per autoveicoli FIAT',5,0,'nuovo',15.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(56,'Filtro aria per autoveicoli AUDI',5,0,'nuovo',35.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(57,'Filtro aria per autoveicoli Volkswagen',5,0,'nuovo',29.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(58,'Filtro aria per autoveicoli Renault',5,0,'nuovo',10.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(59,'Filtro aria per autoveicoli Peugeot',5,0,'nuovo',25.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(60,'Filtro aria per autoveicoli Mini',5,0,'nuovo',19.90,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(61,'Filtro aria per autoveicoli Hyundai',5,0,'nuovo',34.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(62,'Filtro aria per autoveicoli Lancia',5,0,'nuovo',17.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(63,'Filtro aria per autoveicoli Chevrolet',5,0,'nuovo',20.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(64,'Filtro aria per autoveicoli Alfa Romeo',5,0,'nuovo',22.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(65,'Filtro aria per autoveicoli Mercedes',5,0,'nuovo',40.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(66,'Filtro aria per autoveicoli Opel',5,0,'nuovo',29.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(67,'Filtro aria per autoveicoli BMW',5,0,'nuovo',50.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(68,'Filtro aria per autoveicoli Ford',5,0,'nuovo',35.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(69,'Filtro aria per autoveicoli Jeep',5,0,'nuovo',37.00,2);

/*filtri olio standard*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(70,'Filtro olio per autoveicoli FIAT',5,0,'nuovo',9.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(71,'Filtro olio per autoveicoli AUDI',5,0,'nuovo',13.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(72,'Filtro olio per autoveicoli Volkswagen',5,0,'nuovo',13.90,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(73,'Filtro olio per autoveicoli Renault',5,0,'nuovo',8.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(74,'Filtro olio per autoveicoli Peugeot',5,0,'nuovo',7.50,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(75,'Filtro olio per autoveicoli Mini',5,0,'nuovo',7.90,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(76,'Filtro olio per autoveicoli Hyundai',5,0,'nuovo',11.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(77,'Filtro olio per autoveicoli Lancia',5,0,'nuovo',9.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(78,'Filtro olio per autoveicoli Chevrolet',5,0,'nuovo',14.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(79,'Filtro olio per autoveicoli Alfa Romeo',5,0,'nuovo',14.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(80,'Filtro olio per autoveicoli Mercedes',5,0,'nuovo',9.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(81,'Filtro olio per autoveicoli Opel',5,0,'nuovo',13.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(82,'Filtro olio per autoveicoli BMW',5,0,'nuovo',11.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(83,'Filtro olio per autoveicoli Ford',5,0,'nuovo',8.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(84,'Filtro olio per autoveicoli Jeep',5,0,'nuovo',12.90,2);

/*filtro aria sportivo*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(85,'Filtro aria sportivo per autoveicoli FIAT: 500, 500e, 500X, 500L ',5,0,'nuovo',15.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(86,'Filtro aria sportivo per autoveicoli AUDI: A1, A3, e-tron, e-tron GT, Q5 e Q4 ',5,0,'nuovo',35.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(87,'Filtro aria sportivo per autoveicoli Volkswagen: Up, Up Van, Golf',5,0,'nuovo',29.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(88,'Filtro aria sportivo per autoveicoli Renault: Twingo, Clio, Megane',5,0,'nuovo',10.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(89,'Filtro aria sportivo per autoveicoli Peugeot: 108, 208, 308 II, 308 III',5,0,'nuovo',25.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(90,'Filtro aria sportivo per autoveicoli Mini: Clubman,Cabrio, Countryman, Countryman, Coupe, Roadster',5,0,'nuovo',19.90,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(91,'Filtro aria sportivo per autoveicoli Hyundai: i10, i20, i30',5,0,'nuovo',34.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(92,'Filtro aria sportivo per autoveicoli Lancia: Delta, Musa, Ypsilon III, Ypsilon II',5,0,'nuovo',17.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(93,'Filtro aria sportivo per autoveicoli Chevrolet: Spark Ls, Spark Lt Aveo, Matiz',5,0,'nuovo',20.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(94,'Filtro aria sportivo per autoveicoli Alfa Romeo: Mito, 156, 159, Giulia, Giulietta, Stelvio',5,0,'nuovo',22.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(95,'Filtro aria sportivo per autoveicoli Mercedes: Classe E, CLS Coupè, CLS, EQS',5,0,'nuovo',40.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(96,'Filtro aria sportivo per autoveicoli Opel: Astra, Corsa',5,0,'nuovo',29.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(97,'Filtro aria sportivo per autoveicoli BMW: Z4, Serie 3 GT, Serie 4 Coupè, Serie 5 Touring, Serie 6 GT, Serie 7, Serie 8 coupè',5,0,'nuovo',50.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(98,'Filtro aria sportivo per autoveicoli Ford: Fiesta Mk6, Fiesta Mk7, Focus Mk4',5,0,'nuovo',35.00,2);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(99,'Filtro aria sportivo per autoveicoli Jeep: Cherokee, Compass, Renegade, Wrangler IV, Grand Cherokee IV',5,0,'nuovo',37.00,2);



/*pastiglie freno*/
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(100,'kit pastiglie-dischi-pinze per autoveicoli FIAT',5,0,'nuovo',150.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(101,'kit pastiglie-dischi-pinze per autoveicoli AUDI',5,0,'nuovo',1280.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(102,'kit pastiglie-dischi-pinze per autoveicoli Volkswagen',5,0,'nuovo',1800.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(103,'kit pastiglie-dischi-pinze per autoveicoli Renault',5,0,'nuovo',800.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(104,'kit pastiglie-dischi-pinze per autoveicoli Peugeot',5,0,'nuovo',1280.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(105,'kit pastiglie-dischi-pinze per autoveicoli Mini',5,0,'nuovo',1200.90,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(106,'kit pastiglie-dischi-pinze per autoveicoli Hyundai',5,0,'nuovo',1700.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(107,'kit pastiglie-dischi-pinze per autoveicoli Lancia',5,0,'nuovo',900.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(108,'kit pastiglie-dischi-pinze per autoveicoli Chevrolet',5,0,'nuovo',800.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(109,'kit pastiglie-dischi-pinze per autoveicoli Alfa Romeo',5,0,'nuovo',1100.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(110,'kit pastiglie-dischi-pinze per autoveicoli Mercedes',5,0,'nuovo',2400.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(111,'kit pastiglie-dischi-pinze per autoveicoli Opel',5,0,'nuovo',900.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(112,'kit pastiglie-dischi-pinze per autoveicoli BMW',5,0,'nuovo',1500.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(113,'kit pastiglie-dischi-pinze per autoveicoli Ford',5,0,'nuovo',1400.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(114,'kit pastiglie-dischi-pinze per autoveicoli Jeep',5,0,'nuovo',1900.00,3);

/*pastiglie freno */
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(115,'kit pastiglie-dischi-pinze sportivo per autoveicoli FIAT: 500, 500e, 500X, 500L',5,0,'nuovo',250.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(116,'kit pastiglie-dischi-pinze sportivo per autoveicoli AUDI: A1, A3, e-tron, e-tron GT, Q5 e Q4',5,0,'nuovo',2240.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(117,'kit pastiglie-dischi-pinze sportivo per autoveicoli Volkswagen: Up, Up Van, Golf',5,0,'nuovo',3800.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(118,'kit pastiglie-dischi-pinze sportivo per autoveicoli Renault:Twingo, Clio, Megane ',5,0,'nuovo',1200.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(119,'kit pastiglie-dischi-pinze sportivo per autoveicoli Peugeot: 108, 208, 308 II, 308 III',5,0,'nuovo',2100.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(120,'kit pastiglie-dischi-pinze sportivo per autoveicoli MiniClubman,Cabrio, Countryman, Countryman, Coupe, Roadster',5,0,'nuovo',2600.90,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(121,'kit pastiglie-dischi-pinze sportivo per autoveicoli Hyundai: i10, i20, i30',5,0,'nuovo',2700.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(122,'kit pastiglie-dischi-pinze sportivo per autoveicoli Lancia: Delta, Musa, Ypsilon III, Ypsilon II',5,0,'nuovo',2000.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(123,'kit pastiglie-dischi-pinze sportivo per autoveicoli Chevrolet: Spark Ls, Spark Lt Aveo, Matiz',5,0,'nuovo',2790.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(124,'kit pastiglie-dischi-pinze sportivo per autoveicoli Alfa Romeo: Mito, 156, 159, Giulia, Giulietta, Stelvio',5,0,'nuovo',2300.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(125,'kit pastiglie-dischi-pinze sportivo per autoveicoli Mercedes: Classe E, CLS Coupè, CLS, EQS',5,0,'nuovo',3000.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(126,'kit pastiglie-dischi-pinze sportivo per autoveicoli Opel: Astra, Corsa',5,0,'nuovo',1600.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(127,'kit pastiglie-dischi-pinze sportivo per autoveicoli BMW: Z4, Serie 3 GT, Serie 4 Coupè, Serie 5 Touring, Serie 6 GT, Serie 7, Serie 8 coupè',5,0,'nuovo',4100.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(128,'kit pastiglie-dischi-pinze sportivo per autoveicoli Ford: Fiesta Mk6, Fiesta Mk7, Focus Mk4',5,0,'nuovo',2200.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(129,'kit pastiglie-dischi-pinze sportivo per autoveicoli Jeep: Cherokee, Compass, Renegade, Wrangler IV, Grand Cherokee IV',5,0,'nuovo',3800.00,3);

/*kit freno usati*/
/*pastiglie freno */
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(130,'kit pastiglie-dischi-pinze per autoveicoli FIAT',3,50,'usato',250.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(131,'kit pastiglie-dischi-pinze per autoveicoli AUDI',2,300,'usato',2240.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(132,'kit pastiglie-dischi-pinze per autoveicoli Volkswagen',2,800,'usato',3800.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(133,'kit pastiglie-dischi-pinze per autoveicoli Renault',3,200,'usato',1200.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(134,'kit pastiglie-dischi-pinze per autoveicoli Peugeot',3,400,'usato',2100.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(135,'kit pastiglie-dischi-pinze per autoveicoli Mini',2,400,'usato',2600.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(136,'kit pastiglie-dischi-pinze per autoveicoli Hyundai',3,500,'usato',2700.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(137,'kit pastiglie-dischi-pinze per autoveicoli Lancia',2,200,'usato',2000.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(138,'kit pastiglie-dischi-pinze per autoveicoli Chevrolet',3,790,'usato',2790.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(139,'kit pastiglie-dischi-pinze per autoveicoli Alfa Romeo',2,300,'usato',2300.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(140,'kit pastiglie-dischi-pinze per autoveicoli Mercedes',1,150,'usato',3000.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(141,'kit pastiglie-dischi-pinze per autoveicoli Opel',3,600,'usato',1600.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(142,'kit pastiglie-dischi-pinze per autoveicoli BMW',1,300,'usato',4100.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(143,'kit pastiglie-dischi-pinze per autoveicoli Ford',2,200,'usato',2200.00,3);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(144,'kit pastiglie-dischi-pinze per autoveicoli Jeep',3,800,'usato',3800.00,3);


/*carrozzeria*/

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(145,'Kit 2 specchietti retrovisori per FIAT',2,15,'usato',50.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(146,'kit frecce autoveicoli FIAT',2,5,'usato',20.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(147,'kit 2pcs fanali posteriori FIAT',3,100,'usato',200.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(148,'kit 2pcs fari anteriori FIAT',3,100,'usato',200.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(149,'paraurti posteriore FIAT',4,0,'nuovo',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(150,'paraurti anteriore FIAT',4,0,'nuovo',100.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(151,'Kit 2 specchietti retrovisori per AUDI',2,0,'nuovo',70.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(152,'kit frecce autoveicoli AUDI',3,0,'nuovo',50.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(153,'kit 2pcs fanali posteriori AUDI:',2,0,'nuovo',339.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(154,'kit 2pcs fari anteriori AUDI: ',3,0,'nuovo',350.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(155,'paraurti posteriore per AUDI: ',2,0,'nuovo',170.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(156,'paraurti anteriore per AUDI: ',3,0,'nuovo',290.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(163,'Kit 2 specchietti retrovisori per Volkswagen',2,0,'nuovo',50.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(164,'kit frecce autoveicoli Volkswagen',3,0,'nuovo',30.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(165,'kit 2pcs fanali posteriori Volkswagen ',2,0,'nuovo',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(166,'kit 2pcs fari anteriori Volkswagen ',3,0,'nuovo',100.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(167,'paraurti posteriore per Volkswagen ',2,0,'nuovo',130,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(168,'paraurti anteriore per Volkswagen ',3,0,'nuovo',150.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(169,'Kit 2 specchietti retrovisori per Renault',2,0,'nuovo',50.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(170,'kit frecce autoveicoli Renault',3,0,'nuovo',40.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(171,'kit 2pcs fanali posteriori Renault ',2,0,'nuovo',100.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(172,'kit 2pcs fari anteriori Renault ',2,0,'nuovo',160.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(173,'paraurti posteriore per Renault ',2,0,'nuovo',190.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(174,'paraurti anteriore per Renault ',2,0,'nuovo',260.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(175,'Kit 2 specchietti retrovisori per Peugeot',3,0,'nuovo',40.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(176,'kit frecce autoveicoli Peugeot',3,0,'nuovo',35.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(177,'kit 2pcs fanali posteriori Peugeot ',3,0,'nuovo',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(178,'kit 2pcs fari anteriori Peugeot ',3,0,'nuovo',100.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(179,'paraurti posteriore per Peugeot ',3,0,'nuovo',120.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(180,'paraurti anteriore per Peugeot ',3,0,'nuovo',160.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(181,'Kit 2 specchietti retrovisori per Mini',3,0,'nuovo',50.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(182,'kit frecce autoveicoli Mini',3,0,'nuovo',25.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(183,'kit 2pcs fanali posteriori Mini ',3,0,'nuovo',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(184,'kit 2pcs fari anteriori Mini ',3,0,'nuovo',120.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(185,'paraurti posteriore per Mini ',3,0,'nuovo',100.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(186,'paraurti anteriore per Mini ',3,0,'nuovo',150.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(187,'Kit 2 specchietti retrovisori per Hyunday',3,0,'nuovo',70.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(188,'kit frecce autoveicoli Hyunday',3,0,'nuovo',30.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(189,'kit 2pcs fanali posteriori Hyunday ',3,0,'nuovo',190.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(190,'kit 2pcs fari anteriori Hyunday ',3,0,'nuovo',250.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(191,'paraurti posteriore per Hyunday ',3,50,'usato',150.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(192,'paraurti anteriore per Hyunday ',3,100,'usato',350.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(193,'Kit 2 specchietti retrovisori per Lancia',2,0,'nuovo',60.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(194,'kit frecce autoveicoli Lancia',2,0,'nuovo',35.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(195,'kit 2pcs fanali posteriori Lancia ',2,0,'nuovo',120.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(196,'kit 2pcs fari anteriori Lancia ',2,0,'nuovo',170.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(197,'paraurti posteriore per Lancia ',2,0,'nuovo',70.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(198,'paraurti anteriore per Lancia ',2,0,'nuovo',180.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(199,'Kit 2 specchietti retrovisori per Chevrolet',2,30,'usato',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(200,'kit frecce autoveicoli Chevrolet',2,9,'usato',29.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(201,'kit 2pcs fanali posteriori Chevrolet ',2,30,'usato',150.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(202,'kit 2pcs fari anteriori Chevrolet ',2,30,'usato',130.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(203,'paraurti posteriore per Chevrolet ',2,30,'usato',130.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(204,'paraurti anteriore per Chevrolet ',2,40,'usato',200.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(205,'Kit 2 specchietti retrovisori per Alfa Romeo',3,0,'nuovo',130.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(206,'kit frecce autoveicoli Alfa Romeo',4,0,'nuovo',40.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(207,'kit 2pcs fanali posteriori Alfa Romeo ',3,0,'nuovo',160.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(208,'kit 2pcs fari anteriori Alfa Romeo ',4,0,'nuovo',150.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(209,'paraurti posteriore per Alfa Romeo ',3,0,'nuovo',300.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(210,'paraurti anteriore per Alfa Romeo ',4,0,'nuovo',450.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(211,'Kit 2 specchietti retrovisori per Mercedes',2,0,'nuovo',320.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(212,'kit frecce autoveicoli Mercedes',2,0,'nuovo',72.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(213,'kit 2pcs fanali posteriori Mercedes ',2,0,'nuovo',200.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(214,'kit 2pcs fari anteriori Mercedes ',2,0,'nuovo',300.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(215,'paraurti posteriore per Mercedes ',2,0,'nuovo',250.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(216,'paraurti anteriore per Mercedes ',2,0,'nuovo',440.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(217,'Kit 2 specchietti retrovisori per Opel',2,10,'usato',50.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(218,'kit frecce autoveicoli Opel',2,10,'usato',40.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(219,'kit 2pcs fanali posteriori Opel ',2,20,'usato',120.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(220,'kit 2pcs fari anteriori Opel ',2,30,'usato',180.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(221,'paraurti posteriore per Opel ',3,0,'nuovo',170.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(222,'paraurti anteriore per Opel ',3,0,'nuovo',210.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(223,'Kit 2 specchietti retrovisori per BMW',3,0,'nuovo',520.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(224,'kit frecce autoveicoli BMW',3,0,'nuovo',81.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(225,'kit 2pcs fanali posteriori BMW ',2,0,'nuovo',400.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(226,'kit 2pcs fari anteriori BMW ',2,0,'nuovo',800.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(227,'paraurti posteriore per BMW ',2,0,'nuovo',389.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(228,'paraurti anteriore per BMW ',2,0,'nuovo',714.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(229,'Kit 2 specchietti retrovisori per Ford',3,0,'nuovo',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(230,'kit frecce autoveicoli Ford',3,0,'nuovo',35.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(231,'kit 2pcs fanali posteriori Ford ',3,0,'nuovo',150.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(232,'kit 2pcs fari anteriori Ford ',3,0,'nuovo',190.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(233,'paraurti posteriore per Ford ',2,0,'nuovo',80.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(234,'paraurti anteriore per Ford ',2,0,'nuovo',150.00,5);

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(235,'Kit 2 specchietti retrovisori per Jeep',2,50,'usato',200.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(236,'kit frecce autoveicoli Jeep',2,19,'usato',69.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(237,'kit 2pcs fanali posteriori Jeep ',2,30,'usato',150.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(238,'kit 2pcs fari anteriori Jeep ',2,40,'usato',300.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(239,'paraurti posteriore per Jeep ',2,32,'usato',292.00,5);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(240,'paraurti anteriore per Jeep ',2,50,'usato',400.00,5);


/*Accessori auto*/

insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(241,'Dash cam per monitoraggio della guida ',20,0,'nuovo',40.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(242,'Kit primo soccorso auto',30,0,'nuovo',25.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(243,'Tappetino rettangolare universale in gomma 4pcs',20,0,'nuovo',10.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(244,'Copri sedili universali',10,0,'nuovo',15.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(245,'Autoradio universale con connettività bluetooth',5,0,'nuovo',35.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(246,'Kit amplificatori JBL universali',5,0,'nuovo',200.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(247,'Copri parabrezza universale',8,0,'nuovo',7.90,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(248,'Kit pulizia auto',10,0,'nuovo',12.00,4);
insert into ricambio(codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria)
values(249,'Profumatore da auto',20,0,'nuovo',2.00,4);
