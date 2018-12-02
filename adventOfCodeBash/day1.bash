#!/bin/bash
# Part 2 was written by reddit.com/u/vypxl as I had to take a peek at other peoples solutions for this one.

# Part 1
echo "Solution for part 1:"
file="day1.txt"
sum=0
while read -r line
do
	sum=$((sum + line))
done < $file
printf 'Sum: %s\n' "$sum"

# Part 2 - Put all new frequencies into an associative array until a duplicate is encountered
freq=0
iters=0
declare -A seen

# Loop until solution is found
while true; do
	# Read every line
	while read f; do
		# Update current frequency
		freq=$(($freq+$f))

		# Check if already encountered
		if [ ${seen["$freq"]} ]; then
			# Print solution and exit
			echo "Solution for part 2:"
			echo $freq
			echo "Took $iters iterations."
			exit 0
		else
			# Add frequency to seen
			seen["$freq"]=1
		fi
		iters=$(($iters+1))
	done < day1.txt
done
