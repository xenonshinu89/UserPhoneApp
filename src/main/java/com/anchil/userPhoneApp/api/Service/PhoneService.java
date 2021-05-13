package com.anchil.userPhoneApp.api.Service;

import java.util.List;
import java.util.UUID;

import com.anchil.userPhoneApp.api.Entity.Phone;

public interface PhoneService {
	
	Phone createNewPhone(Phone phone);
	
	Boolean deletePhoneById(UUID phoneId);
	
	List<Phone> getPhonesByUser(UUID userId);
	
	

}
