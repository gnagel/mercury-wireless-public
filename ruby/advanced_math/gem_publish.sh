#!/bin/bash

rm *.gem;
rake gemspec:generate &&
gem build && 
gem push
