#!/bin/bash
#Used to find todo statements and such and collect them in a file

echo
echo COLLECTING INFO...

git status

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
(du -ch) | sort -h -r > info/size.temp

#find .md files
(find -name "*.md") > info/md.temp

#find files in src/
ls -lsthR src > info/src.temp




