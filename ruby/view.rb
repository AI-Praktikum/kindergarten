#!/usr/local/bin/ruby
require "child"

class View
	def initialize(title)
		@title=title
	end

	def displayError(error_message)
		puts "<p class=\"error\">" + error_message + "</p>"
	end

	def displayChildren(children)
		puts "<h1>Die Wartelistenplatzierungen Ihres Kindes</h1>"
		children.each{|x|
			puts "<h2>Warteliste:</h2>"
			puts "<p class=\"queue\">" + x.queue + "</p>"
			puts "<h3>Name:</h3>"
			puts "<p class=\"fullname\">" + x.surname + ", " + x.name + "</p>"
			puts "<h3>Platzierung:</h3>"		
			puts "<p class=\"rank\">" + x.rank.to_s + "</p>"
		}
	end

	def displayHead
		puts "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""
		puts "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
		puts "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
		puts "<head><title>"+@title+"</title><link rel=\"stylesheet\" type=\"text/css\" href=\"/style.css\" /></head>"
		puts "<body>"
	end	

	def displayBottom
		puts "</body>"
		puts "</html>"
	end

	def display(something)
		displayHead
		if(something.is_a?(String))
			displayError(something)
		elsif(something.is_a?(Array) and not(something.empty?) and something.all?{|x|x.is_a?(Child)})
			displayChildren(something)
		else
			puts something.to_s
			something.each{|x| puts x.to_s}
			#displayError("Ein interner Fehler ist aufgetreten")
		end
		displayBottom
	end
end
