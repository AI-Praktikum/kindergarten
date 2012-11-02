#!/usr/bin/ruby
require 'rubygems'
require "model_mysql"
require "view"
require 'json'
require 'cgi'
require 'cgi/session'
require 'child'
cgi = CGI.new("html4")
params=cgi.params

#Enthällt den Kindergarten der in Login mitgegeben wurde

#params[] returns array -> to_s 
kindergarten =  params["state"].to_s
model = MysqlModel.new

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
  # perse string such as  "access_token=AAAsZCLvBgZDZD&expires=4992995"
  response.body.split("&").each do |param|
    access_token = param.split("=")[1] if param.split("=")[0] == "access_token"
  end
}
#
# 7. Request User Information Using Access Token
#
json=""
url = "/me?access_token=#{access_token}"
https.start {
  json = https.get(url).body
}
 
#
# 9. User Infomation Page
#
#name = JSON.parse(json)["name"]

facebook_id = JSON.parse(json)["id"]

children = []
#Get the Hashes for the logged in parent
hashes=model.getChildHashes(kindergarten, facebook_id)

#Get Wartelistenplätze for each hash(child) 
puts "######Hashes: "
if (not(hashes.empty?)) 
	hashes.each{|hash| 
		
		puts hash
		#getChilderen Returns list(Child) or String if Error or no Child found
		child_list = model.getChildren(kindergarten,hash)
		puts "Childrens for Hash"
		puts child_list.empty?
		puts child_list.to_s
		if not(child_list.is_a? String) 
			children + child_list
		end
		puts "Children: "
		puts children.empty?
		puts children.to_s	
		}
else 
	puts "no child found"
end
			puts "Output Children: "
			puts children.empty?
			puts children.to_s
			children.each{|x| puts x.to_s}
#view= View.new("Kindergarten Wartelisten&uuml;berblick")
#view.display(children)



