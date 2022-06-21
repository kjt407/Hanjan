package com.jongtk.hanjan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jongtk.hanjan.entity.Member;


// SpringDataJpa는 JpaRepository 상속한 인터페이스를 구현하는 것 만으로도 Repository 기능을 사용 가능
public interface MemberRepository extends JpaRepository<Member, Long>{
	
}
