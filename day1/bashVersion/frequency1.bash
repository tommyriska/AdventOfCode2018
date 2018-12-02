#!/bin/bash
file="../input.txt"
sum=0
while read -r line
do
	sum=$((sum + line))
done < $file

printf 'Sum: %s\n'  "$sum"
