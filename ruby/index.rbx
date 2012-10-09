require 'cgi'
require "view"
require "model"

cgi = CGI.new("xhtml10")
params=cgi.params
model = Oci8Model.new
parameter= params.has_key?("hash") ? params["hash"].first : nil
result=model.getChildren(parameter)
view= View.new("Kindergarten Wartelisten&uuml;berblick")
view.display(result)

