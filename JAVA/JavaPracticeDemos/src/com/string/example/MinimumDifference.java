package com.string.example;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumDifference {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Array Length :");
		int length = scan.nextInt();
		int array[] = new int[length];
		System.out.println("Enter Array Elements :");
		for(int count=0; count<array.length; count++){
			array[count] = scan.nextInt();
		}
		scan.close();
		int minimumDiff = minimumDifference(array);
		System.out.println("Minimum Difference :"+minimumDiff);
	}
	
	public static int minimumDifference(int array[]){
		Arrays.sort(array);
		int minDiff = array[1]-array[0];
		int difference = 0;
		for (int i = 2 ; i != array.length ; i++) {
			difference = array[i]-array[i-1];
		    if(difference < minDiff)
		    	minDiff = difference;
		}
		return minDiff;
	}
}
