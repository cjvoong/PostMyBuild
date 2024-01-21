package com.voongc.postmybuild.service;

import com.voongc.postmybuild.data.entity.Address;
import com.voongc.postmybuild.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voongc.postmybuild.data.entity.Builder;
import com.voongc.postmybuild.data.repository.BuilderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
     BuilderRepository builderRepository;

    public Address getAddress(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Builder saveAddressToBuilder(Address address, long builderId){
        Builder builder = builderRepository.findById(builderId).orElseThrow(() -> new EntityNotFoundException());
        builder.addAddresses(address);
        return builderRepository.save(builder);
    }

    public Address updateAddress(Address input, Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException());
        address.setHouseNo(input.getHouseNo());
        address.setHouseNo(input.getHouseNo());
        address.setStreet(input.getStreet());
        address.setCounty(input.getCounty());
        address.setCountry(input.getCountry());
        address.setPostcode(input.getPostcode());
        // Ensure bidirectional relationship is maintained
        Builder builder = address.getBuilder();
        if (builder != null) {
            if (!builder.getAddresses().contains(address)) {
                builder.addAddresses(address);
            }
            builderRepository.save(builder);
        }

        // Save the updated address
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        Iterable<Address> addressesIterable = addressRepository.findAll();
        List<Address> addressesList = new ArrayList<>();
        addressesIterable.forEach(addressesList::add);
        return addressesList;
    }
}
