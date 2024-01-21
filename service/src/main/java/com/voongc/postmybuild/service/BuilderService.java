package com.voongc.postmybuild.service;

import com.voongc.postmybuild.data.entity.Address;
import com.voongc.postmybuild.data.repository.AddressRepository;
import com.voongc.postmybuild.data.repository.BuilderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voongc.postmybuild.data.entity.Builder;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public Builder updateBuilder(Builder updatedBuilder, Long builderId) {
        Builder builder = builderRepository.findById(builderId).orElseThrow(() -> new EntityNotFoundException());

        // Update builder details
        builder.setBuilderForename(updatedBuilder.getBuilderForename());
        builder.setBuilderSurname(updatedBuilder.getBuilderSurname());
        builder.setBuilderName(updatedBuilder.getBuilderName());

        // Update addresses individually or replace them entirely based on your requirement
        builder.getAddresses().clear();  // Clear existing addresses
        builder.getAddresses().addAll(updatedBuilder.getAddresses());  // Add updated addresses

        builder.getAddresses().forEach(address -> address.setBuilder(builder));

        builderRepository.save(builder);

        // Return the updated builder
        return builder;
    }

    public void removeAddressFromBuilder(Long builderId, Long addressId) {
        Address addressToDelete  = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException());
        addressRepository.delete(addressToDelete);
    }

    public List<Builder> getAllBuilders() {
        Iterable<Builder> buildersIterable = builderRepository.findAll();
        List<Builder> buildersList = new ArrayList<>();
        buildersIterable.forEach(buildersList::add);
        return buildersList;
    }
}
