package postmybuild.data.repository;

import org.springframework.data.repository.CrudRepository;

import postmybuild.data.entity.Builder;

public interface BuilderRepository extends CrudRepository<Builder, Long> {

	Builder findById(Long builderId);

}
