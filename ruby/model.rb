#!/usr/local/bin/ruby
require "db_config"
require "child"
require 'rubygems'
require 'oci8'

class Oci8Model
  	def getChildren(hashvalue)
		if(hashvalue.nil?)
			return "Kein Hash angegeben"
		else
			children=[]
			o = OCI8.new(username, password, '//oracle.informatik.haw-hamburg.de:1521/inf09')
			sql_child = "select k.vorname,k.nachname,w.ident,w.wartelistentyp,kw.datum_registrierung from (((Select * from kind where hashvalue=:1) k join registrierung kw on k.ident = kw.kind_id) join warteliste w on kw.warteliste_id=w.ident)"
			cursor_child = o.parse(sql_child)
			cursor_child.bind_param(1,hashvalue)
			cursor_child.exec
			child_hash = cursor_child.fetch_hash()
			while(not(child_hash.nil?))
				sql_rank = "select (count (*)) from registrierung where warteliste_id=:1 and datum_registrierung<= :2"
		 		cursor_rank = o.parse(sql_rank)
				cursor_rank.bind_param(1,child_hash["IDENT"])
				cursor_rank.bind_param(2,child_hash["DATUM_REGISTRIERUNG"])
				cursor_rank.exec
				child= Child.new(child_hash["VORNAME"],child_hash["NACHNAME"],child_hash["WARTELISTENTYP"],cursor_rank.fetch[0].to_i)
				children = children << child
				cursor_rank.close
				child_hash = cursor_child.fetch_hash()
			end
			cursor_child.close
			if(children.empty?) 
				return "Kein Kind f&uuml;r Hash gefunden"
			end
			o.logoff
			return children
		end	
  	end

	
end
