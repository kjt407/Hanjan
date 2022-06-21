package com.jongtk.hanjan;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		
		log.info("*****members: ");
		log.info(member1);
		log.info(member2);
		log.info(member3);
		
		DrinkGroup group1 = DrinkGroup.builder()
				.host(member1)
				.build();
		
//		group1.addMember(member2);

		
		drinkGroupRepository.save(group1);
		
		log.info("*****group1 ");
		for(Member member : group1.getMembers()) {
			log.info(member.getName());
		}
	}
	
	//구성원 중복추가
	@Test
	public void addDupplicateMember(){
		
		//Given
		createMemberEach();
		
		log.info("*****members: ");
		log.info(member1);
		log.info(member2);
		log.info(member3);
		
		DrinkGroup group1 = DrinkGroup.builder()
				.host(member1)
				.build();
				
		//When
		assertThatThrownBy(()->{
			group1.addMember(member2);
			group1.addMember(member2);
		}).isInstanceOf(IllegalStateException.class).hasMessageContaining("이미 구성원 입니다.");

		
		//Then
		
	}
	
	//내가 속한 그룹 찾기
	@Test
	public void findMyGroup() throws Exception{
		
		//Given
		createMemberEach();
		
		log.info("*****members: ");
		log.info(member1);
		log.info(member2);
		log.info(member3);
		
//		DrinkGroup group1 = DrinkGroup.builder()
//				.host(member1)
//				.title("그룹1")
//				.build();
//		
//		DrinkGroup group2 = DrinkGroup.builder()
//				.host(member1)
//				.title("그룹2")
//				.build();
//		
//		DrinkGroup group3 = DrinkGroup.builder()
//				.host(member1)
//				.title("그룹3")
//				.build();
//		
//		DrinkGroup group4 = DrinkGroup.builder()
//				.host(member2)
//				.title("그룹2")
//				.build();
		
		DrinkGroup group1 = new DrinkGroup();
		group1.host(member1,"그룹1");
		
		DrinkGroup group2 = new DrinkGroup();
		group2.host(member1,"그룹2");
		
		DrinkGroup group3 = new DrinkGroup();
		group3.host(member1,"그룹3");
		
		DrinkGroup group4 = new DrinkGroup();
		group4.host(member2,"그룹4");
		
		
		group4.addMember(member1);
	
		drinkGroupRepository.save(group1);
		drinkGroupRepository.save(group2);
		drinkGroupRepository.save(group3);
		drinkGroupRepository.save(group4);
		
		Member targetMember = memberRepository.findById(member1.getId()).get();
		
		List<DrinkGroup> hosted = targetMember.getHosted();
		List<DrinkGroup> joined = targetMember.getJoined();
//		List<DrinkGroup> result = drinkGroupRepository.fin();
		log.info("host 갯수: "+hosted.size());
		log.info("join 갯수: "+joined.size());
		
		for (DrinkGroup group : hosted) {
			log.info("member1이 개설한 그룹: "+group.getTitle());
		}
		for (DrinkGroup group : joined) {
			log.info("member1이 가입한 그룹: "+group.getTitle());
		}

		
		//Then
		
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
