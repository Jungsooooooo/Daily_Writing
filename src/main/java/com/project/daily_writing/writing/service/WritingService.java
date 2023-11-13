package com.project.daily_writing.writing.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.daily_writing.writing.dto.RequestWritingDto;
import com.project.daily_writing.writing.entity.Writing;

public interface WritingService {
	
	public Writing createWriting(RequestWritingDto requestWritingDto);
	
	public Page<Writing> getWritingAll(Pageable pageable);
}
