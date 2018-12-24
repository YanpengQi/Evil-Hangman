package Hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class controllerEvil extends controller {
	
	Map<String, ArrayList<String>> guessWordSort = new HashMap<String, ArrayList<String>>();
	Set<String> KeySet = guessWordSort.keySet();
	ArrayList<String> guessWords = new ArrayList<>();

	/**
	 * this function divide guessWords into different types according to the key
	 * choose the type with biggest number of elements 
	 * return this Arraylist as the new guessWords
	 * @param guessWords
	 * @param input
	 * @return
	 */
	public ArrayList<String> UpdateWordList(ArrayList<String> guessWords, String input) {
	
		//set the key using index
		//if the word have 2 input, set the key as string of index and index
		//for example word "bee", set the key as "12"
		//for the word "key", the key should be "1"
		for (int i = 0; i < guessWords.size(); i++) {
			String key = "";
			for (int j = 0; j < guessWords.get(i).length(); j++) {
				if(input.equals(guessWords.get(i).substring(j, j+1))){
					key += String.valueOf(j);
				}
			}
			if(!guessWordSort.containsKey(key)) {
				guessWordSort.put(key, new ArrayList<String>());
			}
			ArrayList<String> temp = guessWordSort.get(key);
			temp.add(guessWords.get(i));
		}
		//set the initial max value
		int max = 0;
		//this arraylist is used to store the arraylist with the max number of elements
		ArrayList<String> newGuessWords = new ArrayList<>();
		//go through each key and get each value's size
		for(String key : KeySet) {
			//compare each size with max
			if(max < guessWordSort.get(key).size()) {
				//if the size is bigger than the max value, replace it and put the arraylist into newGuessWords list
				max = guessWordSort.get(key).size();
				newGuessWords.clear();
				newGuessWords.addAll(guessWordSort.get(key));
			}
		}
		return newGuessWords;
	}
	
	/**
	 * this is the main process of the game
	 */
	@Override
	public void play() {
		//detect the input of user
		Scanner scannerInput = new Scanner(System.in);
		boolean playAgain = true;
		
		while(playAgain) {
			//initialize all variables
			initializeGame();
			//initialize guessWords list
			for(String word : Lines) {
				//this guessWord is just used for limiting length
				if(word.length() == guessWord.length()) {
					//only add the words with same size of guessWord
					guessWords.add(word);
				}
			}
			print(guessWords.get(0));
			//one around process
			//this guessWord is just used for limiting length, only each element is replaced, game ends
			while (!isOver(guessWord)) {
				String input = scannerInput.nextLine();
				//record the score
				count ++;
				//test for a letter, user must input a letter
				while(!isLetter(input)) {
					System.out.println("Please make sure to enter a letter");
					input = scannerInput.nextLine();
				}
				
				//update guessWords list what we choose word from
				guessWordSort.clear();
				guessWords = UpdateWordList(guessWords, input);
				
				//for each round of game, according to the input, update all kinds of list and print some information
				updateAndPrint(guessWords.get(0), input);
			}
			printResult();
			//let player choose whether play or not, only if the player choose No, the game will stop
			if(scannerInput.nextLine().equals("No"))
				playAgain = false;
		}
		//close scanner
		scannerInput.close();
	}
	
	public static void main(String[] args) {
		//initialize and get the address
		controllerEvil playerEvil =  new controllerEvil();
		readFile inputLines = new readFile("/Users/apple/Desktop/3.16/Hangman/src/words.txt");
		
		//if you want to play traditional hangman, uncomment this
		//controller playTraditional = new controller();
		
		
		//get the input from file
		playerEvil.Lines = inputLines.filter(inputLines.getAllLines());
		
		//the play process is all in play method
		playerEvil.play();
		
		//if you want to play traditional hangman, uncomment this
		//playTraditional.play();
		
		//end the game
		System.out.println("Thanks for playing!");
	}
}
