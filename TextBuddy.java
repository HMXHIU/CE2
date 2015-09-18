import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class TextBuddy {

	private static Scanner sc = new Scanner(System.in);
	private static String fileName;
	private static ArrayList<String> inputData;
	private static String line;
	
	// The possible input command to be enter by user.
	enum InputCommand {
		ADD, DISPLAY, DELETE, CLEAR, EXIT, INVALID
	}

	public static void main(String[] args) {
		checkForFile(args);
		processFile(args[0]);
		processCommand();
	}

	/**
	 * This method is used for check the availability of the specified file.
	 * 
	 * @param arg
	 *            is the filename input by the user.
	 */
	public static void checkForFile(String[] arg) {
		if (arg.length == 0) {
			System.out.println("File not located. Please Try Agian.");
			System.exit(0);
		}
	}
	
	/**
	 * This method is used for read data from the specified file.
	 * 
	 * @param arg
	 *            is the filename input by the user.
	 */
	public static void processFile(String arg) {

		fileName = arg;
		inputData = new ArrayList<String>();

		try {
			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);

			// Read file line by line and store into arraylist
			while ((line = bufferReader.readLine()) != null) {
				inputData.add(line);
			}

			bufferReader.close();

		} catch (Exception e) {
			System.out.println("Error while reading file: " + e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * This method is used for process all the command entered by the user.
	 */
	public static void processCommand() {
		InputCommand command;

		System.out.println("Welcome to TextBuddy. " + fileName + " is ready for use!");
		while (true) {
			System.out.print("command: ");
			command = checkCommand(sc.next().toUpperCase());
			switch (command) {
			case ADD:
				commandAdd();
				break;
			case DISPLAY:
				commandDisplay();
				break;
			case DELETE:
				commandDelete();
				break;
			case CLEAR:
				commandClear();
				break;
			case INVALID:
				wrongCommand();
				break;
			case EXIT:
				commandExit();
				break;
			default:
				throw new Error("Unrecognized command type");
			}
		}
	}
	
	/**
	 * This operation classified command into different type
	 * 
	 * @param input
	 *            is the first word of the user input
	 */
	public static InputCommand checkCommand(String input) {
		if (input == null)
			throw new Error("command type string cannot be null!");

		if (input.equalsIgnoreCase("ADD")) {
			return InputCommand.ADD;
		} else if (input.equalsIgnoreCase("DISPLAY")) {
			return InputCommand.DISPLAY;
		} else if (input.equalsIgnoreCase("DELETE")) {
			return InputCommand.DELETE;
		} else if (input.equalsIgnoreCase("CLEAR")) {
			return InputCommand.CLEAR;
		} else if (input.equalsIgnoreCase("EXIT")) {
			return InputCommand.EXIT;
		} else {
			return InputCommand.INVALID;
		}
	}

	public static void wrongCommand() {
		System.out.println("Wrong input of command. Please try agian.");
	}

	/**
	 * This method is to add variable into the array list.
	 */
	public static void commandAdd() {

		line = sc.nextLine().trim();
		inputData.add(line);
		System.out.println("added to " + fileName + " : \"" + line + "\"");
		save();
	}

	/**
	 * This method is to display all the variable into the array list.
	 */
	public static void commandDisplay() {
		int i = 0;
		if (inputData.size() > 0) {
			while (i != inputData.size()) {
				i++;
				System.out.println(i + ". " + inputData.get(i - 1).toString());
			}
		} else
			System.out.println(fileName + " is empty");
	}

	/**
	 * This method is to remove variable from the array list.
	 */
	public static void commandDelete() {
		int number;
		try{
			number = sc.nextInt() - 1;
			if (number + 1 > inputData.size()||number<0) {
				System.out.println("Error! Could not found the number on list!");
			} else {
				line = inputData.get(number);
				inputData.remove(number);
				System.out.println("deleted from " + fileName + ": \"" + line + "\"");
				save();
			}
		}
		catch (Exception e) {
            System.out.println("Error on delete command!");
            sc.nextLine();
        }
	}
	
	/**
	 * This method is to clear the entire array list.
	 */
	public static void commandClear() {
		inputData.clear();
		System.out.println("all content deleted from " + fileName);
		save();
	}

	/**
	 * Exit the program.
	 */
	public static void commandExit() {
		System.exit(0);
	}

	/**
	 * This method is to print the arraylist into the specified file.
	 */
	public static void save() {
		int i = 0;

		try {
			FileWriter fileWrite = new FileWriter(fileName);
			BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
			PrintWriter fileOut = new PrintWriter(bufferWrite);
			
			// Write the arraylist line by line into the file specified
			while (i < inputData.size()) {
				fileOut.println(inputData.get(i).toString());
				i++;
			}
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
