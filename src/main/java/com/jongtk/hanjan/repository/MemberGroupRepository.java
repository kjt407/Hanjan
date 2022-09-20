package com.jongtk.hanjan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.MemberGroup;


public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long>{
	
	@Query("SELECT mg FROM MemberGroup mg WHERE mg.member.id = :memberId AND mg.group.id = :groupId")
	Optional<MemberGroup> findByAllMatch(@Param("memberId")Long memberId, @Param("groupId")Long groupId);
	
}
