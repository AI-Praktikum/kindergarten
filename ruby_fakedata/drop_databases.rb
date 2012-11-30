#!/usr/bin/ruby

require 'db_config'
require 'rubygems'
require 'mysql'

con = Mysql.new(host,username, password)
File.open(ARGV[0],"r"){|file|
	file.each_line { |kindergarten|
		puts "Kindergarten: " + kindergarten 
		sql_db = "drop database if exists #{kindergarten}"
		pst_db = con.prepare(sql_db)
		pst_db.execute
		pst_db.close
		con.query("grant all on #{kindergarten}.* to #{kindergarten} identified by '#{kindergarten}'")
		sql_user = "drop user #{kindergarten}"
		pst_user = con.prepare(sql_user)
		pst_user.execute
		pst_user.close
	}
}
con.close
