require 'test/unit'
require File.dirname(__FILE__) + "/../lib/advanced_math.rb"



class SimpleMovingAverageTest < Test::Unit::TestCase
  # Verify the constructor raises an ArgumentError if the "range" is invalid
  def test_sma_initialize_nil
    assert_raise(ArgumentError) { AdvancedMath::SimpleMovingAverage.new(nil); }
  end

  # Verify the constructor raises an ArgumentError if the "range" is invalid
  def test_sma_initialize_negative
    assert_raise(ArgumentError) { AdvancedMath::SimpleMovingAverage.new(-1); }
  end

  # Verify the constructor raises an ArgumentError if the "range" is invalid
  def test_sma_initialize_zero
    assert_raise(ArgumentError) { AdvancedMath::SimpleMovingAverage.new(0); }
  end

  # Verify the constructor doesn't raise an exception if the value is positive
  def test_sma_initialize_positive
    (1...1000).each do |i|
      assert_nothing_raised(ArgumentError) { AdvancedMath::SimpleMovingAverage.new(i); }
    end
  end

  # Verify "add" raises an ArgumentError if the "value" is invalid
  def test_sma_add_nil
    assert_raise(ArgumentError) { AdvancedMath::SimpleMovingAverage.new(1).add(nil); }
  end

  # Verify SMA of 1, always returns the same value
  def test_sma_add_1
    sma = AdvancedMath::SimpleMovingAverage.new(1);
    (1...1000).each do |i|
      assert_equal(i, sma.add(i));
    end
  end

  # Verify SMA of 2, always returns the same value - 0.5
  def test_sma_add_2
    sma = AdvancedMath::SimpleMovingAverage.new(2);
    assert_equal(nil, sma.add(1));
    (2...1000).each do |i|
      assert_equal(i - 0.5, sma.add(i));
    end
  end

end
