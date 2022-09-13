package com.jongtk.hanjan.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jongtk.hanjan.entity.test.Item;


public interface ItemRepository extends JpaRepository<Item, Long>{
	
}
