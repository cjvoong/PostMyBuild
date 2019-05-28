package postmybuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postmybuild.data.entity.Builder;
import postmybuild.data.repository.AddressRepository;
import postmybuild.data.repository.BuilderRepository;

import javax.persistence.EntityNotFoundException;

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

    public Builder createBuilder(Builder builder){
        addressRepository.saveAll(builder.getAddresses());
        builderRepository.save(builder);
        return builderRepository.findById(builder.getBuilderId()).orElseThrow(() -> new EntityNotFoundException());
    }
}
