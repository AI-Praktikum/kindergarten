#!/usr/bin/ruby

require 'rubygems'
require 'faker'
require "fileutils"

if(File.exists?("kindergarten.csv"))
	FileUtils.mv("kindergarten.csv","kindergarten_#{Time.now.to_i}.csv")
end

kindergartens=Hash.new {0}
File.open("kindergarten.csv","w"){|f|
	ARGV[0].to_i.times{|x|
	begin 
		name=Faker::Name.last_name
	end while (name.size + 3 + (kindergartens[name]+1).to_s.size) > 16
	kindergartens[name]=kindergartens[name]+1
	f.puts(("kg_" + name + kindergartens[name].to_s).gsub("'",""))
	}
}

