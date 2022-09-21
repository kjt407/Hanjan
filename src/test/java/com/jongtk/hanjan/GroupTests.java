package com.jongtk.hanjan;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThrows;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberGroup;
import com.jongtk.hanjan.repository.GroupRepository;
import com.jongtk.hanjan.repository.MemberGroupRepository;
import com.jongtk.hanjan.repository.MemberRepository;
import com.jongtk.hanjan.service.GroupServiceImp;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional
@Log4j2
class GroupTests {

	@Autowired GroupRepository groupRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired MemberGroupRepository memberGroupRepository;
	@Autowired GroupServiceImp groupService;
	Member member1;
	Member member2;
	Member member3;
	
	//내가 속한 그룹 찾기2
	@Test @Rollback(false)
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
		
		log.info("group4 호스트: "+ memberRepository.findById(group4.getHostId()).get().getName());
		for(MemberGroup memberGroup : group4.getMemberGroups()) {
			log.info(memberGroup.getMember().getName());
		}
		
		//내가 속한 그룹(호스팅+구성원)
		List<Group> groups = groupRepository.findByMemeber(member1.getId());
		log.info("내가 속한 모든 그룹: "+groups.size());
		for(Group group : groups) {
			log.info(group.getTitle());
			if(group.getHostId() == member1.getId()) {
				log.info("↑ 내가 호스팅한 그룹임");
			}
		};
		
		//내가 속한 그룹(호스팅+구성원): service
		log.info("내가 속한 모든 그룹: service ========");
		log.info(groupService.getMyGroups(member1.getId()));
	}
	
	//그룹 탈퇴
	@Test @Rollback(false)
	public void 그룹탈퇴(){
		
		//Given
		createMemberEach();

		Long group1_id = groupService.hostGroup(member1.getId(), "그룹1", "그냥내용");
		Long group2_id = groupService.hostGroup(member1.getId(), "그룹2", "그냥내용");
		Long group3_id = groupService.hostGroup(member1.getId(), "그룹3", "그냥내용");
		Long group4_id = groupService.hostGroup(member1.getId(), "그룹4", "그냥내용");
		
		groupService.joinGroup(member2.getId(), group1_id);
		groupService.joinGroup(member2.getId(), group2_id);
		groupService.joinGroup(member2.getId(), group3_id);
		groupService.joinGroup(member2.getId(), group4_id);

//		assertThatThrownBy(()-> {
//			/* 그룹4에 member1 중복 추가 시 */
//			groupService.joinGroup(member1.getId(), group4_id);
//		}).isInstanceOf(RuntimeException.class);
		
		assertThrows(RuntimeException.class, ()-> {
			/* 그룹4에 member1 중복 추가 시 */
			groupService.joinGroup(member1.getId(), group4_id);
		});
		
		try {
		log.info("========= 4번째 그룹의 멤버 목록");
		groupService.withDrawGroup(group4_id, member1.getId());
		groupRepository.findById(group4_id).get()
			.getMemberGroups().stream().forEach(memberGroup->{
				log.info("멤버 : " + memberGroup.getMember().getName());
			});
		} catch (Exception e) {
			// TODO: handle exception
			log.warn(e.getMessage());
		}
		
		log.info("========= member1이 속한 그룹");
		groupRepository.findByMemeber(member1.getId()).stream().forEach(group->{
				log.info("그룹 : " + group.getTitle());
			});
	}
	
	
	
	
	//테스트 편의 메서드
	void createMemberEach() {
		member1 = Member.createMember("user1@gmail.com", "유저1", "1111", Gender.MALE);
		member2 = Member.createMember("user2@gmail.com", "유저2", "1211", Gender.FEMALE);
		member3 = Member.createMember("user3@gmail.com", "유저3", "1121", Gender.MALE);
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
	}
	

}
