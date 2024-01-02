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
		
		Long longId = Long.parseLong(id);
		Writing writing = writingRepository.findById(longId);
		writingRepository.delete(writing);
		
	}
	
	@Override
	public Writing getWritingOne(Long id) {
		
		Writing writing = writingRepository.findById(id);
		
		return writing;
	}
	
	@Override
	public Page<Writing> getSearchWritingByTitle(String title,Pageable pageable) {
	
		Page<Writing> writing = writingRepository.findByTitleContaining(title,pageable);
		
		return writing;
	}
	
	@Override
	public Writing updateWriting(RequestWritingDto requestWritingDto) {
		
		Long id = requestWritingDto.getId();
		
		Writing writing = writingRepository.findById(id);
		
		String title   = requestWritingDto.getTitle();
		String context = requestWritingDto.getContext();
		
		writing.updateWriting(title, context);
		writingRepository.save(writing);
		
		return writing;
	}
}
