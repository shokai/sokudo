class Location
  include Mongoid::Document
  field :lat, :type => Float
  field :lon, :type => Float
  field :time, :type => Integer
  field :user_id, :type => String
  def to_hash
    {
      :lat => lat,
      :lon => lon,
      :time => time
    }
  end
end
