package com.project.daily_writing.writing.service;

import com.project.daily_writing.writing.dto.RequestWritingDto;
import com.project.daily_writing.writing.entity.Writing;

public interface WritingService {
	
	public Writing createWriting(RequestWritingDto requestWritingDto);

}
