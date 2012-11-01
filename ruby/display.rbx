
#!/usr/bin/ruby
require 'rubygems'
require 'json'
require 'cgi'
require 'cgi/session'
cgi = CGI.new("html4")
params=cgi.params

state =  params["state"]

 
client_id     = "429415353772776"  # set your facebook application client_id
client_secret = "da5d3ce4978f795a470888cb00cd554b"  # set your facebook application client_secret
redirect_uri  = CGI.escape("http://kindergarten.hopto.org/display.rbx")  # set your application url
 
 
#
# 5. Access Token Request
#
code = CGI.new()["code"]
url = "/oauth/access_token?" +
  "client_id=#{client_id}&" +
  "client_secret=#{client_secret}&" +
  "redirect_uri=#{redirect_uri}&"+
  "code=#{code}"
 
# start ssl
require 'net/https'
https = Net::HTTP.new('graph.facebook.com',443)
https.use_ssl = true
https.verify_mode = OpenSSL::SSL::VERIFY_NONE
 
access_token=""
https.start {
  response = https.get(url)
	puts "Response: " + response.body
  # perse string such as  "access_token=AAAsZCLvBgZDZD&expires=4992995"
  response.body.split("&").each do |param|
    access_token = param.split("=")[1] if param.split("=")[0] == "access_token"
    puts access_token
  end
}
 puts access_token
#
# 7. Request User Information Using Access Token
#
puts "Requesting" 
json=""
url = "/me?access_token=#{access_token}"
https.start {
  json = https.get(url).body
}

puts json
 
#
# 9. User Infomation Page
#
name = JSON.parse(json)["name"]
puts name

name = JSON.parse(json)["id"]
puts id
