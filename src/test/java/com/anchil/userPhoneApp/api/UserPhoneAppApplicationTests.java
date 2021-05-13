package com.anchil.userPhoneApp.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.anchil.userPhoneApp.api.Entity.Phone;
import com.anchil.userPhoneApp.api.Entity.User;
import com.anchil.userPhoneApp.api.Repository.UserRepository;
import com.anchil.userPhoneApp.api.Repository.PhoneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserPhoneAppApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private ObjectMapper mapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PhoneRepository phoneRepo;

	@Test
	@Order(1)
	void addUser() throws Exception{
		
		
		User testUser=new User(UUID.fromString("93f3ed0a-92bd-4c82-ba0e-c098111cef59"),"test_user","12345","test@example.com","+353881234567","USER");
		
		mockMvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON)
										.content(mapper.writeValueAsString(testUser))).andDo(print()).andExpect(status().is2xxSuccessful())
		  .andExpect(content().string(containsString("userId")));
		
		assertThat(
				userRepo
		            .findById(UUID.fromString("93f3ed0a-92bd-4c82-ba0e-c098111cef59"))
		            .orElseThrow(
		                () -> new IllegalStateException("New User has not been saved in the repository")).getUserName(),
		        equalTo(testUser.getUserName()));
		
	}
	
	@Test
	@WithMockUser(value = "test_user")
	@Order(2)
	void addPhone() throws Exception{
		
		User testUser=new User(UUID.fromString("93f3ed0a-92bd-4c82-ba0e-c098111cef59"),"test_user","12345","test@example.com","+353881234567","USER");
		Phone testPhone1=new Phone(UUID.fromString("2a810635-18d0-40ce-ada6-0c0fd1181225"),"Test User's Pixel","ANDROID","+353881234567",null);
		Phone testPhone2=new Phone(UUID.fromString("2a810635-18d0-40ce-ada6-0c0fd1181235"),"Test User's Pixel","ANDROID","+353881234577",null);

		
		testPhone1.setUser(testUser);
		testPhone2.setUser(testUser);
		
		mockMvc.perform(post("/addPhone").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(testPhone1))).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("phoneId")));
		
		mockMvc.perform(post("/addPhone").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(testPhone2))).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("phoneId")));
		
		assertThat(
				phoneRepo
		            .findById(UUID.fromString("2a810635-18d0-40ce-ada6-0c0fd1181225"))
		            .orElseThrow(
		                () -> new IllegalStateException("New Phone has not been saved in the repository")).getPhoneNumber(),
		        equalTo(testPhone1.getPhoneNumber()));
		
		assertThat(
				phoneRepo
		            .findById(UUID.fromString("2a810635-18d0-40ce-ada6-0c0fd1181235"))
		            .orElseThrow(
		                () -> new IllegalStateException("New Phone has not been saved in the repository")).getPhoneNumber(),
		        equalTo(testPhone2.getPhoneNumber()));
		
	}
	
	@Test
	@WithMockUser(value = "test_user")
	@Order(3)
	void listPhoneByUsers() throws Exception{
		
		mockMvc.perform(get("/getUserPhone/93f3ed0a-92bd-4c82-ba0e-c098111cef59/phone")).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("2a810635-18d0-40ce-ada6-0c0fd1181225")));
		
	}
	
	@Test
	@WithMockUser(value = "test_user")
	@Order(4)
	void listUsers() throws Exception{
		
		mockMvc.perform(get("/getUser")).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("93f3ed0a-92bd-4c82-ba0e-c098111cef59")));
		
	}
	
	@Test
	@WithMockUser(value = "test_user")
	@Order(5)
	void updateUserPreferredPhoneNumber() throws Exception{
		
		User testUser=new User(UUID.fromString("93f3ed0a-92bd-4c82-ba0e-c098111cef59"),"test_user","12345","test@example.com","+353881234577","USER");
		
		mockMvc.perform(put("/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(testUser))).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("+353881234577")));
		
		assertThat(
				userRepo
		            .findById(UUID.fromString("93f3ed0a-92bd-4c82-ba0e-c098111cef59"))
		            .orElseThrow(
		                () -> new IllegalStateException("User has not been found in the repository")).getPreferredPhoneNumber(),
		        equalTo(testUser.getPreferredPhoneNumber()));
		
	}
	
	

	@Test
	@WithMockUser(value = "test_user")
	@Order(6)
	void deletePhoneById() throws Exception{
		
		mockMvc.perform(delete("/deletePhone/2a810635-18d0-40ce-ada6-0c0fd1181225")).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("true")));
		
		mockMvc.perform(get("/getUserPhone/93f3ed0a-92bd-4c82-ba0e-c098111cef59/phone")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andExpect(content().string(not(containsString("2a810635-18d0-40ce-ada6-0c0fd1181225"))));
		
		mockMvc.perform(delete("/deletePhone/2a810635-18d0-40ce-ada6-0c0fd1181235")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andExpect(content().string(containsString("true")));

		mockMvc.perform(get("/getUserPhone/93f3ed0a-92bd-4c82-ba0e-c098111cef59/phone")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andExpect(content().string(not(containsString("2a810635-18d0-40ce-ada6-0c0fd1181235"))));
		
	}
	
	
	@Test
	@WithMockUser(value = "test_user")
	@Order(7)
	void deleteUserById() throws Exception{
		
		mockMvc.perform(delete("/deleteUser/93f3ed0a-92bd-4c82-ba0e-c098111cef59")).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("true")));
		
		mockMvc.perform(get("/getUser")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andExpect(content().string(not(containsString("93f3ed0a-92bd-4c82-ba0e-c098111cef59"))));
		
	}
	
}
