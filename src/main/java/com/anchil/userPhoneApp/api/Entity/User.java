package com.anchil.userPhoneApp.api.Entity;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	private UUID userId;
	private String userName;
	private String password;
	private String emailAddress;
	private String preferredPhoneNumber;
	private String role;
	
	

}
