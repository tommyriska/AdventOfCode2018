package com.tommyriska;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends AdventOfCode2018 {

	private static String filename = String.format("%s/day3.txt", System.getProperty("user.dir"));
	private static int[][] size = new int[1000][1000];

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<String> lines = readFile(filename);
		System.out.println(String.format("Part 1 answer: %s", partOne(lines)));
		System.out.println(String.format("Part 2 answer: %s", partTwo(lines)));

	}

	private static int partOne(List<String> lines) throws FileNotFoundException, IOException {
		Pattern numberPattern = Pattern.compile("\\d+");
		for (String str : lines) {
			List<Integer> numbers = new ArrayList<>();
			Matcher numberMatcher = numberPattern.matcher(str);
			while (numberMatcher.find()) {
				numbers.add(Integer.parseInt(numberMatcher.group()));
			}
			for (int i = numbers.get(1); i < numbers.get(1) + numbers.get(3); i++) {
				for (int j = numbers.get(2); j < numbers.get(2) + numbers.get(4); j++) {
					size[i][j] += 1;
				}
			}
		}
		int dupl = 0;
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 1000; j++)
				if (size[i][j] > 1)
					dupl++;
		return dupl;
	}

	private static int partTwo(List<String> lines) {

		Pattern numberPattern = Pattern.compile("\\d+");
		for (String str : lines) {
			boolean flag = true;
			List<Integer> numbers = new ArrayList<>();
			Matcher numberMatcher = numberPattern.matcher(str);
			while (numberMatcher.find()) {
				numbers.add(Integer.parseInt(numberMatcher.group()));
			}
			for (int i = numbers.get(1); i < numbers.get(1) + numbers.get(3); i++) {
				for (int j = numbers.get(2); j < numbers.get(2) + numbers.get(4); j++) {
					if (size[i][j] > 1)
						flag = false;
				}
			}
			if (flag) {
				return numbers.get(0);
			}
		}
		return 0;
	}
}
