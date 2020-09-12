#!/bin/bash
#Used to find todo statements and such and collect them in a file

echo
echo COLLECTING INFO...

git status

#Move to base directory
cd ..
mkdir info #ensure folder exists

#Things involving the source code start here
cd src

#find todo statements
grep -r -i -n "todo" > ../info/todo.temp

#find single-line comments
grep -r -i -n "//" > ../info/comment.temp

#count source code lines
(find -name "*.java" -exec wc -l {} +) | sort -n -r > ../info/lines.temp

cd ..
#No longer working in source code folder

#count text lines
(find -name "*.md" -exec wc -l {} +) | sort -n -r > info/mdlines.temp

#find file sizes
(du -ch) | sort -h -r > info/size.temp

#find .md files
(find -name "*.md") > info/md.temp

#find files in src/
ls -lsthR src > info/src.temp








