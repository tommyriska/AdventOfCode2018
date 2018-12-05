package com.tommyriska;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends AdventOfCode2018 {

	private static String filename = String.format("%s/day4.txt", System.getProperty("user.dir"));

	public static void main(String[] args) {
		List<String> lines = readFile(filename);
		partOneAndTwo(lines);
	}

	// Borrowed some logic from reddit.com/u/vypxl
	private static void partOneAndTwo(List<String> lines) {
		Collections.sort(lines);

		Map<Integer, int[]> guards = new HashMap<>();
		int current = 0;
		int start = 0;
		for (String s : lines) {

			int min = Integer.parseInt(s.substring(15, 17));
			boolean shift = s.contains("shift");
			boolean wake = s.contains("wake");
			boolean sleep = s.contains("sleep");

			int id;
			if (shift) {
				id = Integer.parseInt(s.split(" ")[3].substring(1));
				current = id;
				if (!guards.containsKey(id)) {
					guards.put(current, new int[60]);
					Arrays.fill(guards.get(current), 0);
				}
			}
			if (sleep) {
				start = min;
			}
			if (wake) {
				for (int i = start; i < min; i++) {
					guards.get(current)[i] += 1;
				}
			}
		}

		List<Integer> keys = new ArrayList<>(guards.keySet());
		int best = keys.get(0);
		int bestSum = 0;
		for (int id : keys) {
			int sum = Arrays.stream(guards.get(id)).filter(x -> x >= 1).sum();
			if (sum > bestSum) {
				bestSum = sum;
				best = id;
			}
		}

		int bestMinute = 0;
		int bestTime = 0;
		for (int i = 0; i < 60; i++) {
			if (guards.get(best)[i] > bestMinute) {
				bestMinute = guards.get(best)[i];
				bestTime = i;
			}
		}

		System.out.println(String.format("Guard most asleep: %s", best));
		System.out.println(String.format("Mintes asleep: %s", bestTime));
		System.out.println(String.format("Part one answer: %s", best * bestTime));
		System.out.println("");
		System.out.println("");

		best = keys.get(0);
		bestMinute = 0;
		for (int id : keys) {
			int minute = Arrays.stream(guards.get(id)).max().orElse(-1);
			if (minute > bestMinute) {
				bestMinute = minute;
				best = id;
			}
		}

		bestTime = 0;
		for (int i = 0; i < 60; i++) {
			if (guards.get(best)[i] == bestMinute) {
				bestTime = i;
			}
		}
		System.out.println("Guard id: " + best);
		System.out.println("Minute: " + bestTime);
		System.out.println("Solution to part 2: " + best * bestTime);
	}

	// Well this was for nothing.. Thanks Collections.sort...
	private static List<String> sortDates(List<String> lines) {
		List<String> sortedList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM");

		Pattern pattern = Pattern.compile("(\\d+-\\d+-\\d+ \\d+:\\d+)] (.*)");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Map<Date, String> seperated = new TreeMap<>();
		for (String line : lines) {
			Matcher m = pattern.matcher(line);
			while (m.find()) {
				Date date = null;
				try {
					date = dateFormat.parse(m.group(1));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				seperated.put(date, m.group(2));
			}
		}
		try {
			writeMapToFile(seperated);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Map.Entry<Date, String> entry : seperated.entrySet()) {
			sortedList.add(entry.getKey() + " - " + entry.getValue());
		}

		return sortedList;
	}
}
