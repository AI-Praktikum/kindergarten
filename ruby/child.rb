#!/usr/local/bin/ruby
class Child
  def initialize(name,surname,queue,rank)
    @name,@surname,@queue,@rank=name,surname,queue,rank
  end
  
  def name
    @name
  end
  def surname
    @surname
  end
  def queue
    @queue
  end
  def rank
    @rank
  end

  def to_s
	"Name: #@name #@surname Queue: #@queue Rank: #@rank"
  end
end
