package com.project.daily_writing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.daily_writing.writing.entity.Writing;
import com.project.daily_writing.writing.repository.WritingRepository;

public class TestCode {
	
	private WritingRepository writingRepository;
	
	@Autowired
	public TestCode(WritingRepository writingRepository) {
		this.writingRepository = writingRepository;
	}
	
	@Test
	public void create() {
		Writing w = new Writing();
		w.createWriting("test", "테스트 입니다");
		writingRepository.save(w);
	}

}
