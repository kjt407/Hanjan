package com.jongtk.hanjan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.MemberGroup;


// SpringDataJpa는 JpaRepository 상속한 인터페이스를 구현하는 것 만으로도 Repository 기능을 사용 가능
public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long>{
	
	@Query("select g from MemberGroup mg join mg.group g where mg.member.id = :member_id")
	List<Group> findMyGroup(@Param("member_id")long member_id);
	
}
