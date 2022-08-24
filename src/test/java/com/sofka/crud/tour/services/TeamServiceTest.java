package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.TeamRepository;
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
class TeamServiceTest {

    @Mock
    TeamRepository repository;

    @InjectMocks
    TeamService service;

    @Test
    void getAllTeams() {
        //arrange
        List<Team> teams = Arrays.asList(
                Team.builder().id("t1").build(),
                Team.builder().id("t2").build(),
                Team.builder().id("t3").build()
        );
        when(repository.findAll()).thenReturn(teams);

        //Act
        List<Team> obtainedList = service.getAllTeams();

        //assert
        Assertions.assertEquals(3, obtainedList.size());
    }

    @Test
    void createTeam() {
        //arrange
        Team team =  Team.builder()
                .id("t1")
                .name("team")
                .code("xxx")
                .country("country")
                .build();
        when(repository.save(Mockito.any(Team.class))).then(e -> e.getArgument(0));

        //act
        Team saved = service.createTeam(team);

        //assert
        Assertions.assertEquals("t1", saved.getId());
        Assertions.assertEquals("team", saved.getName());
        Assertions.assertEquals("country", saved.getCountry());
    }

    @Test
    void createTeam_noValidCode() {
        //arrange
        Team team =  Team.builder()
                .id("t1")
                .name("name")
                .code("xxx")
                .build();
        when(repository.findByCode("xxx")).thenReturn(Optional.of(team));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createTeam(team));
    }

    @Test
    void getTeamById() {
        //arrange
        Team team =  Team.builder()
                .id("t1")
                .name("team")
                .code("xxx")
                .country("country")
                .build();
        when(repository.findById("t1")).thenReturn(Optional.of(team));
        //act
        Team founded = service.getTeamById("t1");
        //assert
        Assertions.assertEquals("t1", founded.getId());
        Assertions.assertEquals("country", founded.getCountry());
    }

    @Test
    void deleteTeamById() {
        Team team = Team.builder()
                .id("t1")
                .build();
        when(repository.findById("t1")).thenReturn(Optional.of(team));
        //act
        String str = service.deleteTeamById("t1");
        //assert
        Assertions.assertEquals("Team deleted - Id: t1",str);
    }

    @Test
    void updateTeam() {
        //arrange
        Team team =  Team.builder()
                .id("t1")
                .name("team")
                .code("xxx")
                .country("country")
                .build();
        when(repository.findById("t1")).thenReturn(Optional.of(team));
        when(repository.save(Mockito.any(Team.class))).then(e -> e.getArgument(0));
        //act
        Team updated = service.updateTeam("t1", team);
        //assert
        Assertions.assertEquals("t1", updated.getId());
        Assertions.assertEquals("xxx", updated.getCode());
    }

    @Test
    void getAllTeamsByCountry() {
        //arrange
        List<Team> teams = Arrays.asList(
                Team.builder().id("t1").country("country1").build(),
                Team.builder().id("t2").country("country2").build(),
                Team.builder().id("t3").country("country3").build(),
                Team.builder().id("t3").country("country1").build()
        );
        when(repository.findAll()).thenReturn(teams);

        //Act
        List<Team> obtainedList = service.getAllTeamsByCountry("country1");

        //assert
        Assertions.assertEquals(2, obtainedList.size());
    }
}