package com.jongtk.hanjan;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberGroup;
import com.jongtk.hanjan.repository.GroupRepository;
import com.jongtk.hanjan.repository.MemberGroupRepository;
import com.jongtk.hanjan.repository.MemberRepository;
import com.jongtk.hanjan.service.GroupService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional
@Log4j2
class GroupTests {

	@Autowired GroupRepository groupRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired MemberGroupRepository memberGroupRepository;
	@Autowired GroupService groupService;
	Member member1;
	Member member2;
	Member member3;
	
	//내가 속한 그룹 찾기2
	@Test
	public void 내그룹찾기() throws Exception{
		
		//Given
		createMemberEach();
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);

		Long group1_id = groupService.hostGroup(member1.getId(), "그룹1", "그냥내용");
		Long group2_id = groupService.hostGroup(member1.getId(), "그룹2", "그냥내용");
		Long group3_id = groupService.hostGroup(member1.getId(), "그룹3", "그냥내용");
		Long group4_id = groupService.hostGroup(member2.getId(), "그룹4", "그냥내용");
		
		groupService.joinGroup(member1.getId(), group4_id);
		
		Group group4 = groupRepository.findById(group4_id).get();
		
		log.info("group4 구성: "+group4.getHost().getName());
		for(MemberGroup memberGroup : group4.getMemberGroups()) {
			log.info(memberGroup.getMember().getName());
		}
		
		List<Group> groups = groupRepository.findMyGroup(member1.getId());
		log.info(groups.size());
		for(Group group : groups) {
			log.info(group.getTitle());
		};
		
		
		
//		Group group1 = Group.createGroup(member1, "그룹1", "그냥내용");
//		Group group2 = Group.createGroup(member1, "그룹2", "그냥내용");
//		Group group3 = Group.createGroup(member1, "그룹3", "그냥내용");
//		Group group4 = Group.createGroup(member2, "그룹4", "그냥내용");
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
//		List<Group> joinedG = drinkGroupRepository.findJoinedGroup(member1.getId());
//		
//		log.info("가입한 모임 갯수: "+joinedG.size());
//		
//		for (Group group : joinedG) {
//			log.info("member1이 가입한 그룹: "+group.getTitle());
//		}

	}
	
	
	//테스트 편의 메서드
	void createMemberEach() {
		member1 = Member.createMember("user1", "user1@gmail.com", "유저1", Gender.MALE);
		member2 = Member.createMember("user2", "user2@gmail.com", "유저2", Gender.FEMALE);
		member3 = Member.createMember("user3", "user3@gmail.com", "유저3", Gender.MALE);
	}
	

}
