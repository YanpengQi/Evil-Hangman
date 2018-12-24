package Hangman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class controllerTest {
	
	controller testController;

	@Before
	public void setUp() throws Exception {
		testController = new controller();
		testController.Lines.add("word");
		testController.Lines.add("dog");
		testController.Lines.add("family");
	}
	
	
	@Test
	public void testInitializeGame() {

		testController.initializeGame();
		//test initial count number is 
		assertEquals(testController.count, 0);
		
		//test initial guessedLetter list and incorrectGuess list are empty
		assertTrue(testController.guessedLetter.isEmpty());
		assertTrue(testController.incorrectGuesses.isEmpty());
		
		//test whether the length of guessword is in the range
		assertTrue(testController.guessWord.length() >= 3);
		assertTrue(testController.guessWord.length() <= 6);
		
		//test whether the limitCount is in the range
		assertTrue(testController.limtiCount >= 8);
		assertTrue(testController.limtiCount <= 11);
		
		//check whether the printWord is filled with "_ "
		for (int i = 0; i < testController.printWord.size(); i++) {
			assertEquals(testController.printWord.get(i), "_ ");
		}
	}

	@Test
	public void testIsOver() {
		testController.initializeGame();
		//at the beginning, the game is not over
		assertFalse(testController.isOver(testController.guessWord));
		
		for (int i = 0; i < testController.guessWord.length(); i++) {
			testController.printWord.set(i, testController.guessWord.substring(i, i + 1));
		}
		//test when the printWord is replaced by correct guess
		assertTrue(testController.isOver(testController.guessWord));
	}

	@Test
	public void testIsLetter() {
		//test for general case
		assertTrue(testController.isLetter("a"));
		
		//test for a number
		assertFalse(testController.isLetter("1"));
		
		//test for a compound
		assertFalse(testController.isLetter("12aa"));
		
		//test for a space
		assertFalse(testController.isLetter(" "));
		
		//test for weird input
		assertFalse(testController.isLetter("*"));
	}

	@Test
	public void testUpdateAndPrint() {
		testController.initializeGame();
		String guessWord = "dog";
		//input is not in the word
		testController.updateAndPrint(guessWord, "a");
		assertTrue(testController.incorrectGuesses.contains("a"));
		assertTrue(testController.guessedLetter.contains("a"));
		
		//upperCase input will be transfer to lowerCase
		testController.updateAndPrint(guessWord, "A");
		assertFalse(testController.incorrectGuesses.contains("A"));
		assertFalse(testController.guessedLetter.contains("A"));
		
		//input is in the word
		testController.updateAndPrint(guessWord, "o");
		assertFalse(testController.incorrectGuesses.contains("o"));
		assertTrue(testController.guessedLetter.contains("a"));

		testController.updateAndPrint(guessWord, "d");
		assertFalse(testController.incorrectGuesses.contains("d"));
		assertTrue(testController.guessedLetter.contains("d"));
		
		testController.updateAndPrint(guessWord, "g");
		assertFalse(testController.incorrectGuesses.contains("g"));
		assertTrue(testController.guessedLetter.contains("g"));
		
	}


}
