package com.anchil.userPhoneApp.api.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchil.userPhoneApp.api.Entity.User;

public interface UserRepository extends JpaRepository<User,UUID>{

	User findByUserName(String username);
}
