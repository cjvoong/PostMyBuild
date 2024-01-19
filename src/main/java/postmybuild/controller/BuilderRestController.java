package postmybuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.service.AddressService;
import postmybuild.service.BuilderService;

@RestController
public class BuilderRestController {
	@Autowired
	BuilderService builderService;

	@Autowired
	AddressService addressService;

	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.GET)
	public Builder getBuilderById(@PathVariable Long builderId) {
		return builderService.getBuilder(builderId);
	}

	@RequestMapping(value = "/builder/add", method = RequestMethod.POST)
	public Builder createBuilder(@RequestBody Builder input) {
		return  builderService.createBuilder(input);
	}

	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.DELETE)
	public void deleteBuilderById(@PathVariable Long builderId) {
		builderService.deleteBuilder(builderId);
	}

	@RequestMapping(value = "/address/add", method = RequestMethod.POST)
	public Address createAddress(@RequestBody Address input) {
		addressService.createAddress(input);
		return addressService.getAddress(input.getAddressId());
	}
}
