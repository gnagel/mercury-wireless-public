require File.dirname(__FILE__) + "/sma.rb"

module AdvancedMath
  ###
  # Relative Strength Index (RSI) calculator
  # Created: 2011-08-28
  # Author:  G Nagel
  # Company: Mercury Wireless Software LLC
  #
  # Source: http://www.tradingmarkets.com/.site/stocks/commentary/editorial/2-Period-RSI.cfm
  #
  # Source: http://en.wikipedia.org/wiki/Relative_Strength_Index#Cutler.27s_RSI
  # Source: http://www.aspenres.com/Documents/AspenGraphics4.0/Aspen_Graphics_4.htm#CutlersRSI.htm
  # Source: http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:relative_strength_index_rsi
  ###
  class RelativeStrengthIndex
    attr_accessor :values_up, :values_down, :range, :last_value, :count
    attr_accessor :verbose
    ###
    # Initialize the members
    ###
    def initialize(range)
      raise ArgumentError, "Range is nil" unless (range)
      raise ArgumentError, "Range must be > 1" unless range.to_i > 1

      @verbose = false
      @values_up = []
      @values_down = []
      @range = range.to_i()
      @last_value = nil
      @count = 0
    end

    ###
    # Add a value to the list.
    # If the list is < @range, then return nil.
    # Otherwise compute the RSI and return the value.
    ###
    def add(value)
      raise ArgumentError, "Value is nil" unless (value)
      puts "==================" if verbose()
      puts "value = #{value}" if verbose()

      if (nil == @last_value)
        @last_value = value.to_f()
        puts "Setting intial last_value = #{@last_value}" if verbose()
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
      puts "diff = #{diff}, values_up = #{@values_up.inspect()}, values_down = #{@values_down.inspect()}" if verbose()

      if (@values_up.length() < range())
        puts "#{@values_up.length()} < #{range()}" if verbose()
        return nil
      end
      if (@values_up.length() > range())
        puts "#{@values_up.length()} > #{range()}" if verbose()
      @values_up.shift()
      @values_down.shift()
      else
        puts "#{@values_up.length()} == #{range()}" if verbose()
      end

      sum = {:up => 0, :down => 0, :i_up => 0, :i_down => 0}
      
      @values_up.slice(0, range()-1).each()   { |value| sum[:up]   = sum[:up] + value }
      @values_down.slice(0, range()-1).each() { |value| sum[:down] = sum[:down] + value }
      if (count() == 0)
        sum[:up]   = sum[:up] + @values_up[range()-1]
        sum[:down] = sum[:down] + @values_down[range()-1]
      else
        sum[:up] = sum[:up]/(range()-1) + @values_up[range()-1]
        sum[:down] = sum[:down]/(range()-1) + @values_down[range()-1]
      end
      @count = count() + 1

      rs = (0 == sum[:down]) ? 100.0 : (sum[:up] / sum[:down])
      puts "rs = #{rs}" if verbose()

      rsi = 100.0 - (100.0 / (1.0 + rs))
      puts "rsi = #{rsi}" if verbose()
      return rsi
    end

    ###
    # Compute the RSI values for an array
    # Return an array with the RSI values
    ###
    def add_array(values)
      raise ArgumentError, "Value is nil" unless (values);
      raise ArgumentError, "Value is not an array" unless values.kind_of?(Array);

      output = []

      # Calculate the sum of the array
      values.each() { |value| output << add(value) }

      # Return the RSI array
      return output
    end
  end

  ###
  # RSI is just an alias to RelativeStrengthIndex
  ###
  class RSI < RelativeStrengthIndex
    def initialize(range)
      super(range)
    end
  end
end
