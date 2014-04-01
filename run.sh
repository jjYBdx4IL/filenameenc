#!/bin/bash

set -Eex

rm -rf a
mkdir a
javac -encoding UTF-8 Test.java
LC_ALL=de_DE.ISO8859-15@euro java Test create
LC_ALL=de_DE.UTF-8 java Test check
ls -l a
rm -rf a

