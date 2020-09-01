#!/bin/bash

#move to base
cd ..

#move to source
cd src

#build list of files
rm find
find -name "*.java" > find

#find -name "*.java" -exec javac {} +
rm build.temp
for i in $(find -name "*.java"); do
	javac -nowarn -d ../build/ $i
	echo $i
done
