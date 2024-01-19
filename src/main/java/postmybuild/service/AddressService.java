package postmybuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.data.repository.AddressRepository;
import postmybuild.data.repository.BuilderRepository;

import javax.persistence.EntityNotFoundException;

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
}
