package com.voongc.postmybuild.controller;

import com.voongc.postmybuild.data.entity.Builder;
import com.voongc.postmybuild.service.BuilderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuilderControllerTest {

    @Mock
    private BuilderService builderService;

    @InjectMocks
    private BuilderController builderController;

    @Test
    void getBuilderById_Success() {
        // Arrange
        Long builderId = 1L;
        Builder mockBuilder = new Builder();
        when(builderService.getBuilder(builderId)).thenReturn(mockBuilder);

        // Act
        ResponseEntity<Builder> response = builderController.getBuilderById(builderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBuilder, response.getBody());
    }

    @Test
    void getBuilderById_EntityNotFoundException() {
        // Arrange
        Long builderId = 1L;
        when(builderService.getBuilder(builderId)).thenThrow(EntityNotFoundException.class);

        // Act
        ResponseEntity<Builder> response = builderController.getBuilderById(builderId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void createBuilder_Success() {
        // Arrange
        Builder inputBuilder = new Builder();
        Builder mockBuilder = new Builder();
        when(builderService.createBuilderWithAddress(inputBuilder)).thenReturn(mockBuilder);

        // Act
        ResponseEntity<Builder> response = builderController.createBuilder(inputBuilder);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockBuilder, response.getBody());
    }

    @Test
    void getAllBuilders_Success() {
        // Arrange
        List<Builder> mockBuilders = Arrays.asList(new Builder(), new Builder());
        when(builderService.getAllBuilders()).thenReturn(mockBuilders);

        // Act
        ResponseEntity<List<Builder>> response = builderController.getAllBuilders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBuilders, response.getBody());
    }

    @Test
    void updateBuilder_Success() {
        // Arrange
        Long builderId = 1L;
        Builder updatedBuilder = new Builder();
        Builder mockBuilder = new Builder();
        when(builderService.updateBuilder(updatedBuilder, builderId)).thenReturn(mockBuilder);

        // Act
        ResponseEntity<Builder> response = builderController.updateBuilder(updatedBuilder, builderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBuilder, response.getBody());
    }

    @Test
    void updateBuilder_EntityNotFoundException() {
        // Arrange
        Long builderId = 1L;
        Builder updatedBuilder = new Builder();
        when(builderService.updateBuilder(updatedBuilder, builderId)).thenThrow(EntityNotFoundException.class);

        // Act
        ResponseEntity<Builder> response = builderController.updateBuilder(updatedBuilder, builderId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void deleteBuilderById_Success() {
        // Arrange
        Long builderId = 1L;

        // Act
        ResponseEntity<Void> response = builderController.deleteBuilderById(builderId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(builderService, times(1)).deleteBuilder(builderId);
    }

    @Test
    void deleteBuilderById_EntityNotFoundException() {
        // Arrange
        Long builderId = 1L;
        doThrow(EntityNotFoundException.class).when(builderService).deleteBuilder(builderId);

        // Act
        ResponseEntity<Void> response = builderController.deleteBuilderById(builderId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(builderService, times(1)).deleteBuilder(builderId);
    }
}
