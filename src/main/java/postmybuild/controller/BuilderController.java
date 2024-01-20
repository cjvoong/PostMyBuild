package postmybuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postmybuild.data.entity.Builder;
import postmybuild.service.BuilderService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class BuilderController {
	@Autowired
	BuilderService builderService;

	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.GET)
	public ResponseEntity<Builder> getBuilderById(@PathVariable Long builderId) {
		try {
			Builder builder = builderService.getBuilder(builderId);
			return ResponseEntity.ok(builder);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Transactional
	@RequestMapping(value = "/builder/createBuilder", method = RequestMethod.POST)
	public ResponseEntity<Builder> createBuilder(@RequestBody Builder input) {
		Builder builder = builderService.createBuilderWithAddress(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(builder);
	}

	@RequestMapping(value = "/builder", method = RequestMethod.GET)
	public ResponseEntity<List<Builder>> getAllBuilders() {
		List<Builder> builders = builderService.getAllBuilders();
		return ResponseEntity.ok(builders);
	}

	@Transactional
	@RequestMapping(value="/builder/{builderId}/updateBuilder", method=RequestMethod.PUT)
	public ResponseEntity<Builder> updateBuilder(@RequestBody Builder updatedBuilder,@PathVariable Long builderId){
		try {
			Builder builder = builderService.updateBuilder(updatedBuilder, builderId);
			return ResponseEntity.ok(builder);
		} catch (EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}
	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBuilderById(@PathVariable Long builderId) {
		try {
			builderService.deleteBuilder(builderId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
