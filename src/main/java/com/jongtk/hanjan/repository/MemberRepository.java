package com.jongtk.hanjan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;


// SpringDataJpa는 JpaRepository 상속한 인터페이스를 구현하는 것 만으로도 Repository 기능을 사용 가능
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	@Query("select mg.group from Member m join m.memberGroups mg on m.id = mg.member.id")
	List<Group> findMyGroup(@Param("member_id") Long member_id);
	
}
