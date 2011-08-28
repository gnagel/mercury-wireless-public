require File.dirname(__FILE__) + "/sma.rb"

module AdvancedMath

  ###
  # Relative Strength Index (RSI) calculator
  # Created: 2011-08-28
  # Author:  G Nagel
  # Company: Mercury Wireless Software LLC
  # Source: http://en.wikipedia.org/wiki/Relative_Strength_Index#Cutler.27s_RSI
  # Source: http://www.aspenres.com/Documents/AspenGraphics4.0/Aspen_Graphics_4.htm#CutlersRSI.htm
  ###
  class RelativeStrengthIndex
    attr_accessor :values_up, :values_down, :range, :last_value
    
    ###
    # Initialize the members 
    ###
    def initialize(range)
      raise ArgumentError, "Range is nil" unless (range)
      raise ArgumentError, "Range must be > 1" unless range.to_i > 1

      @values_up = []
      @values_down = []
      @range = range.to_i()
      @last_value = nil
    end
    
    ###
    # Add a value to the list.
    # If the list is < @range, then return nil.
    # Otherwise compute the SMA and return the value.
    ###
    def add(value) 
      raise ArgumentError, "Value is nil" unless (value)
      puts "=================="
      puts "value = #{value}"
      
      if (nil == @last_value)
        @last_value = value.to_f()
        puts "Setting intial last_value = #{@last_value}"
        return nil
      end
      
      @values_up.shift() if (range() == @values_up.length())
      @values_down.shift() if (range() == @values_down.length())
      
      diff = value.to_f - @last_value
      @last_value = value.to_f()
      if (0 > diff)
        @values_up << 0
        @values_down << diff.abs() 
      elsif (0 < diff)
        @values_up << diff
        @values_down << 0
      else
        @values_up << 0
        @values_down << 0
      end
      puts "diff = #{diff}, values_up = #{@values_up.inspect()}, values_down = #{@values_down.inspect()}"
      
      if (@values_up.length() < range())
        puts "#{@values_up.length()} < #{range()}"
        return nil 
      end
      if (@values_up.length() > range())
        puts "#{@values_up.length()} > #{range()}"
        @values_up.shift()
        @values_down.shift()
      else
        puts "#{@values_up.length()} == #{range()}"
      end

      sum = {:up => 0, :down => 0, :i_up => 0, :i_down => 0}
      @values_up.each()   { |value| sum[:up]   = sum[:up] + value }
      @values_down.each() { |value| sum[:down] = sum[:down] + value }
      
      rs = (0 == sum[:down]) ? 100.0 : (sum[:up] / sum[:down])
      puts "rs = #{rs}"
      
      rsi = 100.0 - (100.0 / (1.0 + rs))
      puts "rsi = #{rsi}"
      return rsi
    end
    
    
    # ###
    # # Compute the SMA values for an array
    # # Return an array with the SMA values
    # ###
    # def add_array(values)
      # raise ArgumentError, "Value is nil" unless (values);
      # raise ArgumentError, "Value is not an array" unless values.kind_of?(Array);
#       
      # output = []
# 
      # # Calculate the sum of the array
      # values.each() { |value| output << add(value) }
#       
      # # Return the SMA array
      # return output
    # end
  end

  ###
  # RSI is just an alias to RelativeStrengthIndex
  ###  
  class RSI < RelativeStrengthIndex
  end
  
end
