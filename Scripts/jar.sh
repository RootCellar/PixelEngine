#!/bin/bash
#puts the .class files into a jar. BasicGame specified, meaning that attempting to run the jar runs the example

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

jar --create -e BasicGame --file pixelengine.jar $i
