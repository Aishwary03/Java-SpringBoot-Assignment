package com.uxpsystems.assignment.service;

import java.util.List;

import com.uxpsystems.assignment.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(long id);
	
	public User save(User user);
	
	public String deleteById(long id);

}
