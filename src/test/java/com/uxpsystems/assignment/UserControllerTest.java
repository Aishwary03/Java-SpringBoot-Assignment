package com.uxpsystems.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.controller.UserController;
import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class,secure=false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@WithMockUser("Admin")
	@Test
	public void testCreateUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(0);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		ObjectMapper mapper = new ObjectMapper();
		String inputInJson = mapper.writeValueAsString(mockUser);
		
		String url = "/api/users/";
		
		Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(mockUser);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON)
				).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@WithMockUser("Admin")
	@Test
	public void testGetAllUsers() throws Exception {
		
		Mockito.when(userService.findAll()).thenReturn(Collections.emptyList());
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users/")
				.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		Mockito.verify(userService).findAll();
	}
	
	@WithMockUser("Admin")
	@Test
	public void testGetUserByUserId() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(mockUser);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users/1")
				.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String expectedJson = mapper.writeValueAsString(mockUser);
		String outputJson = mvcResult.getResponse().getContentAsString();
		
		assertThat(expectedJson).isEqualTo(outputJson);
		
	}
	
	@WithMockUser("Admin")
	@Test
	public void testUpdateUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		ObjectMapper mapper = new ObjectMapper();
		String inputInJson = mapper.writeValueAsString(mockUser);
		
		String url = "/api/users/";
		
		Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(mockUser);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put(url)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON)
				).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@WithMockUser("Admin")
	@Test
	public void testDeleteUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setUsername("Test User");
		mockUser.setPassword("test password");
		mockUser.setStatus("Activated");
		
		Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(mockUser);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/users/1")
				).andReturn();
		 
		  // verify that service method was called once
		  Mockito.verify(userService).deleteById(Mockito.anyLong());;
		
	}
	
	
}
