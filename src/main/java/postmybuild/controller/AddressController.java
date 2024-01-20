package postmybuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.service.AddressService;
import postmybuild.service.BuilderService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
@RestController

public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    BuilderService builderService;

    @RequestMapping(value = "/builder/{builderId}/address/{addressId}", method= RequestMethod.DELETE)
    public ResponseEntity<Builder> deleteAddressFromBuilder(@PathVariable Long builderId, @PathVariable Long addressId){
        try {
            builderService.removeAddressFromBuilder(builderId, addressId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Transactional
    @RequestMapping(value = "/builder/{builderId}/addAddress", method = RequestMethod.POST)
    public ResponseEntity<Builder> addAddressToBuilder(@RequestBody Address input, @PathVariable Long builderId) {
        try {
            Builder builder = addressService.saveAddressToBuilder(input,builderId);
            return ResponseEntity.ok(builder);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Transactional
    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@RequestBody Address input, @PathVariable Long addressId) {
        try {
            Address address = addressService.updateAddress(input,addressId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value="/builder/{builderId}/addresses", method=RequestMethod.GET)
    public ResponseEntity<List<Address>> getAllAddressesForBuilder(@PathVariable Long builderId){
        try {
            List<Address> addresses = builderService.getBuilder(builderId).getAddresses();
            return ResponseEntity.ok(addresses);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value="/addresses", method=RequestMethod.GET)
    public ResponseEntity<List<Address>> getAllAddresses(){
        try {
            List<Address> addresses = addressService.getAllAddresses();
            return ResponseEntity.ok(addresses);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
