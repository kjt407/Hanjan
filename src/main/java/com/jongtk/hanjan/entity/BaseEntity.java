package com.jongtk.hanjan.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class}) //@CreatedDate 등 엔티티 변경시 자동으로 값을 업데이트 해주는 기능을 사용하기 위함
@Getter
abstract class BaseEntity {	// 상속없이 단독으로 사용될일 없기 때문에 추상클래스로 선언
    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;
}
