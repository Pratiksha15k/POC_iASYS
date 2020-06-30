package com.string.example;

import java.util.Scanner;

public class Pascal {
	public static void main(String args[]){/*
	    int no_row,c=1,blk,i,j;
	    System.out.print("Input number of rows: ");
	    Scanner in = new Scanner(System.in);
			    no_row = in.nextInt();
	    for(i=0;i<no_row;i++)
	    {
	        for(blk=1;blk<=no_row-i;blk++)
	        System.out.print(" ");
	        for(j=0;j<=i;j++)
	        {
	            if (j==0||i==0)
	                c=1;
	            else
	               c=c*(i-j+1)/j;
	            System.out.print(" "+c);
	        }
	        System.out.print("\n");
	    }
	*/
		
	
		int r, i, k, number=1, j;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter Number of Rows : ");
		r = scan.nextInt();
		scan.close();
		for(i=0;i<r;i++)
		{
			for(k=r; k>i; k--)
			{
				System.out.print(" ");
			}
            number = 1;
			for(j=0;j<=i;j++)
			{
				 System.out.print(number+ " ");
                 number = number * (i - j) / (j + 1);
			}
			System.out.println();
		}
	
	}
}
