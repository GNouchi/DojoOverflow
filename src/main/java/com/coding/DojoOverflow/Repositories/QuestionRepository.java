package com.coding.DojoOverflow.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coding.DojoOverflow.Models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{
    List<Question> findAll();
}
