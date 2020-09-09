#!/bin/bash
#builds slower, but has more potential to do more while building/with the built source files

echo
echo BUILDING...

#move to base
cd ..

#move to source
cd src

#build list of files
rm find
find -name "*.java" > ../info/find.temp

#This is a slower method of building
for i in $(find -name "*.java"); do
	echo Building $i...
	javac -d ../build/ $i
	echo Built $i
done
