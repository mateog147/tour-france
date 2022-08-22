package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class TeamService {
    @Autowired
    TeamRepository repository;

    public ArrayList<Team> getAllTeams(){
        return repository.findAll();
    }

    public Team createTeam(Team newTeam) {
        return repository.save(newTeam);
    }

    public Team getTeamById(String id) {
        return repository.findById(id)
                .orElse(new Team());
    }

    public String deleteTeamById(String id) {
        Boolean wasDelete = repository.findById(id).isPresent();
        if(Boolean.TRUE.equals(wasDelete)){
            repository.deleteById(id);
            return "Team deleted - Id: " + id;
        }
        return "Error: Team Not Found";
    }

    public Team updateTeam(String id, Team team) {
        Boolean wasDelete = repository.findById(id).isPresent();
        if(Boolean.TRUE.equals(wasDelete)){
            team.setId(id);
            repository.save(team);
        }
        return new Team();
    }
}
