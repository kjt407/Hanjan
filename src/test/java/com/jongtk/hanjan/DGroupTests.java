package com.jongtk.hanjan;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jongtk.hanjan.entity.DrinkGroup;
import com.jongtk.hanjan.entity.Gender;
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
//	@Test
//	void createGroup() {
//		
//		createMemberEach();
//		
//		log.info("*****members: ");
//		log.info(member1);
//		log.info(member2);
//		log.info(member3);
//		
//		DrinkGroup group1 = DrinkGroup.builder()
//				.host(member1)
//				.build();
//		
////		group1.addMember(member2);
//
//		
//		drinkGroupRepository.save(group1);
//		
//		log.info("*****group1 ");
//		for(Member member : group1.getMembers()) {
//			log.info(member.getName());
//		}
//	}
//	
//	//구성원 중복추가
//	@Test
//	public void addDupplicateMember(){
//		
//		//Given
//		createMemberEach();
//		
//		log.info("*****members: ");
//		log.info(member1);
//		log.info(member2);
//		log.info(member3);
//		
//		DrinkGroup group1 = DrinkGroup.builder()
//				.host(member1)
//				.build();
//				
//		//When
//		assertThatThrownBy(()->{
//			group1.getMembers().add(member2);
//			group1.getMembers().add(member2);
//		}).isInstanceOf(IllegalStateException.class).hasMessageContaining("이미 구성원 입니다.");
//
//		
//		//Then
//		
//	}
//	
//
//	//내가 속한 그룹 찾기 JPQL
//	@Test
//	public void findMyGroupJpql() throws Exception{
//		
//		//Given
//		createMemberEach();
//
//		
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
//		
//		group4.getMembers().add(member1);
//	
//		drinkGroupRepository.save(group1);
//		drinkGroupRepository.save(group2);
//		drinkGroupRepository.save(group3);
//		drinkGroupRepository.save(group4);
//		
//		log.info("group1 members size:"+group1.getMembers().size());
//		log.info("group2 members size:"+group2.getMembers().size());
//		log.info("group3 members size:"+group3.getMembers().size());
//		log.info("group4 members size:"+group4.getMembers().size());
//		
//		List<DrinkGroup> joinedG = drinkGroupRepository.findJoinedGroup(member1.getId());
//		List<DrinkGroup> hostedG = drinkGroupRepository.findHostedGroup(member1.getId());
//		
//		log.info("가입한 모임 갯수: "+joinedG.size()+" 개설한 모임 갯수: "+hostedG.size());
//		
//		for (DrinkGroup group : hostedG) {
//			log.info("member1이 개설한 그룹: "+group.getTitle());
//		}
//		for (DrinkGroup group : joinedG) {
//			log.info("member1이 가입한 그룹: "+group.getTitle());
//		}
//
//		//Then		
//	}
	
	//내가 속한 그룹 찾기2
	@Test
	public void 내그룹찾기() throws Exception{
		
		//Given
		createMemberEach();
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);

		DrinkGroup group1 = DrinkGroup.createGroup(member1, "그룹1", "그냥내용");
		DrinkGroup group2 = DrinkGroup.createGroup(member1, "그룹2", "그냥내용");
		DrinkGroup group3 = DrinkGroup.createGroup(member1, "그룹3", "그냥내용");
		DrinkGroup group4 = DrinkGroup.createGroup(member2, "그룹4", "그냥내용");
		
		group4.getMembers().add(member1);
	
		drinkGroupRepository.save(group1);
		drinkGroupRepository.save(group2);
		drinkGroupRepository.save(group3);
		drinkGroupRepository.save(group4);
		
		log.info("group1 members size:"+group1.getMembers().size());
		log.info("group2 members size:"+group2.getMembers().size());
		log.info("group3 members size:"+group3.getMembers().size());
		log.info("group4 members size:"+group4.getMembers().size());
		
		List<DrinkGroup> joinedG = drinkGroupRepository.findJoinedGroup(member1.getId());
		
		log.info("가입한 모임 갯수: "+joinedG.size());
		
		for (DrinkGroup group : joinedG) {
			log.info("member1이 가입한 그룹: "+group.getTitle());
		}

		//Then		
	}
	
	
	//테스트 편의 메서드
	void createMemberEach() {
		member1 = Member.createMember("user1", "user1@gmail.com", "유저1", Gender.MALE);
		member2 = Member.createMember("user2", "user2@gmail.com", "유저2", Gender.FEMALE);
		member3 = Member.createMember("user3", "user3@gmail.com", "유저3", Gender.MALE);
	}
	

}
