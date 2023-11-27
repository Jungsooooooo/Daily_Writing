package com.project.daily_writing.writing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.daily_writing.writing.dto.RequestWritingDto;
import com.project.daily_writing.writing.entity.Writing;
import com.project.daily_writing.writing.repository.WritingRepository;
import com.project.daily_writing.writing.service.WritingService;

@Service
public class WritingServiceImpl implements WritingService {
	
	private final Writing writing;
	private final WritingRepository writingRepository;
	
	public WritingServiceImpl(
			Writing writing,
			WritingRepository writingRepository) {
		this.writing = writing;
		this.writingRepository = writingRepository;
	}

	@Override
	public Writing createWriting(RequestWritingDto requestWritingDto) {
		
		String title	= requestWritingDto.getTitle();
		String context	= requestWritingDto.getContext();  
		
		Writing wt = new Writing().builder().title(title).context(context).build();
		writingRepository.save(wt);
		return wt;
	}
	
	@Override
	public Page<Writing> getWritingAll(Pageable pageable) {
		
		Page<Writing> writingList = writingRepository.findAll(pageable);
		
		return writingList;
	}
	
	@Override
	public void deleteWriting(String id) {
				
		writingRepository.deleteById(Integer.parseInt(id));
		
	}
}
