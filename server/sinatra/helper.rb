require File.dirname(__FILE__)+'/../helper'
require 'rack'
require 'sinatra/reloader' if development?

def app_root
  "#{env['rack.url_scheme']}://#{env['HTTP_HOST']}#{env['SCRIPT_NAME']}"
end
