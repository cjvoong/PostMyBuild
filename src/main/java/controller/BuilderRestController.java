package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Builder builder(@PathVariable String builderId){
		return builderRepo.findById(builderId);
	}
}
