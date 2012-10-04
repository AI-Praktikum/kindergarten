-- drop skripte
-- create skripte
-- insert dummy data skripte

/*=======================*/

drop table preisliste cascade constraints;
drop table kindergartentyp cascade constraints;
drop table kindergarten cascade constraints;
drop table elternteil cascade constraints;
drop table kind cascade constraints;
drop table gruppe cascade constraints;
drop table wartelistentyp cascade constraints;
drop table warteliste cascade constraints;

-- relationen

drop table kindergarten_kindergartentyp cascade constraints;
drop table kind_gruppe cascade constraints;
drop table kind_warteliste cascade constraints;

/*=======================*/

create table kindergartentyp
(ident number not null primary key,
kindergartentyp varchar2(50));

create table elternteil
(ident number not null primary key,
nachname varchar(50)not null,
familiengroesse number not null,
adresse varchar(200) not null,
nettoeinkommen number not null);

CREATE TABLE kind
(Ident number not null PRIMARY KEY,
Vorname VarChar(50) not null,
Nachname VarChar(50) not null,
Geburtsdatum  Date not null,
HashValue number not null,
ElternteilId number references elternteil(Ident));

create table kindergarten
(ident number not null primary key,
bezeichnung varchar2(50) not null,
adresse varchar2(200) not null,
maxplaetze number not null,
kontonummer number not null,
blz number not null);

create table warteliste
(ident number not null primary key,
wartelistentyp varchar2(50) not null unique);

create Table gruppe
(ident number not null primary key,
gruppengroesse number not null,
kindergarten_id number not null references kindergarten,
warteliste_id number not null references warteliste);

create table preisliste
(ident number not null primary key,
familiengroesse number not null,
nettoeinkommen number not null,
preis number not null);

-- relationen

create table kindergarten_kindergartentyp
(kindergarten_id number not null references kindergarten,
kindergartentyp_id number not null,
Primary Key(kindergarten_id),
Foreign Key(kindergartentyp_id) references kindergartentyp);

create Table kind_gruppe
(gruppe_id number not null references Gruppe,
kind_id number not null ,
Primary Key (gruppe_id),
Foreign Key(kind_id) references Kind);

create table kind_warteliste
(kind_id number not null references kind,
warteliste_id number not null,
Primary Key (kind_id),
Foreign Key (warteliste_id) references warteliste);

/*=======================*/
-- !!!! NOCH NICHT FERTIG !!!!!

insert into elternteil values(1,'Kirschenmann',3,'Berliner Tor 5; Hamburg',1500);
insert into elternteil values(2,'Wimmer',3,'Berliner Tor 5; Hamburg',2660);
insert into elternteil values(3,'Bielenberg',3,'Berliner Tor 5; Hamburg',1432);
insert into elternteil values(4,'Gille',3,'Berliner Tor 5; Hamburg',1841);
insert into elternteil values(5,'Krome',3,'Berliner Tor 5; Hamburg',2250);

insert into kind values(1,'Kai','Bielenberg','31.03.2006',12345,1);
insert into kind values(2,'Olli','Behncke','30.03.2006',12346,2);
insert into kind values(3,'Friz','Fritzchen','30.04.2007',12546,3);

insert into kindergarten values(1, 'Kindergarten', 'Musterstrasse',20,12345,6789);

insert into warteliste values(1, 'frueh');

insert into gruppe values(1, 20, 1, 1);

insert into kind_warteliste values(1,1);
insert into kind_warteliste values(2,1);
insert into kind_warteliste values(3,1);