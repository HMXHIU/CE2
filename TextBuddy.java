import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class TextBuddy {
	
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready to be used.\n";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_ERRORFILE = "File not specified. Please Try Agian.\n";
	private static final String MESSAGE_EMPTYADD = "No input detected! Please try agian.\n";
	private static final String MESSAGE_NOTFOUND = "The word is not found in the list!\n";
	private static final String MESSAGE_SORTED = "The list is sorted in order!!\n";
	private static final String MESSAGE_WRONGINPUT = "Wrong input of command. Please try agian.\n";
	private static final String MESSAGE_ERRORDELETE = "Error on delete command! Please enter number\n";
	private static final String MESSAGE_CLEAR = "all content deleted from %s\n";
	private static final String MESSAGE_NOTFOUNDLIST = "Error! Could not found the number on list!\n";
	private static final String MESSAGE_EMPTY = "%s is empty!\n";
	private static final String MESSAGE_DELETED = "deleted from %s: \"%s\"\n";
	private static final String MESSAGE_ADDED = "Added to %s : \"%s\"\n";
	private static final String MESSAGE_ERRORREADFILE = "Error in reading/ creating of file.\n";
	private static final String MESSAGE_ERRORSEARCH = "Error! No input detected for search.\n";
	private static Scanner sc = new Scanner(System.in);
	private static String fileName;
	private static ArrayList<String> inputData;
	private static String line;

	// The possible input command to be enter by user.
	enum InputCommand {
		ADD, DISPLAY, DELETE, CLEAR, EXIT, INVALID, SORT, SEARCH
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
			printMessage(MESSAGE_ERRORFILE);
			System.exit(0);
		}
		File file = new File(arg[0]);
		try {
			if (!file.exists()) {
				printMessage("File not found!!! Do you want to create the file? Y/N");
				if(sc.nextLine().toUpperCase().equals("Y")){
					file.createNewFile();
				}
			}
		} catch (IOException e) {
			printMessage(MESSAGE_ERRORREADFILE);
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
		
		printMessage(String.format(MESSAGE_WELCOME, fileName));
		while (true) {
			printMessage(MESSAGE_COMMAND);
			printMessage(executeCommand(sc.next().toUpperCase()));
		}
	}
	
	/**
	 * This method is used for execute all the command.
	 */
	public static String executeCommand(String instruction) {
		InputCommand command;
		
		command = checkCommand(instruction);
		try{
			switch (command) {
			case ADD:
				return commandAdd(sc.nextLine().trim());

			case DISPLAY:
				return commandDisplay();

			case DELETE:
				return commandDelete(sc.nextInt());

			case CLEAR:
				return commandClear();

			case SORT:
				return commandSort();

			case SEARCH:
				return commandSearch(sc.nextLine().trim());

			case EXIT:
				commandExit();
				break;

			default:
				return MESSAGE_WRONGINPUT;
			}
			return MESSAGE_WRONGINPUT;
		}
		catch(Exception e){
			return MESSAGE_ERRORDELETE;
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
		} else if (input.equalsIgnoreCase("SORT")) {
			return InputCommand.SORT;
		} else if (input.equalsIgnoreCase("SEARCH")) {
			return InputCommand.SEARCH;
		} else {
			return InputCommand.INVALID;
		}
	}

	public static String commandSearch(String information) {

		int i, size = inputData.size(), numberOfFound = 0;
		String result="";
		
		if(information.isEmpty())
			return MESSAGE_ERRORSEARCH;
		
		CharSequence cs = information;
		
		for (i = 0; i < size; i++) {
			if (inputData.get(i).contains(cs)) {
				numberOfFound++;
				result= result.concat(numberOfFound + ". " + inputData.get(i)+ "\n");
			}
		}
		if (numberOfFound == 0) {
			return MESSAGE_NOTFOUND;
		}
		result= result.concat("Total of " + numberOfFound + " results are found!\n");
		return result;
	}

	public static String commandSort() {
		Collections.sort(inputData);
		save();
		return MESSAGE_SORTED;
		
	}

	/**
	 * This method is to add variable into the array list.
	 */
	public static String commandAdd(String information) {
		if(information.isEmpty())
			return MESSAGE_EMPTYADD;
		inputData.add(information);
		save();
		return (String.format(MESSAGE_ADDED, fileName, information));
	}

	/**
	 * This method is to display all the variable into the array list.
	 */
	public static String commandDisplay() {
		int i = 0;
		String result="";
		if (inputData.size() > 0) {
			while (i != inputData.size()) {
				i++;
				 result= result.concat(i + ". " + inputData.get(i - 1).toString() + "\n");
			}
			return result;
		} 
		return (String.format(MESSAGE_EMPTY, fileName));
	}

	/**
	 * This method is to remove variable from the array list.
	 */
	public static String commandDelete(int information) {
		int number;

		number = information - 1;
		if (number + 1 > inputData.size() || number < 0) {
			return MESSAGE_NOTFOUNDLIST;
		} else {
			line = inputData.get(number);
			inputData.remove(number);
			save();
		}
		return (String.format(MESSAGE_DELETED, fileName, line));
	}

	/**
	 * This method is to clear the entire array list.
	 */
	public static String commandClear() {
		inputData.clear();
		save();
		return (String.format(MESSAGE_CLEAR,fileName));
		
	}

	public static void commandExit() {
		System.exit(0);
	}
	
	public static void printMessage(String msg){
		System.out.print(msg);
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

