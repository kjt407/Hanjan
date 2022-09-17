package com.jongtk.hanjan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;


// SpringDataJpa는 JpaRepository 상속한 인터페이스를 구현하는 것 만으로도 Repository 기능을 사용 가능
public interface GroupRepository extends JpaRepository<Group, Long>{
	
	//특정 사용자가 가입한 모임만 조회
	@Query("select g from Group g join g.memberGroups mg where mg.member.id = :member_id")
	List<Group> findMyGroup(@Param("member_id")long member_id);
	
	//특정 사용자가 호스팅한 모임만 조회
	@Query("select g from Group g join g.memberGroups mg where mg.group.hostId = :member_id")
	List<Group> findHostGroup(@Param("member_id")long member_id);
	
	//특정 사용자가 가입한 모임만 조회
	@Query("select g from Group g join g.memberGroups mg where mg.member.id = :member_id and mg.group.hostId != :member_id")
	List<Group> findJoinGroup(@Param("member_id")long member_id);
	
}
