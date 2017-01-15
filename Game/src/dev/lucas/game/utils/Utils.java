package dev.lucas.game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** 
 * <i><b>Utils</b></i>
 * <pre> public class Utils</pre>
 * <p>This class defines all the necessary methods and variables for a general utility class.</p>
 * **/
public class Utils {
	
	/**
	 * <i><b>loadFileAsString</b></i>
	 * <pre>	public static String loadFileAsString(String path)</pre>
	 * <p>This method grabs a file indicated from the parsed string and converts it into a large string. </p>
	 * @param
	 * @return String
	 * **/
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
	
	/**
	 * <i><b>parseInt</b></i>
	 * <pre>	public int parseInt(String num)</pre>
	 * <p>This method returns an int of the parsed String. If the String contains non-numeric characters it reterns 0.</p>
	 * @param
	 * @return int
	 * **/
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
