package com.jongtk.hanjan.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberGroup;

import lombok.Builder;
import lombok.Data;

@Data
public class GroupDTO {

	private long id;
	
	private Member host;
	
	private String title;

	private String content;
	
	private LocalDateTime dueDate;
	
	private int membersNum;
	
	private int maxNum;

}
