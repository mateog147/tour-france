package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.repositories.CyclistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CyclistServiceTest {

    @Mock
    CyclistRepository repository;

    @InjectMocks
    CyclistService service;

    @Test
    void create_new_cyclist_successful(){
        //Arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .fullName("name")
                .nationality("country")
                .riderNumber(123)
                .build();
        when(repository.save(Mockito.any(Cyclist.class))).then(e -> e.getArgument(0));
        //Act
        Cyclist saved = service.createCyclist(cyclist);
        //Assert
        Assertions.assertEquals("c1", saved.getId());
        Assertions.assertEquals("name", saved.getFullName());
        Assertions.assertEquals("COUNTRY", saved.getNationality());
        Assertions.assertEquals(123, saved.getRiderNumber());
    }
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
    @Test
    void get_all_cyclist_by_country_successful(){
        //Arrange
        List<Cyclist> cyclistList = Arrays.asList(
                Cyclist.builder().id("c1").nationality("COUNTRY").build(),
                Cyclist.builder().id("c2").nationality("COUNTRY2").build(),
                Cyclist.builder().id("c3").nationality("COUNTRY").build(),
                Cyclist.builder().id("c4").nationality("COUNTRY2").build());
        when(repository.findAll()).thenReturn(cyclistList);
        //Act
        List<Cyclist> listObtained = service.getByCountry("COUNTRY");
        //Assert
        Assertions.assertEquals(2, listObtained.size());
        Assertions.assertEquals("c1", listObtained.get(0).getId());
        Assertions.assertEquals("c3", listObtained.get(1).getId());
    }

    @Test
    void delete_cyclist_NO_successful(){
        //Arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .teamId("t1")
                .build();
        when(repository.findById("c1")).thenReturn(Optional.of(cyclist));

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteById("c1"));
    }

    @Test
    void delete_cyclist_successful(){
        //Arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .teamId(null)
                .build();
        when(repository.findById("c1")).thenReturn(Optional.of(cyclist));
        //act
        String str = service.deleteById("c1");
        //assert
        Assertions.assertEquals("Cyclist delete id:c1",str);
    }

    @Test
    void update_cyclist_successful(){
        //Arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .fullName("name")
                .nationality("country")
                .riderNumber(123)
                .build();
        when(repository.save(Mockito.any(Cyclist.class))).then(e -> e.getArgument(0));
        //Act
        Cyclist saved = service.updateInfo(cyclist, true);
        //Assert
        Assertions.assertEquals("c1", saved.getId());
        Assertions.assertEquals("name", saved.getFullName());
        Assertions.assertEquals("COUNTRY", saved.getNationality());
        Assertions.assertEquals(123, saved.getRiderNumber());
    }

    @Test
    void update_cyclist_successful_rider_number_no_valid(){
        //Arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .teamId("t1")
                .riderNumber(123)
                .build();
        when(repository.findByRiderNumber(123)).thenReturn(Optional.of(cyclist));
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.updateInfo(cyclist, false));
    }

}