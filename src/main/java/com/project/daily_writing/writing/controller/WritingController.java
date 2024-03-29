package com.project.daily_writing.writing.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.daily_writing.writing.dto.RequestWritingDto;
import com.project.daily_writing.writing.dto.ResponseWritingDto;
import com.project.daily_writing.writing.entity.Writing;
import com.project.daily_writing.writing.service.WritingService;

@RestController
@RequestMapping("/api/writings")
public class WritingController {
	

	private WritingService writingService;
	
	public WritingController(WritingService writingService) {
		this.writingService = writingService;
	}
	
	@GetMapping
	public ResponseEntity<?> getWriting(@RequestParam("page") int page,@RequestParam("size") int size,  Pageable pageable) {
 		Page<Writing> writingList = writingService.getWritingAll(page, size, pageable);
		List<ResponseWritingDto> writingListResponse = writingList.stream().map(writing->new ResponseWritingDto(writing)).collect(Collectors.toList());
		ResponseWritingDto writingListResponse2 = new ResponseWritingDto(writingList,writingList.getTotalElements());
		return new ResponseEntity<>(writingListResponse2, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getWritingOne(@PathVariable("id") Long id) {
		Writing writing = writingService.getWritingOne(id);
		ResponseWritingDto writingListResponse =  new ResponseWritingDto(writing);
		
		return new ResponseEntity<>(writingListResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<?> getSearchDatabyName(@PathVariable("name") String name, Pageable pageable){
		
		Page<Writing> writingList = writingService.getSearchWritingByTitle(name, pageable);
		List<ResponseWritingDto> writingListResponse = writingList.stream().map(writing->new ResponseWritingDto(writing)).collect(Collectors.toList());
		
		return new ResponseEntity<>(writingListResponse, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody RequestWritingDto requestWritingDto) {
		
		Writing writing = writingService.createWriting(requestWritingDto); 
		ResponseWritingDto responseWritingDto = new ResponseWritingDto(writing);
		
		return new ResponseEntity<>(responseWritingDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody RequestWritingDto requestWritingDto){
		
		Writing writing = writingService.updateWriting(requestWritingDto);
		ResponseWritingDto responseWritingDto = new ResponseWritingDto(writing);
		
		return new ResponseEntity<>(responseWritingDto, HttpStatus.OK);
		
	}
	
	@PostMapping("/search")
	   public ResponseEntity<?> getSearchDatabyName(@RequestBody RequestWritingDto requestWritingDto , Pageable pageable){
	      
	      Page<Writing> writingList = writingService.getSearchWritingByTitle(requestWritingDto.getTitle(), pageable);
	      List<ResponseWritingDto> writingListResponse = writingList.stream().map(writing->new ResponseWritingDto(writing)).collect(Collectors.toList());
	      
	      return new ResponseEntity<>(writingListResponse, HttpStatus.OK);
	   }
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		
		writingService.deleteWriting(id);
		
		return new ResponseEntity<>("delete completed",HttpStatus.OK);
	}
	
}
