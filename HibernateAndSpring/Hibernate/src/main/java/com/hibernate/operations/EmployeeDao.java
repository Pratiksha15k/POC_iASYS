package com.hibernate.operations;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class EmployeeDao {

	static Session session;
	static SessionFactory sessionFactory;
	public final static Logger logger = Logger.getLogger(EmployeeDao.class);

	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public static void createEmployee(String name, int salary) {
		Employee employee = null;
		try {
			employee = new Employee();
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			employee.setEmployeename(name);
			employee.setEmployeesalary(salary);
			session.save(employee);
			session.getTransaction().commit();
		}catch(Exception e) {
			logger.error(e.getMessage()+":"+e.getCause());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Employee> getEmployeeByName(String oldname) {
		try {
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Employee where empname = :name");
			query.setParameter("name", oldname); 
			if(query.list() == null || query.list().isEmpty()) { 
				session.getTransaction().commit(); 
				return null; 
			}else {
				return (ArrayList<Employee>)query.list();
			}
		}catch(Exception e) {
			logger.error(e.getMessage()+":"+e.getCause());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
	}

	public static boolean updateEmployeeById(int id, String name, int salary) {
		Employee employee = null;
		try {
			employee = new Employee();
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			employee =  (Employee)session.get(Employee.class, id); 
			if(employee == null){
				session.getTransaction().commit(); 
				return false; 
			}
			employee.setEmployeename(name);
			employee.setEmployeesalary(salary);
			session.getTransaction().commit();
		}catch(Exception e) {
			logger.error(e.getMessage()+":"+e.getCause());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return true;
	}

	public static boolean deleteEmployee(int id) {
		Employee employee = null;
		try {
			employee = new Employee();
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			employee = (Employee) session.get(Employee.class, id);
			if(employee == null)
				return false;
			session.delete(employee);
			session.getTransaction().commit();
		}catch(Exception e) {
			logger.error(e.getMessage()+":"+e.getCause());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return true;
	}

	public static Employee getEmployeeById(int id) {
		Employee employee = null;
		try {
			employee = new Employee();
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			employee = (Employee) session.get(Employee.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			logger.error(e.getMessage()+":"+e.getCause());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return employee;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Employee> getEmployees() {
		ArrayList<Employee> list  = new ArrayList<Employee>();
		try {
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			list = (ArrayList<Employee>) session.createQuery("from Employee").list();
			return list;
		}catch(Exception e) {
			logger.error(e.getMessage()+":"+e.getCause());
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
	}
}