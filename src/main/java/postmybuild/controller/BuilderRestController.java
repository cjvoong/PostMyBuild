package postmybuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.service.AddressService;
import postmybuild.service.BuilderService;

import javax.transaction.Transactional;

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

	@Transactional
	@RequestMapping(value = "/builder/addBuilderWithAddress", method = RequestMethod.POST)
	public Builder createBuilder(@RequestBody Builder input) {
		return  builderService.createBuilderWithAddress(input);
	}

	@Transactional
	@RequestMapping(value="/builder/{builderId}", method=RequestMethod.POST)
	public Builder updateBuilder(@RequestBody Builder updatedBuilder,@PathVariable Long builderId){
		Builder builder = builderService.updateBuilder(updatedBuilder,builderId);
		return builder;
	}

	@RequestMapping(value = "/builder/{builderId}", method = RequestMethod.DELETE)
	public void deleteBuilderById(@PathVariable Long builderId) {
		builderService.deleteBuilder(builderId);
	}

	@RequestMapping(value = "/builder/{builderId}/address/{addressId}", method=RequestMethod.DELETE)
	public void deleteAddressFromBuilder(@PathVariable Long builderId, @PathVariable Long addressId){
		builderService.removeAddressFromBuilder(builderId,addressId);
	}

	@Transactional
	@RequestMapping(value = "/builder/{builderId}/addAddressToBuilder", method = RequestMethod.POST)
	public Address addAddressToBuilder(@RequestBody Address input, @PathVariable Long builderId) {
		addressService.saveAddressToBuilder(input,builderId);
		return addressService.getAddress(input.getAddressId());
	}
}
