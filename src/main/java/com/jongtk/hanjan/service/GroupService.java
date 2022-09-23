package com.jongtk.hanjan.service;

import java.util.List;
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
@Transactional
public interface GroupService {
	
	//그룹 생성
	public Long host(Long memberId, String title, String content);
	
	//그룹 가입
	public void join(Long memberId, Long groupId);
	
	//그룹 조회(ALL)
	public List<GroupDTO> getAll();
	
	//내 그룹 조회
	public List<GroupDTO> getMyGroups(Long memberId);
		
	//그룹 탈퇴
	public void withDrawGroup(Long groupId, Long memberId);
	
	
	//모임날짜생성
	
	
	/* 공통 메소드 구현 */
	/*
	 * Group -> GroupDTO
	 * 멤버 리스트 까지 fullConvert 
	 */
	default GroupDTO entityToDTO(Group group) {
		
		GroupDTO groupDTO = GroupDTO.builder()
				.id(group.getId())
				.hostId(group.getHostId())
				.title(group.getTitle())
				.content(group.getContent())
				.memberList(group.getMemberGroups().stream().map(
						memberGroup-> {
							MemberDTO memberDTO = MemberDTO.builder()
									.id(memberGroup.getMember().getId())
									.email(memberGroup.getMember().getEmail())
									.name(memberGroup.getMember().getName())
									.gender(memberGroup.getMember().getGender().toString())
									.message(memberGroup.getMember().getMessage())
									.mbti(memberGroup.getMember().getMbti())
									.drinkCapacity(memberGroup.getMember().getDrinkCapacity())
									.address(memberGroup.getMember().getAddress())
									.build();
							return memberDTO;
						}).collect(Collectors.toList()))
				.build();
		
		return groupDTO;
	}
	
	/*
	 * GroupDTO -> Group
	 * 현재 미구현 필요시 구현하여 사용할것
	 */
	@Deprecated
	default GroupDTO dtoToEntity(Group group) {
		// 브랜치 변경 오류 해결용
		return null;
	}
	
}
