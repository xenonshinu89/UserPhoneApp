package com.anchil.userPhoneApp.api.Entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Phone {

	@Id
	private UUID phoneId;
	private String phoneName;
	private String phoneModel;
	private String phoneNumber;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JoinColumn(name="userId")
	 private User user;
	
}
