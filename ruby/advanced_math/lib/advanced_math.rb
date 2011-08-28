
module AdvancedMath
  VERSION = '0.0.5'


  # Simple Moving Average (SMA) calculator
  # Created: 2011-06-24
  # Author:  G Nagel
  # Company: Mercury Wireless Software LLC
  class SimpleMovingAverage
    ###
    # Initialize the members: 
    # range: 
    #   number of values to average
    # sum: 
    #   current sum of all values in the array
    # values: 
    #   array of values used as temporary storage
    ###
    def initialize(range)
      raise ArgumentError, "Range is nil" unless (range);
      raise ArgumentError, "Range must be >= 1" unless range.to_i >= 1;
      @range = range.to_i;
      @sum = 0;
      @values = Array.new();
    end
    
    ###
    # Add a value to the list.
    # If the list is < @range, then return nil.
    # Otherwise compute the SMA and return the value.
    ###
    def add(value) 
      raise ArgumentError, "Value is nil" unless (value);
      
      # add the value to the end of the array.
      @values.push(value);
      
      # Calculate the sum of the array
      @sum += value.to_f;

      # Is the array less than the range?
      return nil if (@values.length() < @range)

      # Is the array larger than the range?
      @sum -= @values.shift.to_f() if (@values.length() > @range)
      
      # Compute the average
      return @sum.to_f / @range.to_f;
    end
  end

  ###
  # SMA is just an alias to SimpleMovingAverage
  ###  
  class SMA < SimpleMovingAverage
  end
  
end
