package data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import data.entity.Builder;

public interface BuilderRepository extends CrudRepository<Builder,Long> {

	Builder findById(String id);
	
}
