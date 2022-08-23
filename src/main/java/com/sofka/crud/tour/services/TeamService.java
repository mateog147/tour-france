package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeamService {
    @Autowired
    TeamRepository repository;

    @Autowired
    CyclistService cyclistService;

    public ArrayList<Team> getAllTeams(){
        return repository.findAll();
    }

    public Team createTeam(Team newTeam) {
        Objects.requireNonNull(newTeam);
        if(newTeam.getName().isEmpty()){
            throw new IllegalArgumentException("Name value can not be empty");
        }
        return repository.save(newTeam);
    }

    public Team getTeamById(String id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public String deleteTeamById(String id) {
        if(Boolean.TRUE.equals(repository.findById(id).isPresent())){
            repository.deleteById(id);
            return "Team deleted - Id: " + id;
        }
        throw new IllegalArgumentException("Error: Team Not Found:" + id);
    }

    public Team updateTeam(String id, Team team) {
        Objects.requireNonNull(team.getName());
        if(Boolean.TRUE.equals(repository.findById(id).isPresent())){
            team.setId(id);
            return repository.save(team);
        }
        throw new IllegalArgumentException("Param error can not update team with id:" + id);
    }

    public List<Team> getAllTeamsByCountry(String country){
        return repository.findAll()
                .stream()
                .filter(team -> team.getCountry().equals(country))
                .toList();
    }

    public String addTeamRider(String id, Cyclist rider) {
        Objects.requireNonNull(id);
        Team team  = getTeamById(id);
        rider.setTeamId(team.getId());
        Cyclist cyclist = cyclistService.createCyclist(rider);
        team.addCyclist(cyclist);
        repository.save(team);
        return cyclist.getId();
    }
}
