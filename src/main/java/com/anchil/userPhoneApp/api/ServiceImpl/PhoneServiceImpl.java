package com.anchil.userPhoneApp.api.ServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.anchil.userPhoneApp.api.Entity.Phone;
import com.anchil.userPhoneApp.api.Exception.BadRequestException;
import com.anchil.userPhoneApp.api.Exception.ElementWithSameIDAlreadyExistsException;
import com.anchil.userPhoneApp.api.Repository.PhoneRepository;
import com.anchil.userPhoneApp.api.Service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);
	
	@Autowired
	PhoneRepository phoneRepo;

	@Override
	public Phone createNewPhone(Phone phone) {
		// TODO Auto-generated method stub
		logger.debug("Inside create phone function of PhoneServiceImpl");
		
		if (phone.getPhoneId()== null && phone.getUser()==null) {
		      throw new BadRequestException("The PhoneId and User Details must be provided when creating a new Phone");
		    }
		
		if(phoneRepo.findById(phone.getPhoneId()).isPresent()) {
			throw new ElementWithSameIDAlreadyExistsException("The PhoneId is already present");
		}
		
		return phoneRepo.save(phone);
	}

	@Override
	public Boolean deletePhoneById(UUID phoneId) {
		// TODO Auto-generated method stub
		logger.debug("Inside delete phone function of PhoneServiceImpl for phone id"+phoneId);
		
		Boolean isPhonePresent=false;
		isPhonePresent=phoneRepo.findById(phoneId).isPresent();
		if(isPhonePresent)
			phoneRepo.deleteById(phoneId);
		return isPhonePresent;
	}

	@Override
	public List<Phone> getPhonesByUser(UUID userId) {
		// TODO Auto-generated method stub
		logger.debug("Inside get phone numbers by user function of PhoneServiceImpl for user id "+userId);
		
		return phoneRepo.findAll().stream()
		        .filter((phone) -> userId.equals(phone.getUser().getUserId()))
		        .collect(Collectors.toList());
	}

}
