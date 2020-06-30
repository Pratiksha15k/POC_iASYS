package com.string.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SubString {
	public static void main(String args[]){
		String str = "abcbaa";
		Set<String> stringSet = new HashSet<>();
		for(int i=0; i<str.length(); i++){
			for(int j=i+1;j<=str.length(); j++){
				stringSet.add(str.substring(i, j));
			}
		}
		
		
		
		/* for(int c=0;c<str.length();c++)
	        {
	            for(int i=1;i<=str.length()-c;i++)
	            {
	            	stringSet.add(str.substring(c,c+i));
	            }
	        }
		*/
		
		Iterator<String> i=stringSet.iterator();  
		while(i.hasNext())  
		{  
			System.out.println(i.next());  
		}  
	}
}
