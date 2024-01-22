package com.voongc.postmybuild.service;

import com.voongc.postmybuild.data.entity.Address;
import com.voongc.postmybuild.data.entity.Builder;
import com.voongc.postmybuild.repository.AddressRepository;
import com.voongc.postmybuild.repository.BuilderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private BuilderRepository builderRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    void getAddress_Success() {
        // Arrange
        Long addressId = 1L;
        Address mockAddress = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(mockAddress));

        // Act
        Address result = addressService.getAddress(addressId);

        // Assert
        assertEquals(mockAddress, result);
    }

    @Test
    void getAddress_EntityNotFoundException() {
        // Arrange
        Long addressId = 1L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> addressService.getAddress(addressId));
    }

    @Test
    void saveAddressToBuilder_Success() {
        // Arrange
        long builderId = 1L;
        Builder mockBuilder = new Builder();
        mockBuilder.setAddresses(new ArrayList<>());
        Address inputAddress = new Address();
        when(builderRepository.findById(builderId)).thenReturn(Optional.of(mockBuilder));
        when(builderRepository.save(any())).thenReturn(mockBuilder);

        // Act
        Builder result = addressService.saveAddressToBuilder(inputAddress, builderId);

        // Assert
        assertEquals(mockBuilder, result);
        assertTrue(mockBuilder.getAddresses().contains(inputAddress));
    }

    @Test
    void saveAddressToBuilder_BuilderNotFoundException() {
        // Arrange
        long builderId = 1L;
        Address inputAddress = new Address();
        when(builderRepository.findById(builderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> addressService.saveAddressToBuilder(inputAddress, builderId));
    }

    @Test
    void updateAddress_Success() {
        // Arrange
        Long addressId = 1L;
        Address inputAddress = new Address();
        Address existingAddress = new Address();
        Builder existingBuilder = new Builder();
        existingBuilder.setAddresses(new ArrayList());
        existingBuilder.addAddresses(existingAddress);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
        when(builderRepository.save(any())).thenReturn(existingBuilder);
        when(addressRepository.save(any())).thenReturn(existingAddress);

        // Act
        Address result = addressService.updateAddress(inputAddress, addressId);

        // Assert
        assertEquals(existingAddress, result);
        assertEquals(inputAddress.getHouseNo(), existingAddress.getHouseNo());
        assertEquals(inputAddress.getStreet(), existingAddress.getStreet());
        assertEquals(inputAddress.getCounty(), existingAddress.getCounty());
        assertEquals(inputAddress.getCountry(), existingAddress.getCountry());
        assertEquals(inputAddress.getPostcode(), existingAddress.getPostcode());
        assertTrue(existingBuilder.getAddresses().contains(existingAddress));
    }

    @Test
    void updateAddress_EntityNotFoundException() {
        // Arrange
        Long addressId = 1L;
        Address inputAddress = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> addressService.updateAddress(inputAddress, addressId));
    }

    @Test
    void getAllAddresses_Success() {
        // Arrange
        Iterable<Address> mockAddressesIterable = List.of(new Address(), new Address());
        List<Address> mockAddressesList = new ArrayList<>();
        mockAddressesIterable.forEach(mockAddressesList::add);
        when(addressRepository.findAll()).thenReturn(mockAddressesIterable);

        // Act
        List<Address> result = addressService.getAllAddresses();

        // Assert
        assertEquals(mockAddressesList, result);
    }
}
