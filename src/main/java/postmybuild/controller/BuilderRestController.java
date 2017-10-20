package postmybuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.data.repository.AddressRepository;
import postmybuild.data.repository.BuilderRepository;

@RestController
public class BuilderRestController {

	@Autowired
	BuilderRepository bldrRepo;
	@Autowired
	AddressRepository addrRepo;

	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.GET)
	public Builder getBuilderById(@PathVariable Long builderId) {
		return bldrRepo.findById(builderId);
	}

	@RequestMapping(value = "/builder/add", method = RequestMethod.POST)
	public Builder createBuilder(@RequestBody Builder input) {
		addrRepo.save(input.getAddresses());
		bldrRepo.save(input);
		return bldrRepo.findById(input.getBuilderId());
	}

	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.DELETE)
	public Builder deleteBuilderById(@PathVariable Long builderId) {
		Builder tempBuilder = bldrRepo.findById(builderId);
		bldrRepo.delete(tempBuilder);
		return tempBuilder;
	}

	@RequestMapping(value = "/address/add", method = RequestMethod.POST)
	public Address createAddress(@RequestBody Address input) {
		addrRepo.save(input);
		return addrRepo.findById(input.getAddressId());
	}

}
