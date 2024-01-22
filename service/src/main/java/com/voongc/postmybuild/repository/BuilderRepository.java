package com.voongc.postmybuild.repository;

import org.springframework.data.repository.CrudRepository;

import com.voongc.postmybuild.data.entity.Builder;

import java.util.Optional;

public interface BuilderRepository extends CrudRepository<Builder, Long> {
	Optional<Builder> findById(Long builderId);
}
