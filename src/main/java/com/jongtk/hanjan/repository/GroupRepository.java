package com.jongtk.hanjan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.MemberGroup;


public interface GroupRepository extends JpaRepository<Group, Long>{
	
	//특정 사용자가 가입한 모임만 조회
	@Query("select g from Group g join g.memberGroups mg where mg.member.id = :memberId")
	List<Group> findByMemeber(@Param("memberId")long memberId);
	
	@Query("select mg from Group g join g.memberGroups mg where g.id = :groupId and mg.member.id = :memberId")
	Optional<MemberGroup> findMemberGroupByGroupId(@Param("groupId")long groupId, @Param("memberId")long memberId);
}
