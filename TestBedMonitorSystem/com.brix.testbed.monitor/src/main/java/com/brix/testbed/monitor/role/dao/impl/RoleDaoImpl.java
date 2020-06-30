package com.brix.testbed.monitor.role.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import com.brix.testbed.monitor.bean.Role;
import com.brix.testbed.monitor.bean.User;
import com.brix.testbed.monitor.role.dao.RoleDao;
import com.brix.testbed.monitor.role.dao.RoleDaoException;

@Component
public class RoleDaoImpl implements RoleDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void create(Role role) throws RoleDaoException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = entityManager.unwrap(Session.class);
			transaction = session.beginTransaction();
			session.save(role);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			throw new RoleDaoException(e.getMessage(),e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() throws RoleDaoException {
		Session session = null;
		List<Role> roles = null;
		try {
			session = entityManager.unwrap(Session.class);
			roles = session.createQuery("from Role ORDER BY id DESC").list();
		}catch(Exception e) {
			throw new RoleDaoException(e.getMessage(), e.getCause());
		}
		return roles;
	}

	@Override
	public void update(Role role) throws RoleDaoException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = entityManager.unwrap(Session.class);
			transaction = session.beginTransaction();
			session.update(role);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			throw new RoleDaoException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean delete(long id) throws RoleDaoException {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = entityManager.unwrap(Session.class);
			transaction = session.beginTransaction();
			Role role = new Role();
			role.setId(id);
			flag = checkUsersExistsForRole(id);
			if(!flag)
				entityManager.remove(entityManager.contains(role)? role : entityManager.merge(role));
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			throw new RoleDaoException(e.getMessage(), e.getCause());
		}
		return flag;
	}

	@Override
	public Role getRoleDetailsById(long id) throws RoleDaoException {
		Session session = null;
		Transaction transaction = null;
		Role role = null;
		try {
			session = entityManager.unwrap(Session.class);
			transaction = session.beginTransaction();
			role = session.get(Role.class, id);
			transaction.commit();
		}catch(Exception e) {
			transaction.commit();
			throw new RoleDaoException(e.getMessage(), e.getCause());
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkRoleAlreadyExist(String RoleName) throws RoleDaoException {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = entityManager.unwrap(Session.class);
			transaction = session.beginTransaction();
			List<Role> list = session.createQuery("SELECT r from Role r where r.name = :name").
				setParameter("name", RoleName).getResultList();
			if (!list.isEmpty()) {
				return true;
			}
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			throw new RoleDaoException(e.getMessage(), e.getCause());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUsersExistsForRole(long id) throws RoleDaoException {
		boolean flag = false;
		Session session = null;
		try {
			session = entityManager.unwrap(Session.class);
			List<User> list = session.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.id=:id")
					.setParameter( "id", id)
					.getResultList();
			if(!list.isEmpty()) {
				return true;
			}
		}catch(Exception e) {
			throw new RoleDaoException(e.getMessage(), e.getCause());
		}
		return flag;
	}
	
	
}
