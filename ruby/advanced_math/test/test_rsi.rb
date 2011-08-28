require 'test/unit'
require File.dirname(__FILE__) + "/../lib/advanced_math.rb"

module AdvancedMath
  class RelativeStrengthIndexTest < Test::Unit::TestCase
    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_nil
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(nil) }
    end

    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_negative
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(-1) }
      (-1...-1000).each do |i|
        assert_raise(ArgumentError) { RelativeStrengthIndex.new(i) }
      end
    end

    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_zero
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(0) }
    end

    # Verify the constructor raises an ArgumentError if the "range" is invalid
    def test_sma_initialize_one
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(1) }
    end

    # Verify the constructor doesn't raise an exception if the value is positive
    def test_sma_initialize_positive
      (2...1000).each do |i|
        assert_nothing_raised(ArgumentError) { RelativeStrengthIndex.new(i) }
      end
    end

    # Verify "add" raises an ArgumentError if the "value" is invalid
    def test_sma_add_nil
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(1).add(nil) }
    end

    # # Verify SMA of 1, always returns the same value
    # def test_sma_add_0
    # sma = RelativeStrengthIndex.new(2)
    # assert_nil(value = sma.add(0))
    # assert_nil(value = sma.add(0))
    # assert_not_nil(value = sma.add(0))
    # assert_equal(50, value)
    # (0...1000).each { |i| assert_equal(50, sma.add(0)) }
    # end

    def test_sma_add_range
      sma = RelativeStrengthIndex.new(2)
      (0...1000).each do |i|
        value = sma.add(i)
        if (i < 2)
          assert_nil(value, "#{i}")
        else
          assert_not_nil(value, "#{i}")
          assert_equal(99, sma.add(i).to_i(), "#{i}")
        end
      end
    end

    # Verify SMA of 2, always returns the same value - 0.5
    def test_sma_add_2
      sma = RelativeStrengthIndex.new(2)
      assert_equal(nil, sma.add(0))
      assert_equal(nil, sma.add(0))
      assert_equal(99, sma.add(0).to_i())
      (1...1000).each do |i|
        value = sma.add(i)
        assert_equal(99, sma.add(i).to_i(), "#{i}")
      end

    # (0..1000).to_a().each() do |i|
    # sma = RelativeStrengthIndex.new(2)
    # value = sma.add(i)
    # assert_nil(value, i)
    #
    # value = sma.add(i+1)
    # assert_not_nil(value = sma.add(i+1), i)
    # assert_equal(99, sma.add(i).to_i(), "#{i}")
    # end
    end

    def test_sma_array
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(2).add_array(nil) }
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(2).add_array("") }
      assert_raise(ArgumentError) { RelativeStrengthIndex.new(2).add_array(1) }
      assert_nothing_raised(ArgumentError) { RelativeStrengthIndex.new(2).add_array(Array.new) }
      assert_not_nil( RelativeStrengthIndex.new(2).add_array(Array.new) )
      assert_not_nil( RelativeStrengthIndex.new(2).add_array([]) )
      assert_equal( 0, RelativeStrengthIndex.new(2).add_array([]).length() )
      assert_equal( 1, RelativeStrengthIndex.new(2).add_array([1]).length() )
      assert_equal( 2, RelativeStrengthIndex.new(2).add_array((1..2).to_a).length() )
      assert_equal( 1000, RelativeStrengthIndex.new(2).add_array((1..1000).to_a).length() )

      values = RelativeStrengthIndex.new(2).add_array((0..1000).to_a)
      assert_nil(values[0])
      1.upto(values.length() -1) do |i|
        assert_not_nil(values[i])
        assert_equal(i - 0.5, values[i])
      end

      values = RelativeStrengthIndex.new(14).add_array((0..14000).to_a)
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
end