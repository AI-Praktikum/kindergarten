#!/usr/bin/ruby

require 'db_config'
require 'rubygems'
require 'mysql'
require 'faker'

children_in_queue=1
max_children_per_group=50
children_per_group=1
groups=1

create_db_stmts=["CREATE TABLE `preismodell` (`ident` bigint(20) NOT NULL AUTO_INCREMENT,`bezeichnung` text NOT NULL,`dauer` bigint(20) NOT NULL,PRIMARY KEY (`ident`))",
		"CREATE TABLE `elternteil` (`ident` bigint(20) NOT NULL AUTO_INCREMENT,	`name` text NOT NULL,	`familiengroesse` bigint(20) NOT NULL,	`adresse` text(200) NOT NULL,`nettoeinkommen` bigint(20) NOT NULL,`facebook_id` text(20),PRIMARY KEY (`ident`))",
		"CREATE TABLE `kind` (`ident` bigint(20) NOT NULL AUTO_INCREMENT,`Vorname` text NOT NULL,`Nachname` text NOT NULL,`Geburtsdatum` date NOT NULL,`HashValue` bigint(20) NOT NULL,`Elternteil_id`  bigint(20) NOT NULL,`preismodell_id`  bigint(20) NOT NULL, FOREIGN KEY (`Elternteil_id`) REFERENCES `elternteil`(ident), FOREIGN KEY (`preismodell_id`) REFERENCES `preismodell`(ident), PRIMARY KEY (`ident`))",
		"CREATE TABLE `kindergarten` (`ident` bigint(20) NOT NULL AUTO_INCREMENT, `bezeichnung` text NOT NULL, `adresse` text(200) NOT NULL,		  `maxplaetze` bigint(20) NOT NULL,		  `kontonummer` bigint(20) NOT NULL,		  `blz` bigint(20) NOT NULL,		  PRIMARY KEY (`ident`)		)",
		"CREATE TABLE `warteliste` (			`ident` bigint(20) NOT NULL AUTO_INCREMENT,			`wartelistentyp` text NOT NULL,			PRIMARY KEY (`ident`)		)",
		"CREATE TABLE `gruppe` (		  `ident` bigint(20) NOT NULL AUTO_INCREMENT,		  `gruppengroesse` bigint(20) NOT NULL,		  `bezeichnung` text NOT NULL,		  `kindergarten_id` bigint(20) NOT NULL,		  `warteliste_id` bigint(20) NOT NULL,		  FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(ident),		  FOREIGN KEY (`warteliste_id`) REFERENCES `warteliste`(ident),		  PRIMARY KEY (`ident`)		)",
		"CREATE TABLE `kindergarten_preismodell` (  `kindergarten_id` bigint(20) NOT NULL,  `preismodell_id` bigint(20) NOT NULL,  FOREIGN KEY (`preismodell_id`) REFERENCES `preismodell`(ident),  FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(ident),  PRIMARY KEY (`kindergarten_id`, `preismodell_id`))",
		"CREATE TABLE `kind_gruppe` (  `gruppe_id` bigint(20) NOT NULL,  `kind_id` bigint(20) NOT NULL,  FOREIGN KEY (`gruppe_id`) REFERENCES `gruppe`(ident),  FOREIGN KEY (`kind_id`) REFERENCES `kind`(ident),  PRIMARY KEY (`kind_id`, `gruppe_id`))",
		"CREATE TABLE `registrierung` (  `kind_id` bigint(20) NOT NULL,  `warteliste_id` bigint(20) NOT NULL,  `datum_registrierung` TIMESTAMP NOT NULL default now(),  FOREIGN KEY (`kind_id`) REFERENCES `kind`(ident),  FOREIGN KEY (`warteliste_id`) REFERENCES `warteliste`(ident),  PRIMARY KEY (`kind_id`, `warteliste_id`))"]

starttime=Time.now
puts starttime
x=0
File.open("kindergarten.csv","r"){|file|
	file.each_line { |kindergarten|
		kindergarten=kindergarten.strip
		x=x+1
		begin
			con = Mysql.new(host,username, password)
			puts "Kindergarten: " + kindergarten
			sql_db = "create database if not exists #{kindergarten}"
			pst_db = con.prepare(sql_db)
			pst_db.execute
			pst_db.close
			grant = "grant all on #{kindergarten}.* to #{kindergarten}@'%' identified by '#{kindergarten}'"
			pst_grant= con.prepare(grant)
			pst_grant.execute
			pst_grant.close
			con.query("use #{kindergarten}")
			create_db_stmts.each{|stmt|
				con.query(stmt)
			}
			#inserting
		
			#elternteil
			((children_per_group*groups)+children_in_queue).times{|x|
				con.query("insert into `elternteil` values('','#{con.escape_string(Faker::Name.name)}',#{1+rand(5)},'#{con.escape_string(Faker::Address.street_address)}',#{1000+rand(2000)},null)")
			}
				
			#preismodell: let's get rid of that
			con.query "insert into `preismodell` values(1,'a',12);"
			con.query "insert into `preismodell` values(2,'b',10);"
			con.query "insert into `preismodell` values(3,'c',8);"
			con.query "insert into `preismodell` values(4,'d',6);"
			con.query "insert into `preismodell` values(5,'e',4);"
			con.query "insert into `preismodell` values(6,'f',5);"
			con.query "insert into `preismodell` values(7,'g',7);"
			con.query "insert into `preismodell` values(8,'h',5);"
			con.query "insert into `preismodell` values(9,'i',3);"
			con.query "insert into `preismodell` values(10,'j',2);"
		
			#kindergarten
			con.query "insert into `kindergarten` values('','#{kindergarten}', 'Musterstrasse',20,12345,6789)"
		
			#kinder
			((children_per_group*groups)+children_in_queue).times{|x|
				x=x+1
				birthday=(2005+rand(3)).to_s + "." + (1+rand(11)).to_s + "." + (1+rand(25)).to_s
				con.query "insert into `kind` values('','#{con.escape_string(Faker::Name.first_name)}','#{con.escape_string(Faker::Name.last_name)}','#{birthday}',#{x},#{x},#{1+rand(9)})"
			}
		
			#wartelisten
			con.query "insert into `warteliste` values(1, 'frueh');"
			con.query "insert into `warteliste` values(2, 'vormittags');"
			con.query "insert into `warteliste` values(3, 'nachmittags');"
			con.query "insert into `warteliste` values(4, 'spaet');"
			con.query "insert into `warteliste` values(5, 'ganztags');"
			
			#gruppe
			groups.times{|x|
				con.query "insert into `gruppe` values('',#{max_children_per_group}, '#{con.escape_string(Faker::Name.first_name)}', 1, #{(1+rand(4))});"
			}
		
			#kind_gruppe
			(children_per_group*groups).times{|x|
				x=x+1
				con.query "insert into `kind_gruppe` values(#{(x%groups)+1},#{x})"
			}
		
			#registrierung
			to=((children_per_group*groups)+children_in_queue)
			from=(children_per_group*groups)
			(from..to).each{|x|
				con.query "insert into `registrierung` (`kind_id`,`warteliste_id`) values(#{x},#{(x%5)+1})"
			}
		rescue Mysql::Error => e
            		puts "could not create database " + kindergarten + " because of " + e.to_s
			puts "Waiting 5 seconds"
			sleep 5
	        ensure
	            con.close if con
        	end
		if (x%100) ==0
			puts "Waiting 5 seconds"
			sleep 5
		end
	}
}
stoptime=Time.now
puts "Creating the databases took me " + ((stoptime-starttime)/60).to_s + " minutes"
