package com.string.example;

public class StringEx {
	
	public static void main(String[] args) {
		
		
		String str = "IASYS";
		String str2 = "IASYS";
		
		/*if(str!=null)
			str = str+"123";*/
		
		System.out.println(""+str);
		System.out.println(str == str2);
		System.err.println(str.equals(str2));
		
		String str3 = new String("abc");
		String str4 = new String ("abc");
		
		
		System.out.println(""+str3 == str4);
		System.out.println(""+str3.equals(str4));

		
		System.out.println("str:"+str.hashCode()+",str2:"+str2.hashCode());
		System.out.println("str3:"+str3.hashCode()+",str4:"+str4.hashCode());
		
	}

}
