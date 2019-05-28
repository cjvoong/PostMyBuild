package postmybuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postmybuild.data.entity.Address;
import postmybuild.data.repository.AddressRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address getAddress(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Address createAddress(Address address){
        addressRepository.save(address);
        return addressRepository.findById(address.getAddressId()).orElseThrow(() -> new EntityNotFoundException());
    }


}
