package com.project.daily_writing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.daily_writing.writing.entity.Writing;
import com.project.daily_writing.writing.repository.WritingRepository;

@SpringBootTest
class DailyWritingApplicationTests {
	
	private WritingRepository writingRepository;
	
	public DailyWritingApplicationTests(WritingRepository writingRepository) {
		this.writingRepository = writingRepository;
	}

	@Test
	void contextLoads() {
	}
	
	@Test
	void create() {
		
	}

}
