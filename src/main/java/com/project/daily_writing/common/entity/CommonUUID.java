package com.project.daily_writing.common.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name= "CommonUUID")
@Inheritance(strategy = InheritanceType.JOINED)
public class CommonUUID {
	
	@Id
	@GeneratedValue(generator="uuid2")
	@Column(columnDefinition = "VARBINARY(16)")
	private UUID uuid;
}
