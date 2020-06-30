package com.simpleprograms.example;

import java.util.Scanner;

public class ReverseString {
	public static void main(String[] args) {
		String str1 = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter String :");
		str1 = scan.nextLine();
		String str2 = "";
		for(int i=str1.length()-1;i>=0;i--){
			str2 = str2 + str1.charAt(i);
		}
		System.out.println("Reverse String :\n"+str2);
		scan.close();
	}
}
