#!/bin/bash
#Used to find todo statements and such and collect them in a file

#find todo statements
cd ..
cd src
grep -r -i -n "todo" > todo.temp

#find single-line comments
grep -r -i -n "//" > comment.temp

#count lines
(find -name "*.java" -exec wc -l {} +) | sort -n -r > lines.temp

#find file sizes
cd ..
du -ch > size.temp
