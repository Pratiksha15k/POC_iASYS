package com.simpleprograms.example;

import java.util.Scanner;

public class CountNoOfWords {
	public static void main(String[] args) {
		String str = null;
		System.out.println("Enter String :");
		Scanner scan = new Scanner(System.in);
		str = scan.nextLine();
		String string[];
		string = str.split(" ");
		int count=0;
		for(int i=0;i<string.length;i++){
			if(!(string[i].isEmpty()))
				count=count+1;
		}
		System.out.println("Number of words count :"+count);
		scan.close();
	}
}
