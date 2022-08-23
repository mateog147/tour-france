package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class TeamService {
    @Autowired
    TeamRepository repository;

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
}
