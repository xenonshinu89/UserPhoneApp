package com.anchil.userPhoneApp.api.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anchil.userPhoneApp.api.Entity.User;
import com.anchil.userPhoneApp.api.Exception.BadRequestException;
import com.anchil.userPhoneApp.api.Exception.ElementNotFoundException;
import com.anchil.userPhoneApp.api.Exception.ElementWithSameIDAlreadyExistsException;
import com.anchil.userPhoneApp.api.Repository.UserRepository;
import com.anchil.userPhoneApp.api.Service.PhoneService;
import com.anchil.userPhoneApp.api.Service.UserService;


@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		logger.debug("Inside create user function of UserServiceImpl");
		
		if (user.getUserId()== null && user.getPassword()== null) {
		      throw new BadRequestException("The UserID and password must be provided when creating a new User");
		    }
		
		if(userRepo.findById(user.getUserId()).isPresent()){
			throw new ElementWithSameIDAlreadyExistsException("The UserID is already present");
		}
		
		String pwd=user.getPassword();
		String encryptPwd=passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		
		
		return userRepo.save(user);
	}

	@Override
	public Boolean deleteUserById(UUID id) {
		// TODO Auto-generated method stub
		logger.debug("Inside delete user function of UserServiceImpl by user id "+id);
		
		Boolean isUserPresent=false;
		isUserPresent=userRepo.findById(id).isPresent();
		if(isUserPresent)
			userRepo.deleteById(id);
		
		return isUserPresent;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		logger.debug("Inside get all user function of UserServiceImpl");
		
		return userRepo.findAll();
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		logger.debug("Inside update user preffered phone number function of UserServiceImpl");
		
		userRepo.findById(user.getUserId()).ifPresentOrElse(oUser->{
			oUser.setPreferredPhoneNumber(user.getPreferredPhoneNumber());
			userRepo.save(oUser);
			},()->new ElementNotFoundException("User Not Found to update"));
		
		return userRepo.findById(user.getUserId()).orElseThrow(()->new ElementNotFoundException("User Not Found to update"));
	
	}
	
	

}
