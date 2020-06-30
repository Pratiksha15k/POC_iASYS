package com.string.example;

import java.util.HashSet;
import java.util.Scanner;

public class DuplicateCharactersInString {
	public static void main(String args[]){
		String str = null;
		System.out.println("Enter String :");
		Scanner scan = new Scanner(System.in);
		str = scan.nextLine();
		char chars[] = str.toCharArray();
		HashSet<Character> map = new HashSet<>();
		for(char c : chars) 
	    	{
	            if(map.contains(c)) {
	            	System.out.print(""+c);
	            } else {
	                map.add(c);
	            }
	        }
	         
		scan.close();
	}
}
