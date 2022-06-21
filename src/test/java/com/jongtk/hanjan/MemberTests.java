package com.jongtk.hanjan;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jongtk.hanjan.repository.MemberRepository;
import com.jongtk.hanjan.service.MemberService;

@SpringBootTest
@Transactional
class MemberTests {

	@Autowired MemberService MemberService;
	@Autowired MemberRepository memberRepository;
	
	//내 그룹 찾기
	@Test
	void findMyGroup() {
		
	}

}
