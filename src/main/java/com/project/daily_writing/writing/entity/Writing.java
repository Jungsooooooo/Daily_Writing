package com.project.daily_writing.writing.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.project.daily_writing.common.entity.CommonUUID;
import com.project.daily_writing.types.Types;

import lombok.Getter;


@Entity
@Table(name = "writing")
@Getter
public class Writing extends CommonUUID {
	
	private String title;
	private String context;
	
	@ManyToOne
	private Types types;
	
	public void createWriting(String title, String context) {
		this.title 	 = title;
		this.context = context;
	}
	
}
