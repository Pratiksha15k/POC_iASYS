package com.hashmap.exmaple;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class HashMapExample {
	public static void main(String args[]){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("val1", "value1");
		map.put("val2", "value2");
		map.put("val3", "value3");
		map.put("val4", "value4");
		map.put("val5", "value5");
		
		Map<String, Object> map1 = new TreeMap<String, Object>();
		map1.put("val1", "value1");
		map1.put("val2", "value2");
		map1.put("val3", "value3");
		map1.put("val4", "value4");
		map1.put("val5", "value5");
		//System.out.println(""+map.get(null));
		
		//Hashtable <String,Object> hashtable = new Hashtable<>();
		//hashtable.put(null, "213");//hashtable does not allow null key because hashtable generate hashcode for each key and while producing 
		// hashcode for null key it will throw null pointer exception. To recover this hashmap is introduced.
		//System.out.println("HashTable :"+hashtable.get(null));
		
		System.out.println("Object Hashcode :"+map.hashCode());
		
		SortedMap<String, Object> sortedMap = new TreeMap<>() /*map.s*/;
		sortedMap.put("val1", "value1");
		sortedMap.put("val2", "value2");
		sortedMap.put("val3", "value3");
		sortedMap.put("val4", "value4");
		sortedMap.put("val5", "value5");
		sortedMap.put("val6", "value6");
		Set s = sortedMap.entrySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
			Map.Entry<String, Object> sort = (Entry<String, Object>)i.next();
			System.out.println("Key :"+sort.getKey());
			System.out.println("Value :"+sort.getValue());
		}
	}
}
