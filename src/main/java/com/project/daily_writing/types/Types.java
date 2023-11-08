package com.project.daily_writing.types;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.daily_writing.common.CommonUUID;

import lombok.Getter;

@Entity
@Getter
@Table(name="types")
public class Types extends CommonUUID {

	private String name;
}
