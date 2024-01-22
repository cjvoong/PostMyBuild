package com.voongc.postmybuild.controller;

import com.voongc.postmybuild.data.entity.Address;
import com.voongc.postmybuild.data.entity.Builder;
import com.voongc.postmybuild.service.AddressService;
import com.voongc.postmybuild.service.BuilderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @Mock
    private BuilderService builderService;

    @InjectMocks
    private AddressController addressController;

    @Test
    void deleteAddressFromBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        Long addressId = 2L;

        // Act
        ResponseEntity<Builder> response = addressController.deleteAddressFromBuilder(builderId, addressId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(builderService).removeAddressFromBuilder(builderId, addressId);
    }

    @Test
    void deleteAddressFromBuilder_EntityNotFoundException() {

        // Arrange
        Long builderId = 1L;
        Long addressId = 2L;
        doThrow(new EntityNotFoundException()).when(builderService).removeAddressFromBuilder(builderId, addressId);

        // Act
        ResponseEntity<Builder> response = addressController.deleteAddressFromBuilder(builderId, addressId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void addAddressToBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        Address inputAddress = new Address();
        Builder builder = new Builder();
        when(addressService.saveAddressToBuilder(any(Address.class), anyLong())).thenReturn(builder);

        // Act
        ResponseEntity<Builder> response = addressController.addAddressToBuilder(inputAddress, builderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(builder, response.getBody());
    }

    @Test
    void addAddressToBuilder_EntityNotFoundException() {
        // Arrange
        Long builderId = 1L;
        Address inputAddress = new Address();
        when(addressService.saveAddressToBuilder(any(Address.class), anyLong())).thenThrow(new EntityNotFoundException());

        // Act
        ResponseEntity<Builder> response = addressController.addAddressToBuilder(inputAddress, builderId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Add tests can be written for updateAddress, getAllAddressesForBuilder, and getAllAddresses methods.
}
