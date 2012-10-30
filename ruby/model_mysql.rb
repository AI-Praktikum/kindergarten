require "db_config"
require "child"
require 'rubygems'
require 'mysql'

class MysqlModel
  	def getChildren(kindergarten,hashvalue)
                if(kindergarten.nil?)
                        return "Kein Kindergarten angegeben"
		elsif(hashvalue.nil?)
			return "Kein Hash angegeben"
		else
                  children=[]
                  begin	
			con = Mysql.new(host,username, password, kindergarten)
			sql_child = "select k.vorname,k.nachname,w.ident,w.wartelistentyp,kw.datum_registrierung from (((Select * from kind where hashvalue=?) k join registrierung kw on k.ident = kw.kind_id) join warteliste w on kw.warteliste_id=w.ident)"
			pst_child = con.prepare(sql_child)
			pst_child.execute(hashvalue)
                        pst_child.each{|child_list|
                            sql_rank = "select (count(*)) from registrierung where warteliste_id=? and datum_registrierung<=?"
                            pst_rank = con.prepare(sql_rank)
			    pst_rank.execute(child_list[2],child_list[4])
			    child= Child.new(child_list[0],child_list[1],child_list[3],pst_rank.fetch[0].to_i)
                            children = children << child
			    pst_rank.close
                        }
                  rescue Mysql::Error => e
                        return "Sorry, ein interner Fehler ist passiert."
                  ensure
			pst_child.close if pst_child
                        con.close if con
                  end
		  if(children.empty?) 
			return "Kein Kind f&uuml;r Hash gefunden"
		  end
		  return children
		end	
  	end
end
