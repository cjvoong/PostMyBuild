package postmybuild.data.repository;

import org.springframework.data.repository.CrudRepository;

import postmybuild.data.entity.Address;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Optional<Address> findById(Long addressId);

}
