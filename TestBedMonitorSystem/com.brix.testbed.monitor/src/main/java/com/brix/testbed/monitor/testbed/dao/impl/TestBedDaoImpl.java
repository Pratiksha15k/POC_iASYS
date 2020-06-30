package com.brix.testbed.monitor.testbed.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.bean.User;
import com.brix.testbed.monitor.role.dao.RoleDaoException;
import com.brix.testbed.monitor.testbed.dao.TestBedDao;
import com.brix.testbed.monitor.testbed.dao.TestBedDaoException;

@Component
public class TestBedDaoImpl implements TestBedDao{
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(TestBed testBed) throws TestBedDaoException {
		Session session=null;
		Transaction transaction=null;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			session.save(testBed);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new TestBedDaoException(e.getMessage(),e.getCause());
		}
	}
	
	
	@Override
	public void update(TestBed testBed) throws TestBedDaoException {
		Session session=null;
		Transaction transaction=null;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			session.update(testBed);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new TestBedDaoException(e.getMessage(),e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestBed> getAll() throws TestBedDaoException {
		List<TestBed> testbeds=null;
		Session session=null;
		try {
			session=entityManager.unwrap(Session.class);
			testbeds= session.createQuery("from TestBed ORDER BY id DESC").list();
		} catch (Exception e) {
			throw new TestBedDaoException(e.getMessage(),e.getCause());
		}
		return testbeds;
	}

	
	@Override
	public boolean delete(long id) throws TestBedDaoException {
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			TestBed testBed=new TestBed();
			testBed.setId(id);
			flag = checkUsersExistsForTestbed(id);
			if(!flag)
				entityManager.remove(entityManager.contains(testBed)? testBed : entityManager.merge(testBed));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new TestBedDaoException(e.getMessage(),e.getCause());
		}
		return flag;
	}


	@Override
	public TestBed getTestBedDetailsById(long id) throws TestBedDaoException {
		Session session=null;
		Transaction transaction=null;
		TestBed testBed=null;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			testBed=new TestBed();
			testBed=session.get(TestBed.class, id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new TestBedDaoException(e.getMessage(),e.getCause());
		}
		return testBed;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean checkTestBedAlreadyExist(String testBedName) throws TestBedDaoException {
		Session session=null;
		Transaction transaction=null;
		boolean flag=false;
		try {
			session=entityManager.unwrap(Session.class);
			transaction=session.beginTransaction();
			List<User> list = session.createQuery("SELECT t from TestBed t where t.name = :name")
					.setParameter("name", testBedName).getResultList();
			if (!list.isEmpty()) {
				return true;
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new TestBedDaoException(e.getMessage(),e.getCause());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUsersExistsForTestbed(long id) throws TestBedDaoException {
		boolean flag = false;
		Session session = null;
		try {
			session = entityManager.unwrap(Session.class);
			List<User> list = session.createQuery("SELECT u FROM User u JOIN u.testbeds r WHERE r.id=:id")
					.setParameter( "id", id)
					.getResultList();
			if(!list.isEmpty()) {
				return true;
			}
		}catch(Exception e) {
			throw new TestBedDaoException(e.getMessage(), e.getCause());
		}
		return flag;
	}

}
