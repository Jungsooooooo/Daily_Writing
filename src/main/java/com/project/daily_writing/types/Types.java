package com.project.daily_writing.types;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.daily_writing.common.entity.CommonUUID;

import lombok.Getter;

@Table(name= "types")
@Entity
@Getter
public class Types extends CommonUUID {

	private String name;
}
