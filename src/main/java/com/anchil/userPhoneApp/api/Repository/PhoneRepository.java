package com.anchil.userPhoneApp.api.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchil.userPhoneApp.api.Entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone,UUID>{

}
