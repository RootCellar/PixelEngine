#!/bin/bash

#move to base
cd ..

#move to source
cd src

#build list of files
rm find
find -name "*.java" > find.temp

#find -name "*.java" -exec javac {} +

#This is a slower method of building, but I (RootCellar) prefer it.
for i in $(find -name "*.java"); do
	javac -nowarn -d ../build/ $i
	echo $i
done
