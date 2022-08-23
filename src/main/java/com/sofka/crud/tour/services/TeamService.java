package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepository repository;

    public List<Team> getAllTeams(){
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
        Optional<Team> oldTeam = repository.findById(id);
        if(Boolean.TRUE.equals(oldTeam.isPresent())){
            team.setId(id);
            team.setRiders(oldTeam.get().getRiders());
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

}
