#!/usr/bin/ruby

require 'rubygems'
require 'faker'
require "fileutils"

if(File.exists?("kindergarten.csv"))
	FileUtils.mv("kindergarten.csv","kindergarten_#{Time.now.to_i}.csv")
end
File.open("kindergarten.csv","w"){|f|
	ARGV[0].to_i.times{|x|
	f.puts((Faker::Name.last_name + "_kg").gsub("'",""))
	}
}

