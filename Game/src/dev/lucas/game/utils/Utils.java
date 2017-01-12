package dev.lucas.game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	
	public static String loadFileAsString(String path) {
		// turns the content of a file into a large string that goes to a new line when the files does.
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line+"\n");
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String num) {
		// converts a string into a number and catches if the string given is not all numbers.
		try{
			return Integer.parseInt(num);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}	
}
