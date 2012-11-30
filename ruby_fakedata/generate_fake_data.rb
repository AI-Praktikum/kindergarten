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
	name=Faker::Name.last_name
	kindergartens[name]=kindergartens[name]+1
	f.puts(("kg_" + name + kindergartens[name].to_s).gsub("'",""))
	}
}

