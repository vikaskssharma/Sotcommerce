package com.sot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sot.ecommerce.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByUserName(String userName);

	User findByUserNameAndPassword(String userName, String password);

}
