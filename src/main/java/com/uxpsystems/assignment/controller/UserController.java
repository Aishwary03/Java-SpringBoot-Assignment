package com.uxpsystems.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private UserService userService;
	
	@Autowired
	public UserController(UserService theUserService)
	{
		userService = theUserService;
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<User> findAll() {
		LOGGER.info(" GET method called to fetch all the users ");
		return userService.findAll();
	}
	
	@RequestMapping(value="/users/{userId}",method=RequestMethod.GET)
	public User getUser(@PathVariable long userId){
		LOGGER.info(" GET method called to fetch the user with Id " + userId);
		User user = userService.findById(userId);
		
		if(user == null) {
			LOGGER.info(" User does not exist in the database ");
			throw new RuntimeException("User Id not Found");
		}
		LOGGER.info(" User Exists ");
		return user;
	}
	
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		//Setting id as zero as it is auto increment in database
		user.setId(0);
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		LOGGER.info(" Saving the user ");
		userService.save(user);
		return user;
	}
	
	@RequestMapping(value="/users",method=RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		LOGGER.info(" Updating user ");
		userService.save(user);
		return user;
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable long userId) {
		
		User user = userService.findById(userId);
		
		if(user == null) {
			LOGGER.info(" User Does not Exists ");
			throw new RuntimeException("User Id not found");
		}
		LOGGER.info(" User Exists ");
		userService.deleteById(userId);
		
		return "User Deleted Successfully";
	}
	
	
	
}
