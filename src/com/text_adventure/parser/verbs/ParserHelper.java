package com.text_adventure.parser.verbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility methods to help the parser and the verbs do their jobs
 * @author mcook
 */
public class ParserHelper {
	/**
	 * The buffered input stream we will be using for everything
	 */
	private static BufferedReader input = null;
	
	static {
		input = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Get a line of input from the user, optionally displaying the prompt
	 * give, optionally allowing blank answers
	 * @param prompt The prompt to display (a ": " is added to the end)
	 * @param allowBlank If we should return an empty line
	 * @return The string the user gave us
	 * @throws IOException If something weird happens
	 */
	public static String readLine(String prompt, boolean allowBlank) throws IOException {
		String line = null;
		
		while ((line == null) || (line.length() == 0)) {
			if (prompt != null) {
				System.out.print(prompt);
				System.out.print(": ");
			}
			
			line = input.readLine().trim();
			
			if (allowBlank && (line.length() == 0)) {
				break;	// This is OK if allow blank is set
			}
		}
		
		return line;
	}
	
	/**
	 * Get a line of input from the user, optionally allowing blank answers
	 * @param allowBlank If we should return an empty line
	 * @return The string the user gave us
	 * @throws IOException If something weird happens
	 */
	public static String readLine(boolean allowBlank) throws IOException {
		return readLine(null, allowBlank);
	}
}
