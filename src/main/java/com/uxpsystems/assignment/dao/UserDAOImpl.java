package com.uxpsystems.assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public UserDAOImpl(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}

	@Override
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public User findById(long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User  user = currentSession.get(User.class, id);
		return user;
	}

	@Override
	public User save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
		return user;
	}

	@Override
	public String deleteById(long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from User where id=:userId");
		query.setParameter("userId", id);
		query.executeUpdate();
		return "Success";
	}

}
