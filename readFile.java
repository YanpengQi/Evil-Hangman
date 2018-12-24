package Hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readFile {
	
	//this is fileAddress that we need to input
	String fileAddress;
	
	public readFile(String address) {
		fileAddress = address;
	}
		
	/**
	 * this method is used to read eachline from file
	 * @return Arraylist<String>
	 */
	public ArrayList<String> getAllLines() {

		// initialize an arraylist to hold the lines of text
		ArrayList<String> lines = new ArrayList<String>();

		try {

			// create an BufferedReader to handle reading the file line by line
			BufferedReader file = new BufferedReader(new FileReader(fileAddress));

			// get the first line of the file
			// will be null if no content in the file
			String line = file.readLine();

			// while there is more content
			while (line != null) {

				// trim the line
				line = line.trim();

				// make sure line wasn't just white space
				if (!line.isEmpty()) {
					// add the current line to the arraylist
					lines.add(line);
				}

				// get the next line in the file
				// will be null if no more lines in the file
				line = file.readLine();
			}

			// close the file resource
			file.close();

		} catch (FileNotFoundException e) {

			// print out what went wrong if file can not be found
			e.printStackTrace();

		} catch (IOException e) {

			// print out what went wrong if Input and Output Exception
			e.printStackTrace();

		}

		// return the arraylist of lines of text
		return lines;
	}
	
	/**
	 * this is to check if the word is made up with letters
	 * @param string word
	 * @return boolean
	 */
	public boolean checkFormat(String word) {
		//check each character
		for (int i = 0; i < word.length(); i++) {
			//get the character of each digit
			char c = word.charAt(i);
			//check whether the character is lowercase or not, if not, return false directly
			if(!Character.isLowerCase(c)) {
				return false;
			}
		}
		//otherwise, return return
		return true;
	}
	
	/**
	 * this is to remove unsuitable words in dictionary
	 * @param dictionary 
	 * @return new arraylist
	 */
	public ArrayList<String> filter(ArrayList<String> lines) {
		ArrayList<String> newLines = new ArrayList<>();
		//get each word in lines
		for(String word : lines) {
			//if it is the correct format, add it to newlines
			if(checkFormat(word)) {
				newLines.add(word);
			}
		}
		//returnn new lines
		return newLines;
	}
	
}
