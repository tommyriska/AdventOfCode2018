package com.tommyriska;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author tommy
 *
 */
public class Frequency1 {
	private static int sum = 0;
	private static String filename = String.format("%s/day1/input.txt", System.getProperty("user.dir"));

	public static void main(String[] args) throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (Integer.parseInt(line) > 0) {
					sum += Integer.parseInt(line);
				} else if (Integer.parseInt(line) < 0) {
					sum += Integer.parseInt(line);
				} else {
					System.out.println("Could not process: " + line);
				}
			}
		}

		System.out.println(String.format("Total sum was %s", sum));
	}
}
