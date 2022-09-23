package com.jongtk.hanjan.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jongtk.hanjan.dto.GroupDTO;
import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberGroup;
import com.jongtk.hanjan.repository.GroupRepository;
import com.jongtk.hanjan.repository.MemberGroupRepository;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImp implements GroupService{
	
	private final GroupRepository groupRepository;
	private final MemberRepository memberRepository;
	private final MemberGroupRepository memberGroupRepository ;
	
	/*
	 * 그룹 생성
	 */
	public Long host(Long memberId, String title, String content) {
		
		Member member = memberRepository.findById(memberId).get();
		
		/*
		 * 연결 테이블 영속화 
		 * member와 group의 외래키 관리는 MemberGroup Entity에서 수행한다
		 * 따라서 반드시 MemberGroup Entity를 영속화 시켜 연관관계 유지
		 */		
		MemberGroup memberGroup = MemberGroup.createMemberGroup(member);
		memberGroupRepository.save(memberGroup);
		
		// 새 그룹 생성 및 영속
		Group group = Group.createGroup(member, title, content, memberGroup);
		groupRepository.save(group);
		
		return group.getId(); 
	}
	
	/*
	 * 그룹 가입
	 */
	public void join(Long memberId, Long groupId) {
		
		/* 같은 회원 중복가입 방지 */
		Optional<MemberGroup> mgOp = memberGroupRepository.findByAllMatch(memberId, groupId);
		if(mgOp.isPresent()) {
			throw new RuntimeException("already joined");
		}
		
		Optional<Member> memberOp = memberRepository.findById(memberId);
		Optional<Group> groupOp = groupRepository.findById(groupId);
		
		if(! (memberOp.isPresent() && groupOp.isPresent()) ) {
			// 멤버 또는 그룹이 존재하지 않을 경우 예외
			throw new RuntimeException("member or group is not Exist");
		}
		
		Member member = memberOp.get();
		Group group = groupOp.get();
		
		/*
		 * 연결 테이블 영속화 
		 * member와 group의 외래키 관리는 MemberGroup Entity에서 수행한다
		 * 따라서 반드시 MemberGroup Entity를 영속화 시켜 연관관계 유지
		 */
		MemberGroup memberGroup = MemberGroup.createMemberGroup(member);		
		memberGroupRepository.save(memberGroup);
		
		group.join(memberGroup);
		groupRepository.save(group);
	}
	
	//그룹 조회(ALL)
	public List<GroupDTO> getAll() {
		List<Group> groupList = groupRepository.findAll();
		
		List<GroupDTO> result = groupList.stream().map(group -> {
			return entityToDTO(group);
		}).collect(Collectors.toList());
		
		return result;
	}
	
	//내 그룹 조회
	public List<GroupDTO> getMyGroups(Long memberId) {
		List<Group> groupList = groupRepository.findByMemeber(memberId);
		
		List<GroupDTO> result = groupList.stream().map(group -> {
			return entityToDTO(group);
		}).collect(Collectors.toList());
		
		return result;
	}

		
	//그룹 탈퇴
	public void withDrawGroup(Long groupId, Long memberId) {
		
		Optional<MemberGroup> mgOp = memberGroupRepository.findByAllMatch(memberId, groupId);
		if(!mgOp.isPresent()) {
			throw new RuntimeException("It does not exist");
		}
		MemberGroup mg = mgOp.get();
		
		Group targetGroup =  mg.getGroup();
		targetGroup.withDraw(mg);
		
		groupRepository.save(targetGroup);
		memberGroupRepository.delete(mg);
	}

	
	
	//모임날짜생성
}
