package com.circularprime.example;
import java.util.Scanner;

public class CircularPrime {
	@SuppressWarnings("resource")
	public static void main(String args[]){
		int number,remainder,reversed = 0;
		System.out.println("Enter number :");
		Scanner scan = new Scanner(System.in);
		number = scan.nextInt(); 
		if(number < 1000 && number >= 11){
			boolean result = isItPrimeNumber(number);
			if(result == true){
				 while(number != 0) {
					 remainder = number % 10;
			         reversed = reversed * 10 + remainder;
			         number /= 10;
			        } 
				 result = isItPrimeNumber(reversed);
			}else{
				System.out.println(number + " " + "Number is not circular prime number");
			}
		}else{
			System.out.println("Please enter number having more than 1 digits.");
		}
	}
	public static boolean isItPrimeNumber(int number){
		boolean flag = false;
		if(number == 1 || number == 0){
			return false;
		}else{
			for(int counter = 2; counter < number ; counter++){
				if(number%counter == 0){
					flag = true;
				}
			}
		}
		if(!flag){
			return true;//number is prime number
		}else{
			return false;//number is not prime number
		}
	}
}

