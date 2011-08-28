#!/usr/bin/env perl
use Math::Business::RSI;

my $rsi = new Math::Business::RSI(shift @ARGV);
$rsi->set_cutler;
$rsi->insert( $_ ) for @ARGV;
$rsi = int($rsi->query);
print "$rsi\n";
