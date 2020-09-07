#!/bin/bash
#builds a lot faster than the other build.sh file. Useful for actively developing.

#make sure everything is build first
./fastbuild.sh

cd ..
cd build
i=$(find -name "*.class") 
jar --create --file pixelengine.jar $i
