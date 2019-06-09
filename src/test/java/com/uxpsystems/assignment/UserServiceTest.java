package com.uxpsystems.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.uxpsystems.assignment.dao.UserDAO;
import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserService;
import com.uxpsystems.assignment.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserServiceImpl.class,secure=false)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDAO userDAO;
	
	@WithMockUser("Admin")
	@Test
	public void testCreateUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(0);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		Mockito.when(userDAO.save(mockUser)).thenReturn(mockUser);
		
		assertThat(userService.save(mockUser)).isEqualTo(mockUser);
	}
	
	@WithMockUser("Admin")
	@Test
	public void testGetUserById() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		Mockito.when(userDAO.findById(1)).thenReturn(mockUser);
		
		assertThat(userService.findById(1)).isEqualTo(mockUser);
	}
	
	@WithMockUser("Admin")
	@Test
	public void testGetAllUsers() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		Mockito.when(userDAO.findById(1)).thenReturn(mockUser);
		mockUser.setStatus("Deactivated");
		
		Mockito.when(userDAO.save(mockUser)).thenReturn(mockUser);
		assertThat(userService.save(mockUser)).isEqualTo(mockUser);
	}
	
	@WithMockUser("Admin")
	@Test
	public void testUpdateUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(mockUser);
		
		Mockito.when(userDAO.findAll()).thenReturn(mockUsers);
		
		assertThat(userService.findAll()).isEqualTo(mockUsers);
	}
	
}
