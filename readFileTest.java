package Hangman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class readFileTest {

	readFile testReadFile;
	
	@Before
	public void setUp() throws Exception {
		testReadFile = new readFile("/Users/apple/Desktop/hw9/short_list.txt");
	}

	@Test
	public void testFileReader() {
		assertTrue(testReadFile.fileAddress.equals("/Users/apple/Desktop/hw9/short_list.txt"));
	}

	@Test
	public void testGetAllLines() {
		ArrayList<String> lines = new ArrayList<>();
		lines = testReadFile.getAllLines();
		assertTrue(lines.get(0).equals("coil"));
		assertTrue(lines.get(3).equals("loving"));
		assertTrue(lines.get(lines.size() - 1).equals("record"));
	}

	@Test
	public void testCheckFormat() {
		
		//test for general case
		String testString = "string";
		assertTrue(testReadFile.checkFormat(testString));		
		
		//test for Compound words
		String testString1 = "st ring";
		assertFalse(testReadFile.checkFormat(testString1));
		
		//test for a digit​
		String testString2 = "123string";
		assertFalse(testReadFile.checkFormat(testString2));
		
		//test for a hyphen​
		String testString3 = "user-generated";
		assertFalse(testReadFile.checkFormat(testString3));
		
		//test for An apostrophe
		String testString4 = "​you’re​";
		assertFalse(testReadFile.checkFormat(testString4));
		
		//test for Abbreviations
		String testString5 = "mrs​.";
		assertFalse(testReadFile.checkFormat(testString5));
		
		//test for Upper case letters
		String testString6 = "Shakespearean";
		assertFalse(testReadFile.checkFormat(testString6));
	}

	@Test
	public void testFilter() {
		readFile testReadFile1 = new readFile("/Users/apple/Desktop/hw9/words.txt");
		ArrayList<String> lines = new ArrayList<>();
		lines = testReadFile1.filter(testReadFile1.getAllLines());
		
		//test for general cases 
		assertTrue(lines.get(0).equals("a"));
		assertTrue(lines.get(3).equals("aahing"));
		
		//test for numbers
		assertFalse(lines.contains("2"));
		
		//test for Compound words
		assertFalse(lines.contains("&c"));
		
		//test for An apostrophe
		assertFalse(lines.contains("a'"));
		
		//test for a hyphen​
		assertFalse(lines.contains("accident-prone"));
		
		//test for Upper case letters
		assertFalse(lines.contains("Accius"));
		
		//test for Abbreviations
		assertFalse(lines.contains("acct."));
		
		
	}

}
