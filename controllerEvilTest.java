package Hangman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class controllerEvilTest {
	
	controllerEvil testControllEvil;

	@Before
	public void setUp() throws Exception {
		testControllEvil = new controllerEvil();
	}

	@Test
	public void testUpdateWordList() {
		ArrayList<String> guessWords = new ArrayList<>();
		//test for each elements have only one letter
		guessWords.add("dog");
		guessWords.add("dot");
		guessWords.add("ode");
		guessWords.add("ago");
		ArrayList<String> newList = testControllEvil.UpdateWordList(guessWords, "o");
		assertEquals(newList.size(), 2);
		assertTrue(newList.contains("dog"));
		assertTrue(newList.contains("dot"));
		assertFalse(newList.contains("ode"));
		assertFalse(newList.contains("ago"));
		
		//test for the return list which does not contain input letter
		guessWords.clear();
		testControllEvil.guessWordSort.clear();
		guessWords.add("end");
		guessWords.add("men");
		guessWords.add("and");
		guessWords.add("nor");
		ArrayList<String> newList1 = testControllEvil.UpdateWordList(guessWords, "e");
		assertEquals(newList1.size(), 2);
		assertTrue(newList1.contains("nor"));
		assertTrue(newList1.contains("and"));
		assertFalse(newList1.contains("end"));
		assertFalse(newList1.contains("men"));
		
		//test for the return list contains multiple same letters
		guessWords.clear();
		testControllEvil.guessWordSort.clear();
		guessWords.add("eel");
		guessWords.add("men");
		guessWords.add("eek");
		guessWords.add("and");
		ArrayList<String> newList2 = testControllEvil.UpdateWordList(guessWords, "e");
		assertEquals(newList2.size(), 2);
		assertTrue(newList2.contains("eel"));
		assertTrue(newList2.contains("eek"));
		assertFalse(newList2.contains("and"));
		assertFalse(newList2.contains("men"));
		
		//test for the return list contains multiple same letters
		guessWords.clear();
		testControllEvil.guessWordSort.clear();
		guessWords.add("eke");
		guessWords.add("men");
		guessWords.add("eek");
		guessWords.add("eye");
		ArrayList<String> newList3 = testControllEvil.UpdateWordList(guessWords, "e");
		assertEquals(newList3.size(), 2);
		assertTrue(newList3.contains("eye"));
		assertTrue(newList3.contains("eke"));
		assertFalse(newList3.contains("eek"));
		assertFalse(newList3.contains("men"));
	}

}
