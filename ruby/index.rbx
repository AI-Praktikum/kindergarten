#!/usr/local/bin/ruby
require 'cgi'
require "view"
require "model_mysql"

cgi = CGI.new("xhtml10")
params=cgi.params
model = MysqlModel.new
hash= params.has_key?("hash") ? params["hash"].first : nil
kindergarten= params.has_key?("kindergarten") ? params["kindergarten"].first : nil
result=model.getChildren(kindergarten,hash)
view= View.new("Kindergarten Wartelisten&uuml;berblick" + hash.class.to_s)
view.display(result)

