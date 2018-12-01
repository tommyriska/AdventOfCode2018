package com.tommyriska;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Probably the most disgusting code ever
 *
 * @author tommy
 *
 */
public class Frequency2 {
	private static int sum = 0;
	private static String filename = String.format("%s/day1/input.txt", System.getProperty("user.dir"));
	private static Set<Integer> usedValues = new HashSet<>();
	private static boolean isFound = false;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		for (int i = 0; i < 200; i++) {
			if (!isFound) {
				try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
					String line;
					while ((line = br.readLine()) != null) {
						Integer lineNumber = Integer.parseInt(line);
						sum += lineNumber;
						if (checkDuplicates(sum)) {
							System.out.println(String.format("Found duplicate frequency %s in loop %s", sum, i));
							isFound = true;
							break;
						} else {
							usedValues.add(sum);
						}
					}
				}
			}
		}
	}

	private static boolean checkDuplicates(Integer i) {
		return usedValues.contains(i);
	}
}
