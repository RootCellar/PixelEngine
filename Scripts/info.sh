#!/bin/bash
#Used to find todo statements and such and collect them in a file

#find todo statements
cd ..
cd src
grep -r -i -n "todo" > ../info/todo.temp

#find single-line comments
grep -r -i -n "//" > ../info/comment.temp

#count lines
(find -name "*.java" -exec wc -l {} +) | sort -n -r > ../info/lines.temp

#find file sizes
cd ..
du -ch > info/size.temp

#find files in src/
ls -lsthR src > info/src.temp
