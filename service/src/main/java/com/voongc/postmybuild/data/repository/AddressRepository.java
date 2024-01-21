package com.voongc.postmybuild.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.voongc.postmybuild.data.entity.Address;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Optional<Address> findById(Long addressId);

}
