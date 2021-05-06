package hw6_E_Knapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Anagrams {
	
    // data fields - primes Array, Map tables
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	// constructor
	public Anagrams() { 
		letterTable = new HashMap<>();
		anagramTable = new HashMap<>();
		
		buildLetterTable(); // method call to build new table
	}
	
	// letter table builder for primes
	private void buildLetterTable() {
		int i;
		
		for (i = 0; i < 26; ++i) {
			letterTable.put((char)((char) 97 + i ), primes[i]);
		}
	}
	
	// calculates hash code of the String and puts in anagrams table
	private void addWord(String s) {
		
		if (anagramTable.containsKey(myHashCode(s))) {
			anagramTable.get(myHashCode(s)).add(s);
		}
		
		else {
			ArrayList<String> array1 = new ArrayList<>();
			array1.add(s);
			anagramTable.put(myHashCode(s), array1);
		}
	}
	
	// hash code from string s
	private Long myHashCode(String s) {
		long hash_code = 1;
		int i;
		for (i = 0; i < s.length(); ++i) {
			hash_code *= letterTable.get(s.charAt(i));
		}
		
		return hash_code;
	}
	
	// file input - process the file
	public void processFile(String s) throws IOException{
		
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}
	
	// returns maximum # of anagrams
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		int maximum = 0;
		
		ArrayList<Map.Entry<Long, ArrayList<String>>> array1 = new ArrayList<>();
		
		for (Map.Entry<Long, ArrayList<String>> word: anagramTable.entrySet()) {
			
			if (word.getValue().size() > maximum) {
				array1.clear();
				maximum = word.getValue().size();
				array1.add(word);
			}
			
			else if (word.getValue().size() == maximum) {
				array1.add(word);
			}
		}
		
		return array1;
	}
	
	// read and output for words_alpha.txt file
	public static void main(String[] args) {
		
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();
		
		try {
			a.processFile("words_alpha.txt"); // file sample downloaded from GitHub - read package folder
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		
		// data fields
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime /  1000000000);
		long key_Number = maxEntries.get(0).getKey(); // key number for key
		int list_Length = maxEntries.get(0).getValue().size(); // length of list (15)

		// output - Elapsed time, key of max anagrams, list of max anagrams, length of max anagrams: formatted to match homework output
		System.out.println("Elapsed Time: " + seconds);
		System.out.println("Key of max anagrams: " + key_Number);
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams: " + list_Length);
		

	}

}

// Eric Knapp
// Homework Assignment 6: Hashing (Anagrams)
// 5/4/21
