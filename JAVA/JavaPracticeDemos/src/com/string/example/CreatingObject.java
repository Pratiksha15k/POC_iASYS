package com.string.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class CreatingObject {
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
		/*Class c = Class.forName("com.string.example.CreatingObject");
		CreatingObject object = (CreatingObject) c.newInstance();
		System.out.println("Object :"+object);*/
		
		
		InputStream inputStream = new InputStream() {
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		ObjectInputStream inStream = new ObjectInputStream(inputStream);
		CreatingObject object = (CreatingObject) inStream.readObject();
		System.out.println("Object :"+object);
	}
}
