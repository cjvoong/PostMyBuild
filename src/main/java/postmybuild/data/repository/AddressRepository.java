package postmybuild.data.repository;

import org.springframework.data.repository.CrudRepository;

import postmybuild.data.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findById(Long addressId);

}
