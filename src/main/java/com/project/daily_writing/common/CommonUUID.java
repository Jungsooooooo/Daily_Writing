package com.project.daily_writing.common;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "CommonUUID")
public class CommonUUID {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
}
