package com.comparable.example;

import java.util.TreeSet;

public class ComparableExample {
	public static void main(String args[]){
		TreeSet<Employee> set = new TreeSet<Employee>();
		Employee emp1 = new Employee(123,"Pratiksha",20000);
		Employee emp2 = new Employee(213,"Prarthana",30000);
		Employee emp3 = new Employee(321,"Pooja",40000);
		Employee emp4 = new Employee(231,"Vaishnavi",30000);
		set.add(emp1);
		set.add(emp2);
		set.add(emp3);
		set.add(emp4);
		System.out.println("********************Employees*********************");
		for (Employee employee : set) {
			System.out.println(" "+employee.toString());
		}
	}
}
class Employee implements Comparable<Object>{
	int id;
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	String name;
	double salary;
	public Employee(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	@Override
	public int compareTo(Object o) {
		Employee employee = (Employee)o;
		if(this.id > employee.id){
			return 1;
		}else if(this.id < employee.id){
			return -1;
		}else {
			return 0;
		}
	}
}
