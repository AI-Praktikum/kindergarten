require 'rubygems'
require 'oci8'
require 'cgi'

#require 'config.rb'
#config= DatabaseConfig.new
require "db_config"

title="Kindergarten Wartelistenüberblick"

cgi = CGI.new("xhtml10")
params=cgi.params
children = []
child = nil
rank = nil
if(not(params.has_key?("hash")))
	error_message="No hash specified"
else
	hashvalue=params["hash"].first
	o = OCI8.new(username, password, '//oracle.informatik.haw-hamburg.de:1521/inf09')
	sql_child = "select k.vorname,k.nachname,w.ident,w.wartelistentyp,kw.datum_registrierung from (((Select * from kind where hashvalue=:1) k join registrierung kw on k.ident = kw.kind_id) join warteliste w on kw.warteliste_id=w.ident)"
	cursor_child = o.parse(sql_child)
	cursor_child.bind_param(1,hashvalue)
        cursor_child.exec
	child = cursor_child.fetch_hash()
	#puts child
	while(not(child.nil?))
		sql_rank = "select (count (*)) from registrierung where warteliste_id=:1 and datum_registrierung<= :2"
 		cursor_rank = o.parse(sql_rank)
		cursor_rank.bind_param(1,child["IDENT"])
		cursor_rank.bind_param(2,child["DATUM_REGISTRIERUNG"])
		cursor_rank.exec
		child["RANK"]= cursor_rank.fetch[0].to_i
		children = children << child
		cursor_rank.close
		child = cursor_child.fetch_hash()
		#puts child
	end
	cursor_child.close
	if(children.empty?) 
		error_message="No child found"
	end
	o.logoff
end



puts "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""
puts "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
puts "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
puts "<head><title>"+title+"</title></head>"
puts "<body>"
if(error_message.nil?)
	puts "<h1>Die Wartelistenplatzierungen Ihres Kindes</h1>"
	children.each{|x|
		puts "<h2>Warteliste</h2>"
		puts "<p class=\"queue\">" + x["WARTELISTENTYP"] +"</p>"
		puts "<h3>Name:</h3>"
		puts "<p class=\"fullname\">" + x["NACHNAME"] + ", " + x["VORNAME"] +"</p>"
		puts "<h3>Platzierung:</h3>"		
		puts "<p class=\"rank\">" + x["RANK"].to_s + "</p>"
	}
else
	puts "<p class=\"error\">"+error_message+"</p>"
end
puts "</body>"
