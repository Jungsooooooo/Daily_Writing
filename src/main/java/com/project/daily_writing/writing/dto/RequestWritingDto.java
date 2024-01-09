package com.project.daily_writing.writing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestWritingDto {

	private String title;
	private String context;
	private Long   id;
	private String mainImageUrl;
	
}
