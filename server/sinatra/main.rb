#!/usr/bin/env ruby
# -*- coding: utf-8 -*-

get '/' do
  @mes = 'sokudo server'
end

post '/location' do
  @lat = params['lat'].to_f
  @lon = params['lon'].to_f
  begin
    @loc = Location.new(:lat => @lat, :lon => @lon, :time => Time.now.to_i)
    @loc.save
    @mes = @loc.to_hash.to_json
  rescue => e
    STDERR.puts e
  end
end

