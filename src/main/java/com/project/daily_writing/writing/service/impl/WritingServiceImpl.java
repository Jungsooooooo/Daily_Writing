package com.project.daily_writing.writing.service.impl;

import org.springframework.stereotype.Service;

import com.project.daily_writing.writing.dto.RequestWritingDto;
import com.project.daily_writing.writing.entity.Writing;
import com.project.daily_writing.writing.repository.WritingRepository;
import com.project.daily_writing.writing.service.WritingService;

@Service
public class WritingServiceImpl implements WritingService {
	
	private final Writing writing;
//	private final WritingRepository writingRepository;
	
	public WritingServiceImpl(
			Writing writing,
			WritingRepository writingRepository) {
		this.writing = writing;
//		this.writingRepository = writingRepository;
	}

	@Override
	public Writing createWriting(RequestWritingDto requestWritingDto) {
		
		String title	= requestWritingDto.getTitle();
		String context	= requestWritingDto.getContext();  
		
		writing.createWriting(title, context);
		
		return null;
	}
}
