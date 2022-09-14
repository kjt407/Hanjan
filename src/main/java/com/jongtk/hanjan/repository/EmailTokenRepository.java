package com.jongtk.hanjan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jongtk.hanjan.entity.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, Long>{
	
}
