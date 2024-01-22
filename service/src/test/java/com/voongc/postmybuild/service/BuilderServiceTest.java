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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuilderServiceTest {

    @Mock
    private BuilderRepository builderRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private BuilderService builderService;

    @Test
    void getBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        Builder mockBuilder = new Builder();
        when(builderRepository.findById(builderId)).thenReturn(Optional.of(mockBuilder));

        // Act
        Builder result = builderService.getBuilder(builderId);

        // Assert
        assertEquals(mockBuilder, result);
    }

    @Test
    void getBuilder_EntityNotFoundException() {
        // Arrange
        Long builderId = 1L;
        when(builderRepository.findById(builderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> builderService.getBuilder(builderId));
    }

    @Test
    void deleteBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        when(builderRepository.findById(builderId)).thenReturn(Optional.of(new Builder()));

        // Act
        builderService.deleteBuilder(builderId);

        // Assert
        verify(builderRepository, times(1)).delete(any());
    }

    @Test
    void deleteBuilder_EntityNotFoundException() {
        // Arrange
        Long builderId = 1L;
        when(builderRepository.findById(builderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> builderService.deleteBuilder(builderId));
    }

    @Test
    void createBuilderWithAddress_Success() {
        // Arrange
        Builder inputBuilder = new Builder();
        when(builderRepository.save(inputBuilder)).thenReturn(inputBuilder);
        when(builderRepository.findById(inputBuilder.getId())).thenReturn(Optional.of(inputBuilder));

        // Act
        Builder result = builderService.createBuilderWithAddress(inputBuilder);

        // Assert
        assertEquals(inputBuilder, result);
    }

    @Test
    void updateBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        Builder updatedBuilder = new Builder();
        updatedBuilder.setForename("Updated Forename");
        updatedBuilder.setSurname("Updated Surname");
        updatedBuilder.setName("Updated Name");
        updatedBuilder.setAddresses(Collections.emptyList());

        Builder existingBuilder = new Builder();
        existingBuilder.setAddresses(Collections.emptyList());
        existingBuilder.setId(builderId);

        when(builderRepository.findById(builderId)).thenReturn(Optional.of(existingBuilder));
        when(builderRepository.save(existingBuilder)).thenReturn(existingBuilder);

        // Act
        Builder result = builderService.updateBuilder(updatedBuilder, builderId);

        // Assert
        assertEquals("Updated Forename", result.getForename());
        assertEquals("Updated Surname", result.getSurname());
        assertEquals("Updated Name", result.getName());
    }

    @Test
    void removeAddressFromBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        Long addressId = 2L;

        Address addressToDelete = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(addressToDelete));

        // Act
        builderService.removeAddressFromBuilder(builderId, addressId);

        // Assert
        verify(addressRepository, times(1)).delete(addressToDelete);
    }

    @Test
    void getAllBuilders_Success() {
        // Arrange
        Iterable<Builder> mockBuildersIterable = Arrays.asList(new Builder(), new Builder());
        List<Builder> mockBuildersList = Arrays.asList(new Builder(), new Builder());

        when(builderRepository.findAll()).thenReturn(mockBuildersIterable);

        // Act
        List<Builder> result = builderService.getAllBuilders();

        // Assert
        assertEquals(mockBuildersList, result);
    }
}
