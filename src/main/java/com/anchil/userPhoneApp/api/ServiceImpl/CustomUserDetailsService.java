package com.anchil.userPhoneApp.api.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anchil.userPhoneApp.api.Entity.User;
import com.anchil.userPhoneApp.api.Repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.debug("Loading users by username inside CustomUserDetailsService");
		
		User user=userRepo.findByUserName(username);
		CustomUserDetails userDetails=null;
		if(user!=null) {
			userDetails=new CustomUserDetails();
			userDetails.setUser(user);
		}else {
			throw new UsernameNotFoundException("User Not present with user"+username);
		}
		
		return userDetails;
	}

}
