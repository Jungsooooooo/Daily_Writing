package com.project.daily_writing.writing.repository;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.daily_writing.writing.entity.Writing;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Integer> {
	
	Page<Writing> findAll(Pageable pageable);
}
