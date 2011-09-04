require 'rubygems'
require 'bundler/setup'
require 'bson'
require 'mongoid'
gem 'mongoid','>=2.2.0'
require 'yaml'
require File.dirname(__FILE__)+'/models/location'

begin
  @@conf = YAML::load open(File.dirname(__FILE__)+'/config.yaml').read
rescue => e
  STDERR.puts 'config.yaml load error!'
  STDERR.puts e
end

Mongoid.configure{|conf|
  conf.master = Mongo::Connection.new(@@conf['mongo_server'], @@conf['mongo_port']).db(@@conf['mongo_dbname'])
}
