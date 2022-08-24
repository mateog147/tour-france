package com.sofka.crud.tour.services;

import com.sofka.crud.tour.controllers.dtos.RiderTransferDTO;
import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.CyclistRepository;
import com.sofka.crud.tour.repositories.TeamRepository;
import com.sofka.crud.tour.services.CyclistService;
import com.sofka.crud.tour.services.TeamMembersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamMembersServiceTest {

    @Mock
    CyclistRepository cyclistRepository;

    @Mock
    TeamRepository teamRepository;

    @Mock
    CyclistService cyclistService;

    @InjectMocks
    TeamMembersService service;

    @Test
    void getTeamRidersByCode() {
        //arrange
        List<Cyclist> cyclistList = Arrays.asList(
                Cyclist.builder().id("c1").teamId("t1").build(),
                Cyclist.builder().id("c2").teamId("t2").build(),
                Cyclist.builder().id("c3").teamId("t1").build()
        );
        when(cyclistRepository.findAll()).thenReturn(cyclistList);

        Team team =  Team.builder()
                .id("t1")
                .name("team")
                .code("xxx")
                .country("country")
                .build();
        when(teamRepository.findByCode("xxx")).thenReturn(Optional.of(team));

        //Act
        List<Cyclist> list = service.getTeamRidersByCode("xxx");

        //Assert
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void addTeamRider() {
        //Assert
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .fullName("name")
                .nationality("country")
                .riderNumber(123)
                .build();
        Team team =  Team.builder()
                .id("t1")
                .name("team")
                .code("xxx")
                .country("country")
                .riders(new HashSet<>())
                .build();
        when(teamRepository.findById("t1")).thenReturn(Optional.of(team));
        when(teamRepository.save(Mockito.any(Team.class))).then(e -> e.getArgument(0));
        when(cyclistService.createCyclist(Mockito.any(Cyclist.class))).then(e -> e.getArgument(0));
        //Act
        String str = service.addTeamRider("t1",cyclist);

        //assert
        Assertions.assertEquals("c1", str);
    }

    @Test
    void removeTeamRider() {
        //arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .fullName("name")
                .nationality("country")
                .riderNumber(123)
                .build();

        Set<Cyclist> riders = new HashSet<>();
        riders.add(cyclist);

        Team team =  Team.builder()
                .id("t1")
                .name("team")
                .code("xxx")
                .country("country")
                .riders(riders)
                .build();
        when(teamRepository.findById("t1")).thenReturn(Optional.of(team));
        when(cyclistRepository.findById("c1")).thenReturn(Optional.of(cyclist));
        when(teamRepository.save(Mockito.any(Team.class))).then(e -> e.getArgument(0));
        when(cyclistRepository.save(Mockito.any(Cyclist.class))).then(e -> e.getArgument(0));

        //act
        String str = service.removeTeamRider("t1", "c1");

        //Assert
        Assertions.assertEquals("Rider remove from the team", str);
    }

    @Test
    void transferRider() {
        //arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .fullName("name")
                .nationality("country")
                .riderNumber(123)
                .build();
        when(cyclistRepository.findById("c1")).thenReturn(Optional.of(cyclist));

        Set<Cyclist> riders = new HashSet<>();
        riders.add(cyclist);

        Team oldTeam =  Team.builder()
                .id("t1")
                .name("oldTeam")
                .code("xxx")
                .country("country")
                .riders(riders)
                .build();
        when(teamRepository.findById("t1")).thenReturn(Optional.of(oldTeam));

        Team newTeam =  Team.builder()
                .id("t2")
                .name("newTeam")
                .code("xxx")
                .country("country")
                .riders(new HashSet<Cyclist>())
                .build();
        when(teamRepository.findById("t2")).thenReturn(Optional.of(newTeam ));
        when(teamRepository.saveAll(Arrays.asList(oldTeam, newTeam))).thenReturn(Arrays.asList(oldTeam, newTeam));
        when(cyclistRepository.save(Mockito.any(Cyclist.class))).then(e -> e.getArgument(0));

        RiderTransferDTO transfer = RiderTransferDTO.builder()
                .riderId("c1")
                .currentTeamId("t1")
                .newTeamId("t2")
                .build();
        //act
        String str = service.transferRider(transfer);

        //Assert
        Assertions.assertEquals("Transfer successful:c1", str);

    }

    @Test
    void updateCyclistInfo(){
        //arrange
        Cyclist cyclist =  Cyclist.builder()
                .id("c1")
                .fullName("name")
                .nationality("country")
                .teamId("t1")
                .riderNumber(123)
                .build();
        when(cyclistRepository.findById("c1")).thenReturn(Optional.of(cyclist));

        Set<Cyclist> riders = new HashSet<>();
        riders.add(cyclist);

        Team team =  Team.builder()
                .id("t1")
                .name("Team")
                .code("xxx")
                .country("country")
                .riders(riders)
                .build();
        when(teamRepository.findById("t1")).thenReturn(Optional.of(team));
        when(cyclistService.updateInfo(Mockito.any(Cyclist.class),Mockito.anyBoolean())).thenReturn(cyclist);
        when(teamRepository.save(Mockito.any(Team.class))).then(e -> e.getArgument(0));

        //Act
        Cyclist updated = service.updateCyclistInfo("c1",cyclist);

        //assert
        Assertions.assertEquals("name", updated.getFullName());
    }

}