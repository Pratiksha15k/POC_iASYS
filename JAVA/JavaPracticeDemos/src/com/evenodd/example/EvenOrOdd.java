package com.evenodd.example;

import java.util.Scanner;

public class EvenOrOdd {

	public static void main(String[] args) {
		int number = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("\n Enter Number :");
		number = scan.nextInt();
		int quotient = number / 2;
		if(quotient*2 == number){
			System.out.println(number+" Number is even.");
		}else{
			System.out.println(number+" Number is odd.");
		}
		
		
		
		boolean flag=true;
		for(int i=0;i<number;i++){
			flag = !flag;
		}
		if(flag==true)
			System.out.println(number+" Number is even.");
		else
			System.out.println(number+" Number is odd.");
		scan.close();
	}

}
