package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

        validateCode(newTeam.getCode());
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
        Team oldTeam = repository.findById(id)
                .orElseThrow();
        Boolean isTheSameCode = oldTeam.getCode().equals(team.getCode());

        if(Boolean.FALSE.equals(isTheSameCode)){
            validateCode(team.getCode());
        }
            team.setId(id);
            team.setRiders(oldTeam.getRiders());
            return repository.save(team);
    }

    public List<Team> getAllTeamsByCountry(String country){
        return repository.findAll()
                .stream()
                .filter(team -> team.getCountry().equals(country))
                .toList();
    }

    private void validateCode(String code) {

        if(code.length() > 3){
            throw new IllegalArgumentException("Team code is not valid");
        }

        if(repository.findByCode(code).isPresent()){
            throw new IllegalArgumentException("Team code is already used");
        }
    }

}
