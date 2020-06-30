package com.brix.testbed.monitor.user.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.brix.testbed.monitor.bean.Role;
import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.bean.User;
import com.brix.testbed.monitor.user.dao.UserDao;
import com.brix.testbed.monitor.user.dao.UserDaoException;

@Component
public class UserDaoImpl implements UserDao, ApplicationListener<ContextRefreshedEvent>{
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(User user) throws UserDaoException {
		Session session=null;
		Transaction transaction=null;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e.getMessage(),e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() throws UserDaoException {
		List<User> user=null;
		Session session=null;
		try {
			session=entityManager.unwrap(Session.class);
			user= session.createQuery("from User ORDER BY id DESC").list();
		} catch (Exception e) {
			throw new UserDaoException(e.getMessage(),e.getCause());
		}
		return user;
	}

	@Override
	public void update(User user) throws UserDaoException {
		Session session=null;
		Transaction transaction=null;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public boolean delete(long id, String username) throws UserDaoException {
		Session session=null;
		Transaction transaction=null;
		boolean isLoggedInUser = false;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			User user=new User();
			user.setId(id);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userdetails = (UserDetails) auth.getPrincipal();
			String userName = userdetails.getUsername();
			if(userName.equalsIgnoreCase(username)) {
				isLoggedInUser = true;
			}else {
				entityManager.remove(entityManager.contains(user)? user : entityManager.merge(user));
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e.getMessage(),e.getCause());
		}
		return isLoggedInUser;
	}

	@Override
	public User getUserDetailsById(long id) throws UserDaoException {
		Session session=null;
		Transaction transaction=null;
		User user=null;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			user=new User();			
			user=session.get(User.class, id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e.getMessage(),e.getCause());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUserAlreadyExist(String userName) throws UserDaoException {
		Session session = null;
		boolean flag = false;
		try {
			session = entityManager.unwrap(Session.class);
			List<User> list = session.createQuery("SELECT u from User u where u.userName = :userName")
					.setParameter("userName", userName).getResultList();
			if (!list.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			throw new UserDaoException(e.getMessage(), e.getCause());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String name) throws UserDaoException {
		Session session = null;
		User user=null;
		try {
			session = entityManager.unwrap(Session.class);
			List<User> list = session.createQuery("SELECT u from User u where u.userName = :userName")
					.setParameter("userName", name).getResultList();
			if (!list.isEmpty()) {
				user=list.get(0); 
			}
		} catch (Exception e) {
			throw new UserDaoException(e.getMessage(), e.getCause());
		}
		return user;
	}

	
	@Override
	public List<TestBed> getTestBedByUser(String username) throws UserDaoException {
		List<TestBed> list = null;
		try {
			User user = getUserByUsername(username);
			if(user != null) {
				list = user.getTestbeds();
			}
		}catch(Exception e) {
			throw new UserDaoException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void createSuperUser() throws UserDaoException {
		Session session=null;
		Transaction transaction=null;
		User user = new User();
		try {
			session=entityManager.unwrap(Session.class);
			List<Role> rolesList = session.createQuery("from Role ORDER BY id DESC").list();
			List<TestBed> testbedList = session.createQuery("from TestBed ORDER BY id DESC").list();
			Set<Role> roles = new HashSet<Role>(rolesList);
			user.setName("superuser");
			user.setPassword("secret");
			user.setRoles(roles);
			user.setTestbeds(testbedList);
			transaction=session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			//createSuperUser();
		}catch(Exception e) {
			System.out.println("Exception:"+e.getMessage()+":"+e.getCause());
		}
	}
}
