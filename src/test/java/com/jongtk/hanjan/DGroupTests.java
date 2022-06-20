package com.jongtk.hanjan;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.jongtk.hanjan.entity.DrinkGroup;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.repository.DrinkGroupRepository;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional
@Log4j2
class DGroupTests {

	@Autowired DrinkGroupRepository drinkGroupRepository;
	@Autowired MemberRepository memberRepository;
	Member member1;
	Member member2;
	Member member3;
	
	//그룹 생성
	@Test
	void createGroup() {
		
		createMemberEach();
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		
		log.info("*****members: ");
		log.info(member1);
		log.info(member2);
		log.info(member3);
		
		DrinkGroup group1 = DrinkGroup.builder()
				.host(member1)
				.build();
		
		group1.addMember(member2);
		group1.addMember(member3);
		
		drinkGroupRepository.save(group1);
		
		log.info("*****group1 ");
		for(Member member : group1.getMembers()) {
			log.info(member.getName());
		}
	}
	
	
	
	//테스트 편의 메서드
	void createMemberEach() {
		member1 = Member.builder()
				.username("user1")
				.email("user1@gmail.com")
				.name("유저1")
				.build();
		
		member2 = Member.builder()
				.username("user2")
				.email("user2@gmail.com")
				.name("유저2")
				.build();
		
		member3 = Member.builder()
				.username("user3")
				.email("user3@gmail.com")
				.name("유저3")
				.build();
	}
	

}
