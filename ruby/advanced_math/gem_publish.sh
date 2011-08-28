#!/bin/bash

rm *.gem;
rake gemspec:generate &&
gem build ./*.gemspec && 
gem push ./*.gem
rm *.gem;
ls *.gemspec | sed 's/.gemspec$//g' | xargs gem install 
