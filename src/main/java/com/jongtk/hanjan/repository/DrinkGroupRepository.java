package com.jongtk.hanjan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.DrinkGroup;
import com.jongtk.hanjan.entity.Member;


// SpringDataJpa는 JpaRepository 상속한 인터페이스를 구현하는 것 만으로도 Repository 기능을 사용 가능
public interface DrinkGroupRepository extends JpaRepository<DrinkGroup, Long>{
	
	@Query("select dg from DrinkGroup dg join dg.members m where m.id=:member_id")
	List<DrinkGroup> findJoinedGroup(@Param("member_id")long member_id);	
//	
//	@Query("select dg from DrinkGroup dg where dg.host.id=:member_id")
//	List<DrinkGroup> findHostedGroup(@Param("member_id")long member_id);
}
