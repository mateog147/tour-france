package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.repositories.CyclistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CyclistServiceTest {

    @Mock
    CyclistRepository repository;

    @InjectMocks
    CyclistService service;

    @Test
    void get_all_cyclist_successful(){
        //Arrange
        List<Cyclist> cyclistList = Arrays.asList(
                Cyclist.builder().id("c1").build(),
                Cyclist.builder().id("c2").build());
        when(repository.findAll()).thenReturn(cyclistList);
        //Act
        List<Cyclist> listObtained = service.getAll();
        //Assert
        Assertions.assertEquals(2, listObtained.size());
    }

}