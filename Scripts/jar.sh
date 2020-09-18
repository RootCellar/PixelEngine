#!/bin/bash
#builds a lot faster than the other build.sh file. Useful for actively developing.

#make sure everything is built first
echo BUILDING...
./fastbuild.sh

echo
echo
echo BUILDING JAR...
echo

cd ..
cd build

echo Collecting class files...
echo

i=$(find -name "*.class")
echo $i
echo

echo Placing into jar...
echo

jar --create --file pixelengine.jar $i
