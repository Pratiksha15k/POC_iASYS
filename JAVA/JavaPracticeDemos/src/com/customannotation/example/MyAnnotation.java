package com.customannotation.example;

public class MyAnnotation {
	@CustomAnnotation(name="Pratiksha", id=123, message = "")
	public void print(String name, int id){
		System.out.println("Name and Id :"+name+" "+id);
	}
	
	@CustomAnnotation(name=" ", id = 0, message = "Hello Pratiksha")
	public void printMessage(String message){
		System.out.println("Message :"+message);
	}
}
