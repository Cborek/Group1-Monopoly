package edu.neumont.csc110.a.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleUI {

	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Generates a console-based menu using the Strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options
	 *            - Strings representing the menu options
	 * @param withQuit
	 *            - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 * @throws IOException
	 */
	public static int promptForMenuSelection(String[] options, boolean withQuit) throws IOException {

		System.out.println("What menu option do you want to do?");
		int min = 1;

		for (int i = 0; i < options.length; i++) {
			System.out.println((i+1) + " - " +options[i]);
		}
		if (withQuit == true) {
			System.out.println("0 - Quit");
			min = 0;
		}
		boolean isGoodInput = true;
		String input;
		int inputInt = 0;
		if (options == null || options.length==0 && !withQuit) {
			IllegalArgumentException myException = new IllegalArgumentException();
			throw myException;
		}
		
		do {
			input = in.readLine();
			isGoodInput = true;
			try {
				inputInt = Integer.parseInt(input);
			} catch (NumberFormatException myException) {
				System.out.println("Sorry, you must enter a number value. Try again.");
				isGoodInput = false;
			}

			if (isGoodInput == true) {
				if (inputInt < min || inputInt > options.length) {
					isGoodInput = false;
					System.out.println("User input is not valid.");
				}
			}
		} while (!isGoodInput);
		return inputInt;

	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses
	 * that will equate to a boolean value. The trueString represents the case
	 * insensitive response that will equate to true. The falseString acts
	 * similarly, but for a false boolean value. Example: Assume this method is
	 * called with a trueString argument of "yes" and a falseString argument of
	 * "no". If the enters "YES", the method returns true. If the user enters
	 * "no", the method returns false. All other inputs are considered invalid,
	 * the user will be informed, and the prompt will repeat.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param trueString
	 *            - the case insensitive value that will evaluate to true
	 * @param falseString
	 *            - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 * @throws IOException
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString) throws IOException {
		System.out.println(prompt);
		boolean isBoolean = true;
		boolean isGoodInput = true;
		if (prompt == null || trueString == null || falseString == null) {
			IllegalArgumentException myException = new IllegalArgumentException();
			throw myException;
		}
		do {
			String input = in.readLine();

			if (input.equalsIgnoreCase(trueString)) {
				isBoolean = true;
				isGoodInput = true;
			} else if (input.equalsIgnoreCase(falseString)) {
				isBoolean = false;
				isGoodInput = true;
			} else if (!input.equalsIgnoreCase(trueString) || !input.equalsIgnoreCase(falseString)) {
				System.out.println("That is not a valid answer. Please try again...");
				isGoodInput = false;
			}

		} while (!isGoodInput);
		return isBoolean;		
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the byte value
	 * @throws IOException
	 */
	static byte promptForByte(String prompt, byte min, byte max) throws IOException {
		return (byte) promptForShort(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the short value
	 * @throws IOException
	 */
	static short promptForShort(String prompt, short min, short max) throws IOException {
		return (short) promptForInt(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the int value
	 * @throws IOException
	 */
	public static int promptForInt(String prompt, int min, int max) throws IOException {

		return (int) promptForLong(prompt, min, max);

	}

	/**
	 * Generates a prompt that expects a numeric input representing a long
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the long value
	 * @throws IOException
	 */
	static long promptForLong(String prompt, long min, long max) throws IOException {

		System.out.println(prompt);
		String rawInput = null;
		long input = 0;
		boolean isGoodInput = true;
		if (prompt == null || min>max) {
			IllegalArgumentException myException = new IllegalArgumentException();
			throw myException;
		}
		do {
			rawInput = in.readLine();
			isGoodInput = true;
			try {
				input = Long.parseLong(rawInput);
			} catch (NumberFormatException myException) {
				System.out.println("Input is not valid. Please enter a number.");
				isGoodInput = false;
			}

			if (isGoodInput == true) {
				if (input < min || input > max) {
					isGoodInput = false;
					System.out.println("Sorry that is not a valid number. Please enter a numerical value between " + min
							+ " and " + max);
				} else {
					isGoodInput = true;
				}
			}
		} while (!isGoodInput);
		return input;

	}

	/**
	 * Generates a prompt that expects a numeric input representing a float
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the float value
	 * @throws IOException
	 */
	static float promptForFloat(String prompt, float min, float max) throws IOException {
		return (float) promptForDouble(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double
	 * value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the double value
	 * @throws IOException
	 */
	static double promptForDouble(String prompt, double min, double max) throws IOException {
		System.out.println(prompt);
		String rawInput = null;
		double input = 0;
		boolean isGoodInput = true;
		if (prompt == null || min>max) {
			IllegalArgumentException myException = new IllegalArgumentException();
			throw myException;
		}
		do {
			rawInput = in.readLine();
			isGoodInput = true;
			try {
				input = Double.parseDouble(rawInput);
			} catch (NumberFormatException myException) {
				System.out.println("Input is not valid. Please enter a number.");
				isGoodInput = false;
			}

			if (isGoodInput == true) {
				if (input < min || input > max) {
					isGoodInput = false;
					System.out.println("Sorry that is not a valid number. Please enter a numerical value between " + min
							+ " and " + max);
				}
			}
		} while (!isGoodInput);
		return input;

	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns
	 * the String. When allowEmpty is true, empty responses are valid. When
	 * false, responses must contain at least one character (including
	 * whitespace).
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user.
	 * @param allowEmpty
	 *            - when true, makes empty responses valid
	 * @return the input from the user as a String
	 * @throws IOException
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) throws IOException {
		if (prompt == null) {
			IllegalArgumentException myException = new IllegalArgumentException();
			throw myException;
		}
		System.out.println(prompt);
		String input = in.readLine();
		while (input.length() == 0 && allowEmpty == false) { // ! is equal to
																// allowEmpty ==
																// false
			System.out.println("Sorry, you can't enter the empty string");
			input = in.readLine();
		}
		return input;
	}
	// FIN

	/**
	 * Generates a prompt that expects a single character input representing a
	 * char value. This method loops until valid input is given.
	 * 
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the char value
	 * @throws IOException
	 */
	public static char promptForChar(String prompt, char min, char max) throws IOException {
		System.out.println(prompt);

		boolean isGoodInput = true;
		String input;
		char charInput = 0;
		if (prompt == null || min>max) {
			IllegalArgumentException myException = new IllegalArgumentException();
			throw myException;
		}
		do {
			input = in.readLine();
			
			if (input.length() != 1) {
				System.out.println("Sorry, the input is not valid. Please try again.");
				isGoodInput = false;
			} else {
				charInput = input.charAt(0);
				if (charInput < min || charInput > max) {
					System.out.println("Sorry, the character you entered is not between " + min + " and " + max);
					isGoodInput = false;
				} else {

					isGoodInput = true;
				}

			}
		} while (!isGoodInput);

		return charInput;
	}

}
