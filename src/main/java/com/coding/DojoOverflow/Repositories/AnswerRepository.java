package com.coding.DojoOverflow.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coding.DojoOverflow.Models.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
	List<Answer> findAll();
}
