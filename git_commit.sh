#!/bin/bash

git add -A;
git commit -m "$1";
git push;
git pull;
git fetch;
