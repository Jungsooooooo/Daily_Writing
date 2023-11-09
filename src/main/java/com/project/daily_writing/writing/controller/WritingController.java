package com.project.daily_writing.writing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.daily_writing.writing.dto.RequestWritingDto;
import com.project.daily_writing.writing.entity.Writing;
import com.project.daily_writing.writing.service.WritingService;

@Controller
@RequestMapping("/api/writings")
public class WritingController {
	
	@Autowired
	private WritingService writingService;
	
	public WritingController(WritingService writingService) {
		this.writingService = writingService;
	}
	
	@PostMapping("/create")
	public Writing create(@RequestBody RequestWritingDto requestWritingDto) {
		
		
		return writingService.createWriting(requestWritingDto);
	}
	
	
	
	
}
