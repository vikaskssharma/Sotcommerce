package com.sot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sot.ecommerce.entitities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByPostedById(int userId);

}
