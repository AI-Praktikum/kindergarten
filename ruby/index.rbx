require 'rubygems'
require 'oci8'
require 'cgi'

require 'config.rbx'

title="Kindergarten Wartelistenüberblick"

cgi = CGI.new("xhtml10")
params=cgi.params

if(not(params.has_key?("hash")))
	error_message="No hash specified"
else
	hashvalue=params["hash"].first
	o = OCI8.new(username, password, '//oracle.informatik.haw-hamburg.de:1521/inf09')
	sql = "select * from kind where hashvalue=:1"
	cursor = o.parse(sql)
	cursor.bind_param(1,hashvalue)
        cursor.exec
	r = cursor.fetch()
	if(r.nil?) 
		error_message="No child found" 
	end
	cursor.close
	o.logoff
end



puts "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""
puts "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
puts "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
puts "<head><title>"+title+"</title></head>"
puts "<body>"
puts "<p>"+error_message+"</p>"
puts "</body>"
