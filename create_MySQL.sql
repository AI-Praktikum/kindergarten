/*
-- drop skripte
-- create skripte
-- insert dummy data skripte
*/
/*=======================*/

/*
-- Relationen
*/
/*
drop table `kindergarten_preismodell`;
drop table `kind_gruppe`;
drop table `registrierung`;

/*
-- tables
*/
/*
drop table `preisliste`;
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

CREATE TABLE `preisliste` (
  `ident` bigint(20) NOT NULL AUTO_INCREMENT,
  `preismodell_id` bigint(20) NOT NULL,
  `nettoeinkommen` bigint(20) NOT NULL,
  `preis2pers` bigint(20) NOT NULL,
  `preis3pers` bigint(20) NOT NULL,
  `preis4pers` bigint(20) NOT NULL,
  `preis5pers` bigint(20) NOT NULL,
  `preis6pers` bigint(20) NOT NULL,
  FOREIGN KEY (`preismodell_id`) REFERENCES `preismodell`(ident),
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



insert into `elternteil` values(1,'Harald Kirschenmann',3,'Berliner Tor 5; Hamburg',1500);
insert into `elternteil` values(2,'Andreas Wimmer',3,'Berliner Tor 5; Hamburg',2660);
insert into `elternteil` values(3,'Kai Bielenberg',3,'Berliner Tor 5; Hamburg',1432);
insert into `elternteil` values(4,'Philipp Gille',3,'Berliner Tor 5; Hamburg',1841);
insert into `elternteil` values(5,'Sebastian Krome',3,'Berliner Tor 5; Hamburg',2250);

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

/*
insert into `preismodell_preisliste` values(1,1);
insert into `preismodell_preisliste` values(2,1);
insert into `preismodell_preisliste` values(3,1);
*/
insert into `kind` values(1,'Kai','Bielenberg','31.03.2006',12345,1,1);
insert into `kind` values(2,'Olli','Behncke','30.03.2006',12346,2,2);
insert into `kind` values(3,'Friz','Fritzchen','30.04.2007',12546,3,3);
insert into `kind` values(4,'Bernd', 'Kahlbrandt','01.01.2005', 12347,3,3);
insert into `kind` values(5,'Moritz','Moritzson','05.07.2008',12348,2,2); 


insert into `kindergarten` values(1, 'kindergarten', 'Musterstrasse',20,12345,6789);

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

/*====================*/
/*
-- Insert into `preisliste`  values (1,1,1111,112,113,114,115,116);
*/
Insert into `preisliste`  values (2,1,0,49,49,49,49,49);
Insert into `preisliste`  values (3,1,1023,49,49,49,49,49);
Insert into `preisliste`  values (4,1,1074,51,49,49,49,49);
Insert into `preisliste`  values (5,1,1125,53,49,49,49,49);
Insert into `preisliste`  values (6,1,1176,54,49,49,49,49);
Insert into `preisliste`  values (7,1,1227,57,49,49,49,49);
Insert into `preisliste`  values (8,1,1278,60,49,49,49,49);
Insert into `preisliste`  values (9,1,1329,63,49,49,49,49);
Insert into `preisliste`  values (10,1,1380,69,54,49,49,49);
Insert into `preisliste`  values (11,1,1432,75,60,49,49,49);
Insert into `preisliste`  values (12,1,1483,82,67,49,49,49);
Insert into `preisliste`  values (13,1,1534,88,74,49,49,49);
Insert into `preisliste`  values (14,1,1282,95,81,49,49,49);
Insert into `preisliste`  values (15,1,1636,102,87,49,49,49);
Insert into `preisliste`  values (16,1,1687,111,96,57,49,49);
Insert into `preisliste`  values (17,1,1738,121,106,67,49,49);
Insert into `preisliste`  values (18,1,1790,130,116,77,49,49);
Insert into `preisliste`  values (19,1,1841,140,125,86,49,49);
Insert into `preisliste`  values (20,1,1892,151,136,97,49,49);
Insert into `preisliste`  values (21,1,1943,162,148,109,60,49);
Insert into `preisliste`  values (22,1,1994,175,161,122,73,49);
Insert into `preisliste`  values (23,1,2045,189,174,135,86,49);
Insert into `preisliste`  values (24,1,2096,203,189,150,101,49);
Insert into `preisliste`  values (25,1,2147,218,203,164,116,57);
Insert into `preisliste`  values (26,1,2199,232,218,179,130,72);
Insert into `preisliste`  values (27,1,2250,247,232,193,145,86);
Insert into `preisliste`  values (28,1,2301,261,247,208,160,101);
Insert into `preisliste`  values (29,1,2352,276,261,222,174,116);
Insert into `preisliste`  values (30,1,2403,291,277,238,190,131);
Insert into `preisliste`  values (31,1,2454,307,293,254,206,147);
Insert into `preisliste`  values (32,1,2505,324,309,270,222,164);
Insert into `preisliste`  values (33,1,2556,341,326,287,239,180);
Insert into `preisliste`  values (34,1,2608,358,344,305,257,198);
Insert into `preisliste`  values (35,1,2659,376,362,323,275,216);
Insert into `preisliste`  values (36,1,2710,396,380,342,293,235);
Insert into `preisliste`  values (37,1,2761,396,396,360,312,254);
Insert into `preisliste`  values (38,1,2812,396,396,380,331,273);
Insert into `preisliste`  values (39,1,2863,396,396,396,351,293);
Insert into `preisliste`  values (40,1,2914,396,396,396,372,313);
Insert into `preisliste`  values (41,1,2965,396,396,396,392,334);
Insert into `preisliste`  values (42,1,3017,396,396,396,396,354);
Insert into `preisliste`  values (43,1,3068,396,396,396,396,374);
Insert into `preisliste`  values (44,1,3119,396,396,396,396,395);
Insert into `preisliste`  values (45,1,3170,396,396,396,396,396);
Insert into `preisliste`  values (46,1,3221,396,396,396,396,396);
Insert into `preisliste`  values (47,1,3272,396,396,396,396,396);
Insert into `preisliste`  values (48,1,3323,396,396,396,396,396);
Insert into `preisliste`  values (49,1,3375,396,396,396,396,396);
Insert into `preisliste`  values (50,2,0,43,43,43,43,43);
Insert into `preisliste`  values (51,2,1023,44,43,43,43,43);
Insert into `preisliste`  values (52,2,1074,45,43,43,43,43);
Insert into `preisliste`  values (53,2,1125,47,43,43,43,43);
Insert into `preisliste`  values (54,2,1176,49,43,43,43,43);
Insert into `preisliste`  values (55,2,1227,51,43,43,43,43);
Insert into `preisliste`  values (56,2,1278,54,43,43,43,43);
Insert into `preisliste`  values (57,2,1329,57,44,43,43,43);
Insert into `preisliste`  values (58,2,1380,61,48,43,43,43);
Insert into `preisliste`  values (59,2,1432,67,54,43,43,43);
Insert into `preisliste`  values (60,2,1483,73,60,43,43,43);
Insert into `preisliste`  values (61,2,1534,79,66,43,43,43);
Insert into `preisliste`  values (62,2,1282,85,72,43,43,43);
Insert into `preisliste`  values (63,2,1636,92,78,43,43,43);
Insert into `preisliste`  values (64,2,1687,99,86,51,43,43);
Insert into `preisliste`  values (65,2,1738,108,95,60,43,43);
Insert into `preisliste`  values (66,2,1790,117,103,69,43,43);
Insert into `preisliste`  values (67,2,1841,125,112,77,43,43);
Insert into `preisliste`  values (68,2,1892,135,122,87,43,43);
Insert into `preisliste`  values (69,2,1943,145,132,97,54,43);
Insert into `preisliste`  values (70,2,1994,156,144,109,65,43);
Insert into `preisliste`  values (71,2,2045,169,155,121,77,43);
Insert into `preisliste`  values (72,2,2096,182,169,134,90,43);
Insert into `preisliste`  values (73,2,2147,195,182,147,103,51);
Insert into `preisliste`  values (74,2,2199,208,195,160,117,64);
Insert into `preisliste`  values (75,2,2250,221,208,173,129,77);
Insert into `preisliste`  values (76,2,2301,234,221,186,143,90);
Insert into `preisliste`  values (77,2,2352,247,234,199,155,103);
Insert into `preisliste`  values (78,2,2403,261,248,213,170,118);
Insert into `preisliste`  values (79,2,2454,275,262,228,184,132);
Insert into `preisliste`  values (80,2,2505,290,277,242,198,146);
Insert into `preisliste`  values (81,2,2556,305,292,257,214,162);
Insert into `preisliste`  values (82,2,2608,321,308,273,230,177);
Insert into `preisliste`  values (83,2,2659,337,324,289,245,193);
Insert into `preisliste`  values (84,2,2710,353,341,306,262,210);
Insert into `preisliste`  values (85,2,2761,370,357,323,279,227);
Insert into `preisliste`  values (86,2,2812,388,375,340,297,244);
Insert into `preisliste`  values (87,2,2863,396,393,358,314,262);
Insert into `preisliste`  values (88,2,2914,396,396,376,332,280);
Insert into `preisliste`  values (89,2,2965,396,396,394,351,299);
Insert into `preisliste`  values (90,2,3017,396,396,396,369,317);
Insert into `preisliste`  values (91,2,3068,396,396,396,387,335);
Insert into `preisliste`  values (92,2,3119,396,396,396,396,353);
Insert into `preisliste`  values (93,2,3170,396,396,396,396,372);
Insert into `preisliste`  values (94,2,3221,396,396,396,396,390);
Insert into `preisliste`  values (95,2,3272,396,396,396,396,396);
Insert into `preisliste`  values (96,2,3323,396,396,396,396,396);
Insert into `preisliste`  values (97,2,3375,396,396,396,396,396);
Insert into `preisliste`  values (98,3,0,38,38,38,38,38);
Insert into `preisliste`  values (99,3,1023,39,38,38,38,38);
Insert into `preisliste`  values (100,3,1074,40,38,38,38,38);
Insert into `preisliste`  values (101,3,1125,41,38,38,38,38);
Insert into `preisliste`  values (102,3,1176,43,38,38,38,38);
Insert into `preisliste`  values (103,3,1227,45,38,38,38,38);
Insert into `preisliste`  values (104,3,1278,48,38,38,38,38);
Insert into `preisliste`  values (105,3,1329,50,39,38,38,38);
Insert into `preisliste`  values (106,3,1380,54,42,38,38,38);
Insert into `preisliste`  values (107,3,1432,59,48,38,38,38);
Insert into `preisliste`  values (108,3,1483,64,53,38,38,38);
Insert into `preisliste`  values (109,3,1534,70,58,38,38,38);
Insert into `preisliste`  values (110,3,1282,75,64,38,38,38);
Insert into `preisliste`  values (111,3,1636,81,69,38,38,38);
Insert into `preisliste`  values (112,3,1687,87,76,46,38,38);
Insert into `preisliste`  values (113,3,1738,95,84,53,38,38);
Insert into `preisliste`  values (114,3,1790,103,92,61,38,38);
Insert into `preisliste`  values (115,3,1841,110,99,69,38,38);
Insert into `preisliste`  values (116,3,1892,119,107,77,38,38);
Insert into `preisliste`  values (117,3,1943,128,117,86,48,38);
Insert into `preisliste`  values (118,3,1994,138,127,96,58,38);
Insert into `preisliste`  values (119,3,2045,149,138,107,69,38);
Insert into `preisliste`  values (120,3,2096,161,149,118,80,38);
Insert into `preisliste`  values (121,3,2147,172,161,130,92,46);
Insert into `preisliste`  values (122,3,2199,184,172,141,103,57);
Insert into `preisliste`  values (123,3,2250,195,184,153,115,69);
Insert into `preisliste`  values (124,3,2301,207,195,164,126,80);
Insert into `preisliste`  values (125,3,2352,218,207,176,138,92);
Insert into `preisliste`  values (126,3,2403,230,219,188,150,104);
Insert into `preisliste`  values (127,3,2454,243,231,200,162,116);
Insert into `preisliste`  values (128,3,2505,256,244,214,175,129);
Insert into `preisliste`  values (129,3,2556,269,258,227,189,143);
Insert into `preisliste`  values (130,3,2608,283,271,241,202,156);
Insert into `preisliste`  values (131,3,2659,297,286,255,217,171);
Insert into `preisliste`  values (132,3,2710,312,300,269,231,185);
Insert into `preisliste`  values (133,3,2761,327,315,285,246,200);
Insert into `preisliste`  values (134,3,2812,342,331,300,262,216);
Insert into `preisliste`  values (135,3,2863,358,346,315,277,231);
Insert into `preisliste`  values (136,3,2914,374,363,332,293,247);
Insert into `preisliste`  values (137,3,2965,383,378,348,309,263);
Insert into `preisliste`  values (138,3,3017,383,383,364,326,280);
Insert into `preisliste`  values (139,3,3068,383,383,380,342,296);
Insert into `preisliste`  values (140,3,3119,383,383,383,358,312);
Insert into `preisliste`  values (141,3,3170,383,383,383,374,328);
Insert into `preisliste`  values (142,3,3221,383,383,383,383,344);
Insert into `preisliste`  values (143,3,3272,383,383,383,383,360);
Insert into `preisliste`  values (144,3,3323,383,383,383,383,376);
Insert into `preisliste`  values (145,3,3375,383,383,383,383,383);
Insert into `preisliste`  values (146,4,0,31,31,31,31,31);
Insert into `preisliste`  values (147,4,1023,31,31,31,31,31);
Insert into `preisliste`  values (148,4,1074,32,31,31,31,31);
Insert into `preisliste`  values (149,4,1125,33,31,31,31,31);
Insert into `preisliste`  values (150,4,1176,34,31,31,31,31);
Insert into `preisliste`  values (151,4,1227,36,31,31,31,31);
Insert into `preisliste`  values (152,4,1278,38,31,31,31,31);
Insert into `preisliste`  values (153,4,1329,40,31,31,31,31);
Insert into `preisliste`  values (154,4,1380,43,34,31,31,31);
Insert into `preisliste`  values (155,4,1432,47,38,31,31,31);
Insert into `preisliste`  values (156,4,1483,52,42,31,31,31);
Insert into `preisliste`  values (157,4,1534,56,47,31,31,31);
Insert into `preisliste`  values (158,4,1282,60,51,31,31,31);
Insert into `preisliste`  values (159,4,1636,64,55,31,31,31);
Insert into `preisliste`  values (160,4,1687,70,61,36,31,31);
Insert into `preisliste`  values (161,4,1738,76,67,42,31,31);
Insert into `preisliste`  values (162,4,1790,82,73,49,31,31);
Insert into `preisliste`  values (163,4,1841,88,79,55,31,31);
Insert into `preisliste`  values (164,4,1892,95,86,61,31,31);
Insert into `preisliste`  values (165,4,1943,102,93,69,38,31);
Insert into `preisliste`  values (166,4,1994,110,101,77,46,31);
Insert into `preisliste`  values (167,4,2045,119,110,82,55,31);
Insert into `preisliste`  values (168,4,2096,128,119,95,64,31);
Insert into `preisliste`  values (169,4,2147,138,128,204,73,36);
Insert into `preisliste`  values (170,4,2199,147,138,113,82,46);
Insert into `preisliste`  values (171,4,2250,156,147,122,92,55);
Insert into `preisliste`  values (172,4,2301,165,156,131,101,64);
Insert into `preisliste`  values (173,4,2352,174,165,141,110,73);
Insert into `preisliste`  values (174,4,2403,184,175,150,120,83);
Insert into `preisliste`  values (175,4,2454,194,185,161,130,93);
Insert into `preisliste`  values (176,4,2505,205,195,171,140,103);
Insert into `preisliste`  values (177,4,2556,261,206,182,151,114);
Insert into `preisliste`  values (178,4,2608,274,217,193,162,125);
Insert into `preisliste`  values (179,4,2659,286,229,204,173,137);
Insert into `preisliste`  values (180,4,2710,299,240,253,185,148);
Insert into `preisliste`  values (181,4,2761,307,252,265,197,160);
Insert into `preisliste`  values (182,4,2812,307,164,278,209,172);
Insert into `preisliste`  values (183,4,2863,307,277,291,222,185);
Insert into `preisliste`  values (184,4,2914,307,290,304,235,198);
Insert into `preisliste`  values (185,4,2965,307,303,307,247,211);
Insert into `preisliste`  values (186,4,3017,307,307,307,260,223);
Insert into `preisliste`  values (187,4,3068,307,307,307,274,237);
Insert into `preisliste`  values (188,4,3119,307,307,307,286,250);
Insert into `preisliste`  values (189,4,3170,307,307,307,299,262);
Insert into `preisliste`  values (190,4,3221,307,307,307,307,275);
Insert into `preisliste`  values (191,4,3272,307,307,307,307,288);
Insert into `preisliste`  values (192,4,3323,307,307,307,307,301);
Insert into `preisliste`  values (193,4,3375,307,307,307,307,307);
Insert into `preisliste`  values (194,5,0,26,26,26,26,26);
Insert into `preisliste`  values (195,5,1023,26,26,26,26,26);
Insert into `preisliste`  values (196,5,1074,27,26,26,26,26);
Insert into `preisliste`  values (197,5,1125,28,26,26,26,26);
Insert into `preisliste`  values (198,5,1176,29,26,26,26,26);
Insert into `preisliste`  values (199,5,1227,30,26,26,26,26);
Insert into `preisliste`  values (200,5,1278,32,26,26,26,26);
Insert into `preisliste`  values (201,5,1329,34,26,26,26,26);
Insert into `preisliste`  values (202,5,1380,36,29,26,26,26);
Insert into `preisliste`  values (203,5,1432,39,32,26,26,26);
Insert into `preisliste`  values (204,5,1483,43,35,26,26,26);
Insert into `preisliste`  values (205,5,1534,47,39,26,26,26);
Insert into `preisliste`  values (206,5,1282,50,42,26,26,26);
Insert into `preisliste`  values (207,5,1636,54,46,26,26,26);
Insert into `preisliste`  values (208,5,1687,58,51,30,26,26);
Insert into `preisliste`  values (209,5,1738,63,56,35,26,26);
Insert into `preisliste`  values (210,5,1790,69,61,40,26,26);
Insert into `preisliste`  values (211,5,1841,74,66,46,26,26);
Insert into `preisliste`  values (212,5,1892,79,72,51,26,26);
Insert into `preisliste`  values (213,5,1943,85,78,57,32,26);
Insert into `preisliste`  values (214,5,1994,92,84,64,38,26);
Insert into `preisliste`  values (215,5,2045,99,92,71,46,26);
Insert into `preisliste`  values (216,5,2096,107,99,79,53,26);
Insert into `preisliste`  values (217,5,2147,115,107,86,61,30);
Insert into `preisliste`  values (218,5,2199,122,115,94,69,38);
Insert into `preisliste`  values (219,5,2250,130,122,102,76,46);
Insert into `preisliste`  values (220,5,2301,138,130,209,84,53);
Insert into `preisliste`  values (221,5,2352,145,138,117,92,61);
Insert into `preisliste`  values (222,5,2403,153,146,125,100,69);
Insert into `preisliste`  values (223,5,2454,153,153,134,108,78);
Insert into `preisliste`  values (224,5,2505,153,153,143,117,86);
Insert into `preisliste`  values (225,5,2556,153,153,151,126,95);
Insert into `preisliste`  values (226,5,2608,153,153,153,135,104);
Insert into `preisliste`  values (227,5,2659,153,153,153,145,114);
Insert into `preisliste`  values (228,5,2710,153,153,153,153,124);
Insert into `preisliste`  values (229,5,2761,153,153,153,153,133);
Insert into `preisliste`  values (230,5,2812,153,153,153,153,144);
Insert into `preisliste`  values (231,5,2863,153,153,153,153,153);
Insert into `preisliste`  values (232,5,2914,153,153,153,153,153);
Insert into `preisliste`  values (233,5,2965,153,153,153,153,153);
Insert into `preisliste`  values (234,5,3017,153,153,153,153,153);
Insert into `preisliste`  values (235,5,3068,153,153,153,153,153);
Insert into `preisliste`  values (236,5,3119,153,153,153,153,153);
Insert into `preisliste`  values (237,5,3170,153,153,153,153,153);
Insert into `preisliste`  values (238,5,3221,153,153,153,153,153);
Insert into `preisliste`  values (239,5,3272,153,153,153,153,153);
Insert into `preisliste`  values (240,5,3323,153,153,153,153,153);
Insert into `preisliste`  values (241,5,3375,153,153,153,153,153);
Insert into `preisliste`  values (242,6,0,27,27,27,27,27);
Insert into `preisliste`  values (243,6,1023,27,27,27,27,27);
Insert into `preisliste`  values (244,6,1074,28,27,27,27,27);
Insert into `preisliste`  values (245,6,1125,29,27,27,27,27);
Insert into `preisliste`  values (246,6,1176,30,27,27,27,27);
Insert into `preisliste`  values (247,6,1227,32,27,27,27,27);
Insert into `preisliste`  values (248,6,1278,34,27,27,27,27);
Insert into `preisliste`  values (249,6,1329,35,27,27,27,27);
Insert into `preisliste`  values (250,6,1380,38,30,27,27,27);
Insert into `preisliste`  values (251,6,1432,42,34,27,27,27);
Insert into `preisliste`  values (252,6,1483,46,37,27,27,27);
Insert into `preisliste`  values (253,6,1534,49,41,27,27,27);
Insert into `preisliste`  values (254,6,1282,53,45,27,75,27);
Insert into `preisliste`  values (255,6,1636,57,49,27,27,27);
Insert into `preisliste`  values (256,6,1687,62,54,32,27,27);
Insert into `preisliste`  values (257,6,1738,67,59,37,27,27);
Insert into `preisliste`  values (258,6,1790,73,64,43,27,27);
Insert into `preisliste`  values (259,6,1841,78,70,48,27,27);
Insert into `preisliste`  values (260,6,1892,84,76,54,27,27);
Insert into `preisliste`  values (261,6,1943,91,82,61,34,27);
Insert into `preisliste`  values (262,6,1994,98,89,68,41,27);
Insert into `preisliste`  values (263,6,2045,105,97,75,48,27);
Insert into `preisliste`  values (264,6,2096,113,105,83,56,27);
Insert into `preisliste`  values (265,6,2147,121,113,92,64,32);
Insert into `preisliste`  values (266,6,2199,130,121,100,73,40);
Insert into `preisliste`  values (267,6,2250,138,130,108,81,48);
Insert into `preisliste`  values (268,6,2301,146,138,116,89,56);
Insert into `preisliste`  values (269,6,2352,154,146,124,97,64);
Insert into `preisliste`  values (270,6,2403,163,154,133,106,73);
Insert into `preisliste`  values (271,6,2454,172,163,142,115,82);
Insert into `preisliste`  values (272,6,2505,181,173,151,124,91);
Insert into `preisliste`  values (273,6,2556,192,182,160,133,101);
Insert into `preisliste`  values (274,6,2608,192,192,170,143,111);
Insert into `preisliste`  values (275,6,2659,192,192,180,153,121);
Insert into `preisliste`  values (276,6,2710,192,192,192,163,131);
Insert into `preisliste`  values (277,6,2761,192,192,192,174,141);
Insert into `preisliste`  values (278,6,2812,192,192,192,185,152);
Insert into `preisliste`  values (279,6,2863,192,192,192,192,163);
Insert into `preisliste`  values (280,6,2914,192,192,192,192,175);
Insert into `preisliste`  values (281,6,2965,192,192,192,192,186);
Insert into `preisliste`  values (282,6,3017,192,192,192,192,192);
Insert into `preisliste`  values (283,6,3068,192,192,192,192,192);
Insert into `preisliste`  values (284,6,3119,192,192,192,192,192);
Insert into `preisliste`  values (285,6,3170,192,192,192,192,192);
Insert into `preisliste`  values (286,6,3221,192,192,192,192,192);
Insert into `preisliste`  values (287,6,3272,192,192,192,192,192);
Insert into `preisliste`  values (288,6,3323,192,192,192,192,192);
Insert into `preisliste`  values (289,6,3375,192,192,192,192,192);
Insert into `preisliste`  values (290,7,0,36,36,36,36,36);
Insert into `preisliste`  values (291,7,1023,36,36,36,36,36);
Insert into `preisliste`  values (292,7,1074,37,36,36,36,36);
Insert into `preisliste`  values (293,7,1125,39,36,36,36,36);
Insert into `preisliste`  values (294,7,1176,40,36,36,36,36);
Insert into `preisliste`  values (295,7,1227,42,36,36,36,36);
Insert into `preisliste`  values (296,7,1278,44,36,36,36,36);
Insert into `preisliste`  values (297,7,1329,47,36,36,36,36);
Insert into `preisliste`  values (298,7,1380,51,40,36,36,36);
Insert into `preisliste`  values (299,7,1432,55,44,36,36,36);
Insert into `preisliste`  values (300,7,1483,60,50,36,36,36);
Insert into `preisliste`  values (301,7,1534,65,54,36,36,36);
Insert into `preisliste`  values (302,7,1282,70,59,36,36,36);
Insert into `preisliste`  values (303,7,1636,75,64,36,36,36);
Insert into `preisliste`  values (304,7,1687,82,71,42,36,36);
Insert into `preisliste`  values (305,7,1738,89,78,50,36,36);
Insert into `preisliste`  values (306,7,1790,96,85,57,36,36);
Insert into `preisliste`  values (307,7,1841,103,93,64,36,36);
Insert into `preisliste`  values (308,7,1892,111,100,72,36,36);
Insert into `preisliste`  values (309,7,1943,120,109,80,44,36);
Insert into `preisliste`  values (310,7,1994,129,118,89,54,36);
Insert into `preisliste`  values (311,7,2045,139,128,100,64,36);
Insert into `preisliste`  values (312,7,2096,150,139,110,75,36);
Insert into `preisliste`  values (313,7,2147,161,150,121,85,42);
Insert into `preisliste`  values (314,7,2199,171,161,132,96,75);
Insert into `preisliste`  values (315,7,2250,182,171,143,107,85);
Insert into `preisliste`  values (316,7,2301,193,182,153,118,97);
Insert into `preisliste`  values (317,7,2352,203,193,164,128,108);
Insert into `preisliste`  values (318,7,2403,207,204,175,140,121);
Insert into `preisliste`  values (319,7,2454,207,207,187,151,133);
Insert into `preisliste`  values (320,7,2505,207,207,199,164,146);
Insert into `preisliste`  values (321,7,2556,207,207,207,176,160);
Insert into `preisliste`  values (322,7,2608,207,207,207,189,173);
Insert into `preisliste`  values (323,7,2659,207,207,207,202,187);
Insert into `preisliste`  values (324,7,2710,207,207,207,207,201);
Insert into `preisliste`  values (325,7,2761,207,207,207,207,207);
Insert into `preisliste`  values (326,7,2812,207,207,207,207,207);
Insert into `preisliste`  values (327,7,2863,207,207,207,207,207);
Insert into `preisliste`  values (328,7,2914,207,207,207,207,207);
Insert into `preisliste`  values (329,7,2965,207,207,207,207,207);
Insert into `preisliste`  values (330,7,3017,207,207,207,207,207);
Insert into `preisliste`  values (331,7,3068,207,207,207,207,207);
Insert into `preisliste`  values (332,7,3119,207,207,207,207,207);
Insert into `preisliste`  values (333,7,3170,207,207,207,207,207);
Insert into `preisliste`  values (334,7,3221,207,207,207,207,207);
Insert into `preisliste`  values (335,7,3272,207,207,207,207,207);
Insert into `preisliste`  values (336,7,3323,207,207,207,207,207);
Insert into `preisliste`  values (337,7,3375,207,207,207,207,207);
Insert into `preisliste`  values (338,8,0,31,31,31,31,31);
Insert into `preisliste`  values (339,8,1023,31,31,31,31,31);
Insert into `preisliste`  values (340,8,1074,32,31,31,31,31);
Insert into `preisliste`  values (341,8,1125,33,31,31,31,31);
Insert into `preisliste`  values (342,8,1176,34,31,31,31,31);
Insert into `preisliste`  values (343,8,1227,36,31,31,31,31);
Insert into `preisliste`  values (344,8,1278,38,31,31,31,31);
Insert into `preisliste`  values (345,8,1329,40,31,31,31,31);
Insert into `preisliste`  values (346,8,1380,43,34,31,31,31);
Insert into `preisliste`  values (347,8,1432,47,38,31,31,31);
Insert into `preisliste`  values (348,8,1483,52,42,31,31,31);
Insert into `preisliste`  values (349,8,1534,56,47,31,31,31);
Insert into `preisliste`  values (350,8,1282,60,51,31,31,31);
Insert into `preisliste`  values (351,8,1636,64,55,31,31,31);
Insert into `preisliste`  values (352,8,1687,70,61,36,31,31);
Insert into `preisliste`  values (353,8,1738,76,67,42,31,31);
Insert into `preisliste`  values (354,8,1790,82,73,49,31,31);
Insert into `preisliste`  values (355,8,1841,88,79,55,31,31);
Insert into `preisliste`  values (356,8,1892,95,86,61,31,31);
Insert into `preisliste`  values (357,8,1943,102,93,69,38,31);
Insert into `preisliste`  values (358,8,1994,110,101,77,46,31);
Insert into `preisliste`  values (359,8,2045,119,110,85,55,31);
Insert into `preisliste`  values (360,8,2096,128,119,95,64,31);
Insert into `preisliste`  values (361,8,2147,138,128,104,73,36);
Insert into `preisliste`  values (362,8,2199,147,138,113,82,46);
Insert into `preisliste`  values (363,8,2250,156,147,122,92,55);
Insert into `preisliste`  values (364,8,2301,165,156,131,101,64);
Insert into `preisliste`  values (365,8,2352,174,165,141,110,73);
Insert into `preisliste`  values (366,8,2403,184,175,150,120,83);
Insert into `preisliste`  values (367,8,2454,194,185,161,130,93);
Insert into `preisliste`  values (368,8,2505,206,195,171,140,103);
Insert into `preisliste`  values (369,8,2556,207,206,182,151,114);
Insert into `preisliste`  values (370,8,2608,207,207,193,162,125);
Insert into `preisliste`  values (371,8,2659,207,207,204,173,137);
Insert into `preisliste`  values (372,8,2710,207,207,207,185,148);
Insert into `preisliste`  values (373,8,2761,207,207,207,197,160);
Insert into `preisliste`  values (374,8,2812,207,207,207,207,172);
Insert into `preisliste`  values (375,8,2863,207,207,207,207,185);
Insert into `preisliste`  values (376,8,2914,207,207,207,207,198);
Insert into `preisliste`  values (377,8,2965,207,207,207,207,207);
Insert into `preisliste`  values (378,8,3017,207,207,207,207,207);
Insert into `preisliste`  values (379,8,3068,207,207,207,207,207);
Insert into `preisliste`  values (380,8,3119,207,207,207,207,207);
Insert into `preisliste`  values (381,8,3170,207,207,207,207,207);
Insert into `preisliste`  values (382,8,3221,207,207,207,207,207);
Insert into `preisliste`  values (383,8,3272,207,207,207,207,207);
Insert into `preisliste`  values (384,8,3323,207,207,207,207,207);
Insert into `preisliste`  values (385,8,3375,207,207,207,207,207);
Insert into `preisliste`  values (386,9,0,23,23,23,23,23);
Insert into `preisliste`  values (387,9,1023,23,23,23,23,23);
Insert into `preisliste`  values (388,9,1074,24,23,23,23,23);
Insert into `preisliste`  values (389,9,1125,25,23,23,23,23);
Insert into `preisliste`  values (390,9,1176,26,23,23,23,23);
Insert into `preisliste`  values (391,9,1227,27,23,23,23,23);
Insert into `preisliste`  values (392,9,1278,29,23,23,23,23);
Insert into `preisliste`  values (393,9,1329,30,23,23,23,23);
Insert into `preisliste`  values (394,9,1380,32,26,23,23,23);
Insert into `preisliste`  values (395,9,1432,35,29,23,23,23);
Insert into `preisliste`  values (396,9,1483,39,32,23,23,23);
Insert into `preisliste`  values (397,9,1534,42,35,23,23,23);
Insert into `preisliste`  values (398,9,1282,45,38,23,23,23);
Insert into `preisliste`  values (399,9,1636,49,41,23,23,23);
Insert into `preisliste`  values (400,9,1687,53,46,27,23,23);
Insert into `preisliste`  values (401,9,1738,57,50,32,23,23);
Insert into `preisliste`  values (402,9,1790,62,55,36,23,23);
Insert into `preisliste`  values (403,9,1841,66,59,41,23,23);
Insert into `preisliste`  values (404,9,1892,72,54,46,23,23);
Insert into `preisliste`  values (405,9,1943,77,70,52,29,23);
Insert into `preisliste`  values (406,9,1994,83,76,58,35,23);
Insert into `preisliste`  values (407,9,2045,89,82,64,41,23);
Insert into `preisliste`  values (408,9,2096,96,89,71,48,23);
Insert into `preisliste`  values (409,9,2147,103,96,78,55,27);
Insert into `preisliste`  values (410,9,2199,110,103,85,62,34);
Insert into `preisliste`  values (411,9,2250,117,110,92,69,41);
Insert into `preisliste`  values (412,9,2301,124,117,99,76,48);
Insert into `preisliste`  values (413,9,2352,131,124,105,82,55);
Insert into `preisliste`  values (414,9,2403,138,131,113,90,62);
Insert into `preisliste`  values (415,9,2454,146,139,120,97,70);
Insert into `preisliste`  values (416,9,2505,153,147,128,105,78);
Insert into `preisliste`  values (417,9,2556,162,154,136,113,85);
Insert into `preisliste`  values (418,9,2608,174,163,145,122,94);
Insert into `preisliste`  values (419,9,2659,174,171,153,130,102);
Insert into `preisliste`  values (420,9,2710,174,174,162,139,111);
Insert into `preisliste`  values (421,9,2761,174,174,171,148,120);
Insert into `preisliste`  values (422,9,2812,174,174,174,157,129);
Insert into `preisliste`  values (423,9,2863,174,174,174,166,139);
Insert into `preisliste`  values (424,9,2914,174,174,174,174,148);
Insert into `preisliste`  values (425,9,2965,174,174,174,174,158);
Insert into `preisliste`  values (426,9,3017,174,174,174,174,168);
Insert into `preisliste`  values (427,9,3068,174,174,174,174,174);
Insert into `preisliste`  values (428,9,3119,174,174,174,174,174);
Insert into `preisliste`  values (429,9,3170,174,174,174,174,174);
Insert into `preisliste`  values (430,9,3221,174,174,174,174,174);
Insert into `preisliste`  values (431,9,3272,174,174,174,174,174);
Insert into `preisliste`  values (432,9,3323,174,174,174,174,174);
Insert into `preisliste`  values (433,9,3375,174,174,174,174,174);
Insert into `preisliste`  values (434,10,0,15,15,15,15,15);
Insert into `preisliste`  values (435,10,1023,15,15,15,15,15);
Insert into `preisliste`  values (436,10,1074,16,15,15,15,15);
Insert into `preisliste`  values (437,10,1125,16,15,15,15,15);
Insert into `preisliste`  values (438,10,1176,17,15,15,15,15);
Insert into `preisliste`  values (439,10,1227,18,15,15,15,15);
Insert into `preisliste`  values (440,10,1278,19,15,15,15,15);
Insert into `preisliste`  values (441,10,1329,20,15,15,15,15);
Insert into `preisliste`  values (442,10,1380,21,17,15,15,15);
Insert into `preisliste`  values (443,10,1432,24,19,15,15,15);
Insert into `preisliste`  values (444,10,1483,26,21,15,15,15);
Insert into `preisliste`  values (445,10,1534,28,24,15,15,15);
Insert into `preisliste`  values (446,10,1282,30,26,15,15,15);
Insert into `preisliste`  values (447,10,1636,32,28,15,15,15);
Insert into `preisliste`  values (448,10,1687,35,30,18,15,15);
Insert into `preisliste`  values (449,10,1738,38,33,21,15,15);
Insert into `preisliste`  values (450,10,1790,41,36,24,15,15);
Insert into `preisliste`  values (451,10,1841,44,39,27,15,15);
Insert into `preisliste`  values (452,10,1892,48,43,31,15,15);
Insert into `preisliste`  values (453,10,1943,51,47,34,19,15);
Insert into `preisliste`  values (454,10,1994,55,51,38,23,15);
Insert into `preisliste`  values (455,10,2045,59,55,42,27,15);
Insert into `preisliste`  values (456,10,2096,64,59,47,32,15);
Insert into `preisliste`  values (457,10,2147,69,64,52,36,18);
Insert into `preisliste`  values (458,10,2199,73,69,56,41,22);
Insert into `preisliste`  values (459,10,2250,77,73,61,46,27);
Insert into `preisliste`  values (460,10,2301,77,77,65,50,32);
Insert into `preisliste`  values (461,10,2352,77,77,70,55,36);
Insert into `preisliste`  values (462,10,2403,77,77,75,60,41);
Insert into `preisliste`  values (463,10,2454,77,77,77,65,47);
Insert into `preisliste`  values (464,10,2505,77,77,77,70,52);
Insert into `preisliste`  values (465,10,2556,77,77,77,76,57);
Insert into `preisliste`  values (466,10,2608,77,77,77,77,62);
Insert into `preisliste`  values (467,10,2659,77,77,77,77,69);
Insert into `preisliste`  values (468,10,2710,77,77,77,77,74);
Insert into `preisliste`  values (469,10,2761,77,77,77,77,77);
Insert into `preisliste`  values (470,10,2812,77,77,77,77,77);
Insert into `preisliste`  values (471,10,2863,77,77,77,77,77);
Insert into `preisliste`  values (472,10,2914,77,77,77,77,77);
Insert into `preisliste`  values (473,10,2965,77,77,77,77,77);
Insert into `preisliste`  values (474,10,3017,77,77,77,77,77);
Insert into `preisliste`  values (475,10,3068,77,77,77,77,77);
Insert into `preisliste`  values (476,10,3119,77,77,77,77,77);
Insert into `preisliste`  values (477,10,3170,77,77,77,77,77);
Insert into `preisliste`  values (478,10,3221,77,77,77,77,77);
Insert into `preisliste`  values (479,10,3272,77,77,77,77,77);
Insert into `preisliste`  values (480,10,3323,77,77,77,77,77);
Insert into `preisliste`  values (481,10,3375,77,77,77,77,77);
