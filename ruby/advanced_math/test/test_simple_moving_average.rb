require 'test/unit'
require File.dirname(__FILE__) + "/../lib/advanced_math.rb"

module AdvancedMath
  class SimpleMovingAverageTest < Test::Unit::TestCase
    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_nil
      assert_raise(ArgumentError) { SimpleMovingAverage.new(nil) }
    end

    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_negative
      assert_raise(ArgumentError) { SimpleMovingAverage.new(-1) }
      (-1...-1000).each do |i|
        assert_raise(ArgumentError) { SimpleMovingAverage.new(i) }
      end
    end

    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_zero
      assert_raise(ArgumentError) { SimpleMovingAverage.new(0) }
    end

    # Verify the constructor doesn't raise an exception if the value is positive
    def test_sma_initialize_positive
      (1...1000).each do |i|
        assert_nothing_raised(ArgumentError) { SimpleMovingAverage.new(i) }
      end
    end

    # Verify "add" raises an ArgumentError if the "value" is invalid
    def test_sma_add_nil
      assert_raise(ArgumentError) { SimpleMovingAverage.new(1).add(nil) }
    end

    # Verify SMA of 1, always returns the same value
    def test_sma_add_1
      sma = SimpleMovingAverage.new(1)
      (1...1000).each do |i|
        assert_equal(i, sma.add(i))
      end
    end

    # Verify SMA of 2, always returns the same value - 0.5
    def test_sma_add_2
      sma = SimpleMovingAverage.new(2)
      assert_equal(nil, sma.add(1))
      (2...1000).each do |i|
        assert_equal(i - 0.5, sma.add(i))
      end
      
      (0..1000).to_a().each() do |i|
        sma = SimpleMovingAverage.new(2)
        assert_nil(value = sma.add(i))
        assert_not_nil(value = sma.add(i+1))
        assert_equal(i+0.5, value)
      end
    end
    
    def test_sma_array
      assert_raise(ArgumentError) { SimpleMovingAverage.new(2).add_array(nil) }
      assert_raise(ArgumentError) { SimpleMovingAverage.new(2).add_array("") }
      assert_raise(ArgumentError) { SimpleMovingAverage.new(2).add_array(1) }
      assert_nothing_raised(ArgumentError) { SimpleMovingAverage.new(2).add_array(Array.new) }
      assert_not_nil( SimpleMovingAverage.new(2).add_array(Array.new) )
      assert_not_nil( SimpleMovingAverage.new(2).add_array([]) )
      assert_equal( 0, SimpleMovingAverage.new(2).add_array([]).length() )
      assert_equal( 1, SimpleMovingAverage.new(2).add_array([1]).length() )
      assert_equal( 2, SimpleMovingAverage.new(2).add_array((1..2).to_a).length() )
      assert_equal( 1000, SimpleMovingAverage.new(2).add_array((1..1000).to_a).length() )
      
      values = SimpleMovingAverage.new(2).add_array((0..1000).to_a)
      assert_nil(values[0])
      1.upto(values.length() -1) do |i|
        assert_not_nil(values[i])
        assert_equal(i - 0.5, values[i])
      end
      
      values = SimpleMovingAverage.new(14).add_array((0..14000).to_a)
      0.upto(12) { |i| assert_nil(values[i], "#{i}") }

      13.upto(values.length() -1) do |i|
        assert_not_nil(values[i], "#{i}")

        sum = 0.0
        Range.new(i-13, i).each() { |value| sum = sum + value.to_f}
        sum = sum / 14
        
        assert_equal(sum, values[i], "#{i}")
      end
    end
  end
  
  
  class SMATest < SimpleMovingAverageTest
  end 
end