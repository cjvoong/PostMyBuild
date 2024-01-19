package postmybuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.data.repository.AddressRepository;
import postmybuild.data.repository.BuilderRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class BuilderService {

    @Autowired
    BuilderRepository builderRepository;
    @Autowired
    AddressRepository addressRepository;

    public Builder getBuilder(Long id){
        return builderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public void deleteBuilder(Long id){
        builderRepository.delete(builderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Transactional
    public Builder createBuilderWithAddress(Builder builder){
        builderRepository.save(builder);
        return builderRepository.findById(builder.getBuilderId()).orElseThrow(() -> new EntityNotFoundException());
    }

    public Builder updateBuilder(Builder updatedBuilder, Long builderId) {
        Builder builder = builderRepository.findById(builderId).orElseThrow(() -> new EntityNotFoundException());
        builder.setBuilderForename(updatedBuilder.getBuilderForename());
        builder.setBuilderSurname(updatedBuilder.getBuilderSurname());
        builder.setBuilderName(updatedBuilder.getBuilderName());
        builder.setAddresses(updatedBuilder.getAddresses());
        builderRepository.save(builder);
        return builderRepository.findById(builder.getBuilderId()).orElseThrow(() -> new EntityNotFoundException());
    }

    public void removeAddressFromBuilder(Long builderId, Long addressId) {
        Address addressToDelete  = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException());
        addressRepository.delete(addressToDelete);
    }
}
