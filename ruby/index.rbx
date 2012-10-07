require 'rubygems'
require 'oci8'
require 'cgi'

#require 'config.rb'
#config= DatabaseConfig.new
require "db_config"

title="Kindergarten Wartelistenüberblick"

cgi = CGI.new("xhtml10")
params=cgi.params
child = nil
rank = nil
if(not(params.has_key?("hash")))
	error_message="No hash specified"
else
	hashvalue=params["hash"].first
	o = OCI8.new(username, password, '//oracle.informatik.haw-hamburg.de:1521/inf09')
	sql_child = "select k.vorname,k.nachname,w.ident,w.wartelistentyp,kw.datum_registrierung from (((Select * from kind where hashvalue=:1) k join kind_warteliste kw on k.ident = kw.kind_id) join warteliste w on kw.warteliste_id=w.ident)"
	cursor = o.parse(sql_child)
	cursor.bind_param(1,hashvalue)
        cursor.exec
	child = cursor.fetch_hash()
	if(child.nil?) 
		error_message="No child found" 
	else
		sql_rank = "select (count (*)) from kind_warteliste where warteliste_id=:1 and datum_registrierung<= :2"
 		cursor = o.parse(sql_rank)
		cursor.bind_param(1,child["IDENT"])
		cursor.bind_param(2,child["DATUM_REGISTRIERUNG"])
		cursor.exec
		rank= cursor.fetch
	end
	cursor.close
	o.logoff
end



puts "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""
puts "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
puts "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
puts "<head><title>"+title+"</title></head>"
puts "<body>"
if(error_message.nil?)
	puts child
	puts "Vorname"
	puts child["VORNAME"]
	puts "Nachname"
	puts child["NACHNAME"]
	puts "Wartelistentyp"
	puts child["WARTELISTENTYP"]
	puts child["IDENT"]
	puts rank[0].to_i
else
	puts "<p>"+error_message+"</p>"
end
puts "</body>"
