package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.dao.UserDAO;
import com.uxpsystems.assignment.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	public UserServiceImpl(UserDAO theUserDAO) {
		userDAO = theUserDAO;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional
	public User findById(long id) {
		return userDAO.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	@Transactional
	public String deleteById(long id) {
		userDAO.deleteById(id);
		return "Success";
	}

}
