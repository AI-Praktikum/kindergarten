#!/usr/local/bin/ruby
require "cgi"
require "cgi/session"
 
client_id    = "429415353772776"  # set your facebook application client_id
redirect_uri = CGI.escape("http://kindergarten.hopto.org") # set your application url


cgi = CGI.new("xhtml10")
params=cgi.params
hash= params.has_key?("hash") ? params["hash"].first : nil
kindergarten= params.has_key?("kindergarten") ? params["kindergarten"].first : nil

# Session für Kindergarten
session = CGI::Session.new(cgi, "prefix" => "kindergarten.", "kindergarten" => kindergarten)



#
# 2. Redirect
#
url = "https://graph.facebook.com/oauth/authorize?" +
  "response_type=code&" +
  "client_id=#{client_id}&" +
  "redirect_uri=#{redirect_uri}"
print cgi.header({ 'status' => 'REDIRECT', 'Location' => url })
