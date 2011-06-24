# Simple Moving Average (SMA) calculator
# Created: 2011-06-24
# Author:  G Nagel
# Company: Mercury Wireless Software LLC

module Math

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
      # add the value to the end of the array.
      @values.push(value);
      
      # Calculate the sum of the array
      @sum += value.to_f;

      # Is the array less than the range?
      length = @values.length;
      if (length < @range) 
        return nil;
      end

      # Is the array larger than the range?
      if (length > @range)
        @sum -= @values.shift.to_f();
      end
      
      # Compute the average
      return @sum.to_f / @range.to_f;
    end
  end
  
end
