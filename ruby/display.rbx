
#!/usr/bin/ruby

require 'cgi'
require 'cgi/session'
cgi = CGI.new("html4")
params=cgi.params


puts "kindergarten:"
puts params["state"]
puts params["code"]
puts "end"