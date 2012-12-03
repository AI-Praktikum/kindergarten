#!/usr/bin/ruby


File.open(ARGV[0],"r"){|file|
	file.each_line { |kindergarten|
			kindergarten=kindergarten.strip
			puts "set password for #{kindergarten} = password('#{kindergarten}');"
	}
}
