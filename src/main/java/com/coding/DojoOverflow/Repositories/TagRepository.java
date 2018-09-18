package com.coding.DojoOverflow.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coding.DojoOverflow.Models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{
	List<Tag> findAll();
}
