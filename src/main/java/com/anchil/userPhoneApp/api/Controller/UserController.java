package com.anchil.userPhoneApp.api.Controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anchil.userPhoneApp.api.Entity.User;
import com.anchil.userPhoneApp.api.Service.UserService;


@RestController

public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		logger.debug("Inside add user function of UserController");
		
		return userService.createUser(user);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public Boolean deleteUserById(@PathVariable("userId") UUID id) {
		// TODO Auto-generated method stub
		logger.debug("Inside delete user function of UserController with user id "+id);
		
		return userService.deleteUserById(id);
	}

	@GetMapping("/getUser")
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		logger.debug("Inside get all user function of UserController");
		
		return userService.getAllUsers();
	}

	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		logger.debug("Inside update user preffered phone number function of UserController");
		
		return userService.updateUser(user);
	}
}
