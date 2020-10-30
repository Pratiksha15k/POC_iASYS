package com.hibernate.operations;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class HibernateOperation{
	public static void main( String[] args ){
		while(true) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println( "\nSelect any operation from menu:\n"
					+ "1. Add Employee\n"
					+ "2. Update Employee\n"
					+ "3. Delete Employee\n"
					+ "4. Search Employee\n"
					+ "5. Get Employees\n"
					+ "6. Exit" );
			
			
			if(!scanner.hasNextInt() || !scanner.hasNextFloat() || !scanner.hasNextDouble()) {
				System.out.println("Choice must be a number.");
				System.out.println("\n-----------------------------------------------------------------");
				continue;
			}
			
			
			int choice = scanner.nextInt();
			//Scanner.close();
			
			switch(choice) {
			
			case 1:{
				System.out.println("Enter Employee Name");
				String name = scanner.next();
				System.out.println("Enter Employee Salary");
				int salary = scanner.nextInt();
				EmployeeDao.createEmployee(name,salary);
				//	scanner.close();
				System.out.println("Employee created successfully.");
				System.out.println("---------------------------------------------------------------------");
				break;
			}
			
			
			case 2:{
				System.out.println("Select Choice\n"
						+ "1. Update by name\n"
						+ "2. Update by ID");
				
				if(!scanner.hasNextInt() || !scanner.hasNextFloat() || !scanner.hasNextDouble()) {
					System.out.println("Choice must be a number.");
					System.out.println("\n-----------------------------------------------------------------");
					continue;
				}
				
				int choice1 = scanner.nextInt();
				String name = null, oldname =null;
				int id = 0,salary = 0;
				
				if(choice1 == 1) {
					System.out.println("Enter Employee Name of employee which you want to update?");
					if(!scanner.hasNextLine()) {
						System.out.println("Employee name must be string.");
						break;
					}
					oldname = scanner.next();
					ArrayList<Employee> list = EmployeeDao.getEmployeeByName(oldname);
					if(list!=null && !list.isEmpty()) {
						System.out.format("\n%10s        %10s       %10s", "Employee ID", "Employee Name", "Employee Salary");
						for (Employee employee : list) {
							System.out.println("\n-----------------------------------------------------------------");
							System.out.format("%10d          %10s       %10d\n", employee.getEmployeeid(), 
									employee.getEmployeename(), employee.getEmployeesalary());
						}
						System.out.println("\nEnter Employee ID of employee which you want to update from above table?");
						if(!scanner.hasNextInt() || !scanner.hasNextFloat() || !scanner.hasNextDouble()) {
							System.out.println("Employee ID must be a number.");
							break;
						}
						id = scanner.nextInt();
						System.out.println("Enter Employee Name");
						name = scanner.next();
						System.out.println("Enter Employee Salary");
						salary = scanner.nextInt();
						boolean flag = EmployeeDao.updateEmployeeById(id,name,salary);
						if(flag) {
							System.out.println("Employee updated successfully.");
						}else {
							System.out.println("Employee with given ID not exist.");
						}
					}else {
						System.out.println("Employee with given name not exist.");
					}
				}else if(choice1 == 2) {
					System.out.println("Enter Employee ID of employee which you want to update?");
					if(!scanner.hasNextInt() || !scanner.hasNextFloat() || !scanner.hasNextDouble()) {
						System.out.println("Employee ID must be a number.");
						break;
					}
					id = scanner.nextInt();
					System.out.println("Enter Employee Name");
					name = scanner.next();
					System.out.println("Enter Employee Salary");
					salary = scanner.nextInt();
					boolean flag = EmployeeDao.updateEmployeeById(id,name,salary);
					if(flag) {
						System.out.println("Employee updated successfully.");
					}else {
						System.out.println("Employee with given ID not exist.");
					}
				}else {
					System.out.println("Please enter valid choice.");
				}
				System.out.println("---------------------------------------------------------------------");
				break;
			}
			
			
			case 3:{
				System.out.println("Enter Employee ID of employee which you want to delete?");
				int id = scanner.nextInt();
				boolean flag = EmployeeDao.deleteEmployee(id);
				if(flag)
					System.out.println("Employee deleted successfully.");
				else
					System.out.println("Employee with given ID not exist.");
				System.out.println("---------------------------------------------------------------------");
				break;
			}
			
			
			case 4:{
				System.out.println("Select Choice\n"
						+ "1. Search by name\n"
						+ "2. Search by ID");
				
				if(!scanner.hasNextInt() || !scanner.hasNextFloat() || !scanner.hasNextDouble()) {
					System.out.println("Choice must be a number.");
					System.out.println("\n-----------------------------------------------------------------");
					continue;
				}
				
				int choice1 = scanner.nextInt();
				if(choice1==1) {
					System.out.println("Enter name of employee which you want to search?");
					String searchname = scanner.next();
					ArrayList<Employee> list = null;
					list = EmployeeDao.getEmployeeByName(searchname);
					if(list!=null && !list.isEmpty()) {
						System.out.format("\n%10s        %10s       %10s", "Employee ID", "Employee Name", "Employee Salary");
						for (Employee employee : list) {
							System.out.println("\n-----------------------------------------------------------------");
							System.out.format("%10d         %10s       %10d \n", employee.getEmployeeid(), 
									employee.getEmployeename(), employee.getEmployeesalary());
						}
					}else {
						System.out.println("Employee with given name not exist.");
						System.out.println("\n-----------------------------------------------------------------");
					}
				}else if(choice1==2) {
					System.out.println("Enter ID of employee which you want to search?");
					if(!scanner.hasNextInt() || !scanner.hasNextFloat() || !scanner.hasNextDouble()) {
						System.out.println("Employee ID must be a number.");
						System.out.println("\n-----------------------------------------------------------------");
						break;
					}
					int searchid = scanner.nextInt();
					Employee employee = null;
					employee = EmployeeDao.getEmployeeById(searchid);
					if(employee == null)
						System.out.println("Employee with given ID not exist.");
					else {
						System.out.format("\n%10s       %10s       %10s", "Employee ID", "Employee Name", "Employee Salary");
						System.out.println("\n-----------------------------------------------------------------");
						System.out.format("%10d          %10s       %10d \n", employee.getEmployeeid(), 
								employee.getEmployeename(), employee.getEmployeesalary());
					}
				}else {
					System.out.println("Please enter valid choice.");
				}
				System.out.println("\n-----------------------------------------------------------------");
				break;
			}
			
			
			case 5:{
				ArrayList<Employee> list = EmployeeDao.getEmployees();
				if(list!=null && !list.isEmpty()) {
					System.out.format("\n%10s       %10s       %10s", "Employee ID", "Employee Name", "Employee Salary");
					for(Employee employee:list) {
						System.out.println("\n-----------------------------------------------------------------");
						System.out.format("%10d          %10s       %10d \n", employee.getEmployeeid(), 
								employee.getEmployeename(), employee.getEmployeesalary());
					}
				}else {
					System.out.println("There is no record found.");
					System.out.println("\n-----------------------------------------------------------------");
				}
				break;
			}
			
			
			case 6:{
				System.exit(200);
				break;
			}
			
			
			default:{
				System.out.println("Please enter valid choice.");
				System.out.println("\n-----------------------------------------------------------------");
				continue;
			}
			
			
			}
//			scanner.close();
		}
	}
}
