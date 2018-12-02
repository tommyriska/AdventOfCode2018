package com.tommyriska;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day1 extends AdventOfCode2018 {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		System.out.println(partOne());

		System.out.println(partTwo());

	}

	private static String partOne() {
		int sum = 0;
		String filename = String.format("%s/day1.txt", System.getProperty("user.dir"));

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				sum += Integer.parseInt(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.format("Total sum: %s", sum);
	}

	public static String partTwo() {
		int sum = 0;
		String filename = String.format("%s/day1.txt", System.getProperty("user.dir"));
		Set<Integer> usedValues = new HashSet<>();
		boolean isFound = false;

		for (int i = 0; i < 200; i++) {
			if (!isFound) {
				try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
					String line;
					while ((line = br.readLine()) != null) {
						Integer lineNumber = Integer.parseInt(line);
						sum += lineNumber;
						if (usedValues.contains(sum)) {
							isFound = true;
							return String.format("Found duplicate frequency %s in loop %s", sum, i);
						} else {
							usedValues.add(sum);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "Duplicate not found";
	}
}
