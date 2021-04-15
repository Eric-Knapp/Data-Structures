// Eric Knapp
// March 21, 2021
// CS 570 Data Structures
// Homework Assignment 1: Binary Number

import java.util.*;

public class BinaryNumber {

	private int data[];
	private boolean overflow;
	
	
	// binary number of length consisting of all 0s -- throws exception if length not greater than 0
	public BinaryNumber(int length) {
		overflow = false;
		if (length <= 0){
			throw new IllegalArgumentException("Length must be greater than 0");
		}
		
		int binary1 [] = new int[length];
		data = binary1;
		this.getLength();
	}
	
	// create binary number given a string
	public BinaryNumber(String str) {
		int length = str.length();
		data = new int[length];
		int i;
		
		for(i = 0; i < length; ++i) {
			char char1 = str.charAt(i);
			data[i] = Character.getNumericValue(char1);
		}	
	}
	
	// return binary number length
	public int getLength() {
		return data.length;
	}
	

	
	// get digit of a binary number given an index
	// if digit is out of bounds message shows index out of bounds
	public int getDigit(int index) {
		if (index >= 0 && index < data.length) {
			return data[index];
		}
		else {
			System.out.println("The array index is out of bounds.");
			return - 1;
		}
	}
	
// shift elements in binary number any amount to the right in the arrayShift array
	public void shiftR(int amount) { 
		int [] arrayShift = Arrays.copyOf(data, data.length + amount);
		int i;
		for (i = data.length -1; i >= 0; --i) {
			arrayShift[i + amount] = arrayShift[i];
		}
		for (i = 0; i < amount; ++i) {
			arrayShift[i] = 0;
		}
		System.out.println(Arrays.toString(arrayShift)); // prints out shifted array for testing
	}
	
	
	public void add(BinaryNumber aBinaryNumber) {
		int c = 0;
		int index = data.length - 1;
		int sum = 0;
		if (aBinaryNumber.getLength() != 0 && data.length == aBinaryNumber.getLength()) {
			while (index >= 0) {
				sum = data[index] + aBinaryNumber.getDigit(index) + c;
				data[index] = sum % 2;
				c = sum / 2;
				--index;
			}
		}

		else {
			System.out.println("Please enter a binary number with the same length");
		}
	}

	
	// change each attribute in data to string
	// if overflow is false --> method returns the string
	public String toString() {
		String s = "";
		int i;
		for(i = 0; i < data.length; ++i) {
			s += data[i];
		}
		if (overflow == true) {
			throw new IllegalArgumentException("Overflow"); 
		}
		else {
			return s;
		}
	}
	// changes number from binary to decimal format
	
	// transforms binary number to its decimal notation
	public int toDecimal() {
		int decimalNum = 0;
		double P = data.length - 1;
		int i;
		
		for (i = 0; i < data.length; ++i) {
			decimalNum += data[i] * Math.pow(2, i);
			--P;
		}
		return decimalNum;	
	}
	
	// clear overflow method
	
	// method sets overflow to false, which clears the overflow flag
	public void clearOverflow() {
		overflow = false;
	}
	
	

	// **TESTING ALL METHODS** //IGNORE
	public static void main(String[] args) {


		// TEST: 2 BinaryNumber objects (one int one String)
		BinaryNumber bi = new BinaryNumber(4); // works to create 4 0s
		System.out.println(bi);
		BinaryNumber bii = new BinaryNumber("1011"); // works correct to create binary string #
		System.out.println(bii);
		
		//TEST: getLength()
		System.out.println(bii.getLength()); // works correctly to get length
		
		// TEST: getDigit()
		System.out.println(bii.getDigit(2)); // works correct in test to get value of index in list
		
		// TEST: shiftR()
		bii.shiftR(3); // outputs shifted array correctly
		
		// TEST: toString()
		System.out.println(bi.toString()); // correct in test
		
		// TEST: toDecimal()
		System.out.println(bii.toDecimal()); //  works correct in test for changing to decimal form
		
		// TEST: clearOverflow()
		bii.clearOverflow();
		
		BinaryNumber biii = new BinaryNumber("1010");
		
		//TEST: add() 
		bii.add(biii);
		System.out.println(bii);

		
	}
	
}
