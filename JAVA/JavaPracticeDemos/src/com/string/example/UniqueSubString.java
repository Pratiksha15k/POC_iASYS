package com.string.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
 
public class UniqueSubString {
	public static void main(String... ar) {
		Set<String> s = new HashSet<String>();
		//printSubString("abcbaa", s);
		//generateSubstring("abcbaa");
		int s1 = numberdss("abcbaa");
	}
	
	static void printSubString(String s, Set<String> set) {
		if(!set.contains(s)) {
			if(s.length() == 1) {			
				System.out.println(s);
				set.add(s);
			} else {
				for(int i=0; i<(s.length()); i++) {
					if(i==0) {
						printSubString(s.substring(i+1, s.length()), set);
					} else if(i == (s.length()-1)) {
						printSubString(s.substring(0,i), set);
					} else {
						printSubString(s.substring(0,i) + s.substring(i+1, s.length()), set);
					}
				}
				System.out.println(s);
				set.add(s);
			}
		}
	}
	
	public static void generateSubstring(String s){
		Set<String> set = new HashSet<>();
		if(s == null || s.length() == 0){
			return;
		}		
		for(int i = 0; i < s.length(); i++){
			for(int j = 1; j <= s.length()-i; j++){
				set.add(s.substring(i, i+j));//System.out.println(s.substring(i, i+j));
			}
		}
		Iterator<String> i=set.iterator();  
		while(i.hasNext())  
		{  
			System.out.println(i.next());  
		} 
	}
	
	public static int numberdss(String str) {
		HashSet<String> all = new HashSet<>();
		HashSet<StringBuilder> last = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			for (StringBuilder sb : last) {
				sb.append(str.charAt(i));
				if (!all.contains(sb.toString())) {
					all.add(sb.toString());
				}
			}
			if (!all.contains(str.charAt(i) + "")) {
				all.add(str.charAt(i) + "");
			}
		}
		for (String string : all) {
			System.out.println(""+string.toString());
		}
		return all.size();
}
}