package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.entity.Builder;
import data.repository.BuilderRepository;

@RestController
public class BuilderRestController {

	@Autowired
	BuilderRepository builderRepo;
	
	@RequestMapping (value="/builder/{builderId}",method=RequestMethod.GET)
	public Builder getBuilderById(@PathVariable String builderId){
		return builderRepo.findById(builderId);
	}
	
	@RequestMapping(value="/builder/add",method=RequestMethod.POST)
	public Builder createBuilder(@RequestBody Builder input){
		return builderRepo.save(input);
	}
	
	@RequestMapping(value="/builder",method=RequestMethod.DELETE)
	public void deleteBuilderById(@PathVariable String builderId){
		builderRepo.delete(builderRepo.findById(builderId));
	}
	
}
