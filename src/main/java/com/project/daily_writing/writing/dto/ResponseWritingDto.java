package com.project.daily_writing.writing.dto;

import com.project.daily_writing.writing.entity.Writing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseWritingDto {
	
	private String title;
	private String context;
	
	public ResponseWritingDto(Writing writing) {
		this.title   = writing.getTitle();
		this.context = writing.getContext();
	}
	
}