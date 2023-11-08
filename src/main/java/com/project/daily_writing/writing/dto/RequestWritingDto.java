package com.project.daily_writing.writing.dto;

import com.project.daily_writing.writing.entity.Writing;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestWritingDto {

	private String title;
	private String context;
	
}
