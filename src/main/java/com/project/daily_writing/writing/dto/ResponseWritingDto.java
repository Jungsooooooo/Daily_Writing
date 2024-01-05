package com.project.daily_writing.writing.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.project.daily_writing.writing.entity.Writing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseWritingDto {
	
	private String title;
	private String context;
	private LocalDateTime creationDate;
	private Long showNumber;
	private Long id;
	
	public ResponseWritingDto(Writing writing) {
		this.id				= writing.getId();
		this.title			= writing.getTitle();
		this.context		= writing.getContext();
		this.creationDate	= writing.getCreationDate();
		this.showNumber		= writing.getShowNumber();
	}
	
	private List<ResponseWritingDto> writings;
	long totalElements;
	
	public ResponseWritingDto(Page<Writing> writings, long totalElements) {
		
		this.writings				= writings.getContent().stream().map(writing->new ResponseWritingDto(writing)).collect(Collectors.toList());
		this.totalElements 			= totalElements;
		
	}
	
}
