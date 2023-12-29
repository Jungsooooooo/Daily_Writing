package com.project.daily_writing.writing.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import com.project.daily_writing.common.entity.CommonUUID;
import com.project.daily_writing.types.Types;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "writing")
@Getter
@RequiredArgsConstructor
@Component
public class Writing extends CommonUUID {
	
	private String title;
	
	@Lob
	private String context;
	
	private Long showNumber;
	
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	@ManyToOne
	private Types types;
	
	@Builder
	public Writing(String title, String context) {
		this.title 	 = title;
		this.context = context;
	}
	
}

