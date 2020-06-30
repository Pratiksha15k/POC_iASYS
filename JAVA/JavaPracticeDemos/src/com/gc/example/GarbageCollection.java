package com.gc.example;

/*public class GarbageCollection {
	public static void main(String args[]) throws Throwable{
		Person person = new Person("Pratiksha", "Sanjay", "Datir");
		System.out.println("Before garbage collection :"+person.toString());
		//System.gc();
		person.finalize();
	}
	@Override
	public void finalize(){
		System.out.println("Garbage collector called");
		System.out.println("After Garbage collection :"+this);
	}
}*/
class GarbageCollection {
	String firstName, middleName, lastName; 
	public GarbageCollection(String firstName, String middleName, String lastName) throws Throwable{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	public String toString(){
		return firstName+" "+middleName+" "+lastName;
	}
	@Override
	public void finalize() {
		System.out.println("Garbage collector called");
		System.out.println("After Garbage collection :"+this);
	}
	public static void main(String args[]) throws Throwable{
		GarbageCollection person = new GarbageCollection("Pratiksha", "Sanjay", "Datir");
		System.out.println("Before garbage collection :"+person.toString());
		/*System.gc();*/
		/*System.out.println("After Garbage collection : called gc :"+person);*/
		person.finalize();
		/*System.out.println("After Garbage collection : called finalize :"+person);*/
	}
}