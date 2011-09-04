#!/usr/bin/env ruby
# -*- coding: utf-8 -*-

before do
  @title = 'sokudo'
end

get '/' do
  locs = Location.desc(:time)
  @locs_count = locs.count
  @locs = locs.limit(40)
  haml :location
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
