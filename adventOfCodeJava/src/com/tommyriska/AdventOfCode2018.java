package com.tommyriska;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdventOfCode2018 {

	public static List<String> readFile(String filename) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static void writeMapToFile(Map<Date, String> map) throws IOException {
		File out = new File("test.txt");
		FileOutputStream fos = new FileOutputStream(out);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (Map.Entry<Date, String> entry : map.entrySet()) {
			bw.write(entry.getKey() + entry.getValue());
			bw.newLine();
		}
		bw.close();
	}
}
