# -*- coding: utf-8 -*-
require File.dirname(__FILE__)+'/../helper'
require 'rack'
require 'sinatra/reloader' if development?

R = 6378137

def app_root
  "#{env['rack.url_scheme']}://#{env['HTTP_HOST']}#{env['SCRIPT_NAME']}"
end

def speed(a, b)
  a_b = distance(a, b)
  a_b/(a.time-b.time)*60*60/1000 # km/h
end

def distance(a, b)
  d_lon = (a.lon - b.lon).abs*(Math::PI/180) # 経度
  d_lat = (a.lat - b.lat).abs*(Math::PI/180) # 緯度
  d_y = R * d_lon * Math.cos(a.lat*(Math::PI/180))
  d_x = R * d_lat
  Math.sqrt(d_x*d_x + d_y*d_y)  
end

def direction(a, b)
  dx = (a.lon-b.lon).abs
  dy = (a.lat-b.lat).abs
  if dx*0.7 > dy
    if a.lon > b.lon
      return '東'
    else
      return '西'
    end
  elsif dx*1.3 < dy
    if a.lat > b.lat
      return '北'
    else
      return '南'
    end
  else
    if a.lon > b.lon
      if a.lat > b.lat
        return '北東'
      else
        return '北西'
      end
    else
      if a.lat > b.lat
        return '南東'
      else
        return '南西'
      end
    end
  end
end
