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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anchil.userPhoneApp.api.Entity.Phone;
import com.anchil.userPhoneApp.api.Service.PhoneService;

@RestController

public class PhoneController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

	@Autowired
	PhoneService phoneService;
	
	@PostMapping("/addPhone")
	@ResponseStatus(HttpStatus.CREATED)
	public Phone createNewPhone(@RequestBody Phone phone) {
		// TODO Auto-generated method stub
		logger.debug("Inside add phone function of PhoneController");
		
		return phoneService.createNewPhone(phone);
	}

	@DeleteMapping("/deletePhone/{phoneId}")
	@ResponseStatus(HttpStatus.OK)
	public Boolean deletePhoneById(@PathVariable("phoneId") UUID phoneId) {
		// TODO Auto-generated method stub
		logger.debug("Inside delete phone function of PhoneController with phone id "+phoneId);
		
		return phoneService.deletePhoneById(phoneId);
	}

	@GetMapping("/getUserPhone/{userId}/phone")
	public List<Phone> getPhonesByUser(@PathVariable("userId") UUID userId) {
		// TODO Auto-generated method stub
		logger.debug("Inside get user phone function of PhoneController for user id "+userId);
		
		return phoneService.getPhonesByUser(userId);
	}
	
}
