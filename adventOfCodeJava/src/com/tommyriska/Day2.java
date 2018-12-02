package com.tommyriska;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day2 extends AdventOfCode2018 {

	private static String filename = String.format("%s/day2.txt", System.getProperty("user.dir"));

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(partOne(filename));
		System.out.println(partTwo(filename));
	}

	private static String partOne(String filename) throws FileNotFoundException, IOException {

		int occursTwoTimes = 0;
		int occursThreeTimes = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				Map<Character, Integer> frequencies = new HashMap<>();
				for (char c : line.toCharArray()) {
					frequencies.merge(c, 1, (a, b) -> a + b);
				}
				Set<Integer> uniqueFrequencies = new HashSet<>(frequencies.values());
				if (uniqueFrequencies.contains(2))
					occursTwoTimes++;
				if (uniqueFrequencies.contains(3))
					occursThreeTimes++;
			}
		}

		Integer checksum = occursThreeTimes * occursTwoTimes;

		return String.format("Occurs two times: %s\n" + "Occurs three times: %s\n" + "Checksum: %s\n", occursTwoTimes,
				occursThreeTimes, String.valueOf(checksum));
	}

	private static String partTwo(String filename) throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			List<String> lines = br.lines().collect(Collectors.toList());
			for (int i = 0; i < lines.size(); i++) {
				String lineOne = lines.get(i);
				for (int j = i + 1; j < lines.size(); j++) {
					String lineTwo = lines.get(j);
					int differentCharacters = 0;
					StringBuilder equalCharacters = new StringBuilder();
					for (int k = 0; k < lineOne.length(); k++) {
						if (lineOne.charAt(k) != lineTwo.charAt(k)) {
							if (++differentCharacters > 1) {
								break;
							}
						} else {
							equalCharacters.append(lineOne.charAt(k));
						}
					}
					if (differentCharacters == 1) {
						return equalCharacters.toString();
					}
				}
			}
		}

		return "";
	}
}
