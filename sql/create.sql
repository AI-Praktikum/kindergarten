-- drop skripte
-- create skripte
-- insert dummy data skripte

/*=======================*/

drop table preisliste cascade constraints;
drop table preismodell cascade constraints;
drop table kindergarten cascade constraints;
drop table elternteil cascade constraints;
drop table kind cascade constraints;
drop table gruppe cascade constraints;
drop table warteliste cascade constraints;

-- relationen

drop table kindergarten_preismodell cascade constraints;
drop table kind_gruppe cascade constraints;
drop table registrierung cascade constraints;

/*=======================*/

create table preismodell
(ident number not null primary key,
bezeichnung varchar2(50),
dauer number not null);

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
Elternteil_id number references elternteil(ident),
preismodell_id number references preismodell(ident));

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
kindergarten_id number not null references kindergarten(ident),
warteliste_id number not null references warteliste(ident));

create table preisliste
(ident number not null primary key,
preismodell_id number not null references preismodell(ident),
nettoeinkommen number not null,
preis2pers number not null,
preis3pers number not null,
preis4pers number not null,
preis5pers number not null,
preis6pers number not null);

-- relationen

create table kindergarten_preismodell
(kindergarten_id number not null references kindergarten(ident),
preismodell_id number not null references preismodell(ident),
Primary Key(kindergarten_id, preismodell_id));

create Table kind_gruppe
(gruppe_id number not null references Gruppe(ident),
kind_id number not null references kind(ident),
Primary Key (gruppe_id, kind_id));

create table registrierung
(kind_id number not null references kind(ident),
warteliste_id number not null references warteliste(ident),
datum_registrierung TIMESTAMP default sysTIMESTAMP,
primary key (kind_id, warteliste_id));

/*
create table preismodell_preisliste
(preismodell_id number not null references preismodell(ident),
preisliste_id number not null references preisliste(ident),
Primary Key (preismodell_id, preisliste_id));
*/

/*=======================*/

insert into elternteil values(1,'Kirschenmann',3,'Berliner Tor 5; Hamburg',1500);
insert into elternteil values(2,'Wimmer',3,'Berliner Tor 5; Hamburg',2660);
insert into elternteil values(3,'Bielenberg',3,'Berliner Tor 5; Hamburg',1432);
insert into elternteil values(4,'Gille',3,'Berliner Tor 5; Hamburg',1841);
insert into elternteil values(5,'Krome',3,'Berliner Tor 5; Hamburg',2250);

insert into preismodell values(1,'a',12);
insert into preismodell values(2,'b',10);
insert into preismodell values(3,'c',8);
insert into preismodell values(4,'d',6);
insert into preismodell values(5,'e',4);
insert into preismodell values(6,'f',5);
insert into preismodell values(7,'g',7);
insert into preismodell values(8,'h',5);
insert into preismodell values(9,'i',3);
insert into preismodell values(10,'j',2);

insert into preisliste values(1,1,1111,112,113,114,115,116);
/*
insert into preismodell_preisliste values(1,1);
insert into preismodell_preisliste values(2,1);
insert into preismodell_preisliste values(3,1);
*/
insert into kind values(1,'Kai','Bielenberg','31.03.2006',12345,1,1);
insert into kind values(2,'Olli','Behncke','30.03.2006',12346,2,2);
insert into kind values(3,'Friz','Fritzchen','30.04.2007',12546,3,3);

insert into kindergarten values(1, 'Kindergarten', 'Musterstrasse',20,12345,6789);

insert into warteliste values(1, 'frueh');
insert into warteliste values(2, 'vormittags');
insert into warteliste values(3, 'nachmittags');
insert into warteliste values(4, 'spaet');
insert into warteliste values(5, 'ganztags');

insert into gruppe values(1, 20, 1, 1);

insert into registrierung (kind_id,warteliste_id) values(1,1);
insert into registrierung (kind_id,warteliste_id) values(2,1);
insert into registrierung (kind_id,warteliste_id) values(3,1);

insert into registrierung (kind_id,warteliste_id) values(3,2);
insert into registrierung (kind_id,warteliste_id) values(2,2);
insert into registrierung (kind_id,warteliste_id) values(1,2);

/*====================*/



/*====================*/

commit;