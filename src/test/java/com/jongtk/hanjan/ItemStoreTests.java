package com.jongtk.hanjan;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberGroup;
import com.jongtk.hanjan.entity.test.Item;
import com.jongtk.hanjan.entity.test.Store;
import com.jongtk.hanjan.repository.GroupRepository;
import com.jongtk.hanjan.repository.MemberGroupRepository;
import com.jongtk.hanjan.repository.MemberRepository;
import com.jongtk.hanjan.repository.test.ItemRepository;
import com.jongtk.hanjan.repository.test.StoreRepository;
import com.jongtk.hanjan.service.GroupServiceImp;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ItemStoreTests {

	@Autowired ItemRepository itemRepository;
	@Autowired StoreRepository storeRepository;
	
	@Test 
	@Transactional @Rollback(false)
	public void 상품입고() throws Exception{
		
		/*
		 * 엔티티 생성
		 */
		Item note = Item.builder()
				.name("노트")
				.price(1500)
				.build();
		
		Item pencil = Item.builder()
				.name("연필")
				.price(500)
				.build();
		
		Store storeA = Store.builder()
				.name("문구점")
				.build();
		
//		storeRepository.save(storeA);
		
		/*
		 * 문구점에 상품 추가
		 */
		storeA.add(note);
		storeA.add(pencil);
		
		/*
		 * 추가한 상품들 영속화 
		 */
		storeRepository.save(storeA);
		itemRepository.save(note);
		itemRepository.save(pencil);
	}
	
}
