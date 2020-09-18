#!/bin/bash
#Put a game together in a jar

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

jar --create -e $1 --file $1.jar $i
