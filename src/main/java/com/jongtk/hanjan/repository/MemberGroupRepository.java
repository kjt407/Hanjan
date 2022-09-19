package com.jongtk.hanjan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.MemberGroup;


public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long>{
	
}
