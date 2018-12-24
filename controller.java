package Hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class controller {
	
	//count the score
	int count;
	//the word we need to guess
	String guessWord;
	//the limited time
	int limtiCount;
	
	//the letters that user already guessed
	ArrayList<String> guessedLetter = new ArrayList<>();
	//the incorrect guesses that should be printed
	ArrayList<String> incorrectGuesses = new ArrayList<>();
	//lines we read from the file
	ArrayList<String> Lines = new ArrayList<>();
	//the word that we should print, including "_ "
	ArrayList<String> printWord = new ArrayList<>();
	
	/**
	 * this method get a random word from input file arraylist: Lines
	 * use this random word as guessWord
	 * @param Lines
	 * @return
	 */
	public String randomWord(ArrayList<String> Lines) {
		Random random = new Random();
		int len = Lines.size();
		return Lines.get(random.nextInt(len));
	}
	
	/**
	 * check if the game is over
	 * @param guessWord
	 * @return
	 */
	public boolean isOver(String guessWord) {
		
		//only when the letter is correct, we can replace it in printWord
		//only when the printWord is replaced totally, the game is over
		for (int i = 0; i < printWord.size() ; i++) {
			if(printWord.contains("_ ")){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * this is shown to user
	 * what should be print
	 */
	public void print(String guessWord) {
		//guessWord = randomWord(Lines);
		System.out.println("Guess a letter\n");
		//check the updated guessedLetter and incorrectGuess Arraylist
		//use them to update printWord Arraylist
		for (int i = 0; i < guessWord.length(); i++) {
			if(guessedLetter.contains(guessWord.substring(i, i + 1))) {
				printWord.set(i, guessWord.substring(i, i + 1));
			}
			System.out.print(printWord.get(i));
		}
		System.out.println("");
		
		//if user have incorrect inputs, show user
		if (!incorrectGuesses.isEmpty()) {
			System.out.println("\nIncorrect guesses:");
			System.out.println(incorrectGuesses);
		}
	}
	
	/**
	 * make sure the user's input is a letter
	 * @param input
	 * @return
	 */
	public boolean isLetter(String input) {
		 return input.length() == 1 && Character.isLetter(input.charAt(0));
	}
	
	/**
	 * when each time of game starts, we need to initialize these variables
	 */
	public void initializeGame() {
		count = 0;
		//for each round of game, we need to reset guessWord
		guessWord = randomWord(Lines);
		//for each round of game, we need to clear all
		guessedLetter.clear();
		incorrectGuesses.clear();
		//set limit count to judge whether the player win or not
		limtiCount = guessWord.length() + 5;
		//initialize print list
		printWord.clear();
		for (int i = 0; i < guessWord.length(); i++) {
			printWord.add("_ ");
		}
	}
	
	/**
	 * for each round of game, according to the input, update all kinds of list and print some information
	 * @param guessWord
	 * @param input
	 */
	public void updateAndPrint(String guessWord, String input) {
		//change all input to lower case
		input = input.toLowerCase();
		//update list for each round
		if(guessedLetter.contains(input)) {
			//repeat situations
			System.out.println("You already tried this letter.");
		}else {
			if (!guessWord.contains(input)) {
				incorrectGuesses.add(input);
				guessedLetter.add(input);
			}else {
				//general situations
				guessedLetter.add(input);
			}	
		}
		print(guessWord);
	}
	
	/**
	 * After the word is guessed, judge whether the player won or not and print the score
	 * Let player choose to continue or not
	 */
	public void printResult() {
		if (count > limtiCount) {
			System.out.println("Sorry, you lost!");
		} else {
			System.out.println("You win!");
		}
		System.out.println("You used " + count + " times.");
		System.out.println("Play again?(Enter 'No' to stop)");
	}
	
	/**
	 * this is the main process get every method together
	 */
	public void play() {
		//detect the input of user
		Scanner scannerInput = new Scanner(System.in);
		boolean playAgain = true;
		
		while(playAgain) {
			//initialize all variables
			initializeGame();
			print(guessWord);
			
			//one around process
			while (!isOver(guessWord)) {
				String input = scannerInput.nextLine();
				//record the score
				count ++;
				//test for a letter, user must input a letter
				while(!isLetter(input)) {
					System.out.println("Please make sure to enter a letter");
					input = scannerInput.nextLine();
				}
				
				//for each round of game, according to the input, update all kinds of list and print some information
				updateAndPrint(guessWord, input);
			}
			printResult();
			//let player choose whether play or not
			//only if the player choose No, the game will stop
			if(scannerInput.nextLine().equals("No"))
				playAgain = false;
		}
		//close scanner
		scannerInput.close();
	}
	
}
