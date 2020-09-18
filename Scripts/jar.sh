#!/bin/bash
#builds a lot faster than the other build.sh file. Useful for actively developing.

#make sure everything is build first
echo BUILDING...
./fastbuild.sh

echo
echo
echo BUILDING JAR...

cd ..
cd build
i=$(find -name "*.class")
echo $i
jar --create --file pixelengine.jar $i
