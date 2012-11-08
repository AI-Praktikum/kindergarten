/*
-- drop skripte
-- create skripte
-- insert dummy data skripte
*/
/*=======================*/

/*
-- Relationen
*/

drop table `kindergarten_preismodell`;
drop table `kind_gruppe`;
drop table `registrierung`;

/*
-- tables
*/

drop table `kind`;
drop table `preismodell`;
drop table `gruppe`;
drop table `kindergarten`;
drop table `elternteil`;
drop table `warteliste`;


/*=======================*/


CREATE TABLE `preismodell` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `bezeichnung` text NOT NULL,
  `dauer` bigint(20) NOT NULL,
  PRIMARY KEY (`ident`)
)ENGINE=InnoDB;

CREATE TABLE `elternteil` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `familiengroesse` bigint(20) NOT NULL,
  `adresse` text(200) NOT NULL,
  `nettoeinkommen` bigint(20) NOT NULL,
  `facebook_id` text(20),
  PRIMARY KEY (`ident`)
)ENGINE=InnoDB;

CREATE TABLE `kind` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `Vorname` text NOT NULL,
  `Nachname` text NOT NULL,
  `Geburtsdatum` date NOT NULL,
  `HashValue` bigint(20) NOT NULL,
  `Elternteil_id`  bigint(20) NOT NULL,
  `preismodell_id`  bigint(20) NOT NULL,
  FOREIGN KEY (`Elternteil_id`) REFERENCES `elternteil`(ident),
  FOREIGN KEY (`preismodell_id`) REFERENCES `preismodell`(ident),
  PRIMARY KEY (`ident`)
)ENGINE=InnoDB;

CREATE TABLE `kindergarten` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `bezeichnung` text NOT NULL,
  `adresse` text(200) NOT NULL,
  `maxplaetze` bigint(20) NOT NULL,
  `kontonummer` bigint(20) NOT NULL,
  `blz` bigint(20) NOT NULL,
  PRIMARY KEY (`ident`)
)ENGINE=InnoDB;

CREATE TABLE `warteliste` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `wartelistentyp` text NOT NULL,
  PRIMARY KEY (`ident`)
)ENGINE=InnoDB;

CREATE TABLE `gruppe` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `gruppengroesse` bigint(20) NOT NULL,
  `bezeichnung` text NOT NULL,
  `kindergarten_id` bigint(20) NOT NULL,
  `warteliste_id` bigint(20) NOT NULL,
  FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(ident),
  FOREIGN KEY (`warteliste_id`) REFERENCES `warteliste`(ident),
  PRIMARY KEY (`ident`)
)ENGINE=InnoDB;

/*
-- relationen
*/

CREATE TABLE `kindergarten_preismodell` (
  `kindergarten_id` bigint(20) NOT NULL,
  `preismodell_id` bigint(20) NOT NULL,
  FOREIGN KEY (`preismodell_id`) REFERENCES `preismodell`(ident),
  FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(ident),
  PRIMARY KEY (`kindergarten_id`, `preismodell_id`)
)ENGINE=InnoDB;

CREATE TABLE `kind_gruppe` (
  `gruppe_id` bigint(20) NOT NULL,
  `kind_id` bigint(20) NOT NULL,
  FOREIGN KEY (`gruppe_id`) REFERENCES `gruppe`(ident),
  FOREIGN KEY (`kind_id`) REFERENCES `kind`(ident),
  PRIMARY KEY (`kind_id`, `gruppe_id`)
)ENGINE=InnoDB;

CREATE TABLE `registrierung` (
  `kind_id` bigint(20) NOT NULL,
  `warteliste_id` bigint(20) NOT NULL,
  `datum_registrierung` TIMESTAMP NOT NULL default now(),
  FOREIGN KEY (`kind_id`) REFERENCES `kind`(ident),
  FOREIGN KEY (`warteliste_id`) REFERENCES `gruppe`(ident),
  PRIMARY KEY (`kind_id`, `warteliste_id`)
)ENGINE=InnoDB;


/* ########################################## */


insert into `elternteil` values(1,'Harald Kirschenmann',3,'Berliner Tor 5; Hamburg',1500,null);
insert into `elternteil` values(2,'Andreas Wimmer',3,'Berliner Tor 5; Hamburg',2660,null);
insert into `elternteil` values(3,'Kai Bielenberg',3,'Berliner Tor 5; Hamburg',1432,100001847250943);
insert into `elternteil` values(4,'Philipp Gille',3,'Berliner Tor 5; Hamburg',1841,null);
insert into `elternteil` values(5,'Sebastian Krome',3,'Berliner Tor 5; Hamburg',2250,null);

insert into `preismodell` values(1,'a',12);
insert into `preismodell` values(2,'b',10);
insert into `preismodell` values(3,'c',8);
insert into `preismodell` values(4,'d',6);
insert into `preismodell` values(5,'e',4);
insert into `preismodell` values(6,'f',5);
insert into `preismodell` values(7,'g',7);
insert into `preismodell` values(8,'h',5);
insert into `preismodell` values(9,'i',3);
insert into `preismodell` values(10,'j',2);

insert into `kind` values(1,'Kai','Bielenberg','31.03.2006',12345,1,1);
insert into `kind` values(2,'Olli','Behncke','30.03.2006',12346,2,2);
insert into `kind` values(3,'Friz','Fritzchen','30.04.2007',12546,3,3);
insert into `kind` values(4,'Bernd', 'Kahlbrandt','06.01.2010', 12347,3,3);
insert into `kind` values(5,'Moritz','Moritzson','05.07.2008',12348,2,2); 

insert into `kindergarten` values(1, '`kind`ergarten', 'Musterstrasse',20,12345,6789);

insert into `warteliste` values(1, 'frueh');
insert into `warteliste` values(2, 'vormittags');
insert into `warteliste` values(3, 'nachmittags');
insert into `warteliste` values(4, 'spaet');
insert into `warteliste` values(5, 'ganztags');

insert into `gruppe` values(1, 20, 'Hasengruppe', 1, 1);
insert into `gruppe` values(2, 20, 'Die Baeren', 1,2);

insert into `kind_gruppe` values(1,4);
insert into `kind_gruppe` values(2,5);

insert into `registrierung` (`kind_id`,`warteliste_id`) values(1,1);
insert into `registrierung` (`kind_id`,`warteliste_id`) values(2,1);
insert into `registrierung` (`kind_id`,`warteliste_id`) values(3,1);

insert into `registrierung` (`kind_id`,`warteliste_id`) values(3,2);
insert into `registrierung` (`kind_id`,`warteliste_id`) values(2,2);
insert into `registrierung` (`kind_id`,`warteliste_id`) values(1,2);
