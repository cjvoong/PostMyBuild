package postmybuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import postmybuild.data.entity.Builder;
import postmybuild.data.repository.BuilderRepository;

@RestController
public class BuilderRestController {

	@Autowired
	BuilderRepository repository;
	
	@RequestMapping (value="/builder/{builderId}",method=RequestMethod.GET)
	public Builder getBuilderById(@PathVariable String builderId){
		return repository.findById(builderId);
	}
	
	@RequestMapping(value="/builder/add",method=RequestMethod.POST)
	public Builder createBuilder(@RequestBody Builder input){
		return repository.save(input);
	}
	
	@RequestMapping(value="/builder",method=RequestMethod.DELETE)
	public void deleteBuilderById(@PathVariable String builderId){
		repository.delete(repository.findById(builderId));
	}
	
}
