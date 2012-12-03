#!/usr/bin/ruby

require 'db_config'
require 'rubygems'
require 'mysql'

out=File.open("drop.sql","w")
File.open(ARGV[0],"r"){|file|
	file.each_line { |kindergarten|
		kindergarten=kindergarten.strip
		puts "Kindergarten: " + kindergarten 
		out.puts "drop database if exists #{kindergarten};"
		out.puts "grant all on #{kindergarten}.* to #{kindergarten} identified by '#{kindergarten}';"
		out.puts "drop user #{kindergarten};"

	}
}
