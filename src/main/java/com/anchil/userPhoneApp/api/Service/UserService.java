package com.anchil.userPhoneApp.api.Service;

import java.util.List;
import java.util.UUID;

import com.anchil.userPhoneApp.api.Entity.User;

public interface UserService {
	
	User createUser(User user);
	
	Boolean deleteUserById(UUID id);
	
	List<User> getAllUsers();
	
	User updateUser(User user);

}
