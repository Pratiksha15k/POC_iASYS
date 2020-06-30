package com.hashmap.exmaple;

import java.util.HashMap;
import java.util.Map.Entry;

public class IterateHashMapUsingWhile {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<10;i++){
			map.put("key"+i, i);
		}
		for(Entry<String, Integer> entry : map.entrySet()){
			System.out.println(""+entry.getKey()+" : "+entry.getValue());
		}
	} 

}
