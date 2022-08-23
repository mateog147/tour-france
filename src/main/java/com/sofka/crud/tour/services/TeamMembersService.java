package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.CyclistRepository;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TeamMembersService {
    @Autowired
    CyclistRepository cyclistRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CyclistService cyclistService;

    public List<Cyclist> getTeamRidersByCode(String code){
        try{
            Team team = teamRepository.findByCode(code)
                    .orElseThrow();
            return cyclistRepository.findAll()
                    .stream()
                    .filter(cyclist -> cyclist.getTeamId().equals(team.getId()))
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String addTeamRider(String id, Cyclist rider) {
        Objects.requireNonNull(id);
        Team team  = teamRepository.findById(id)
                .orElseThrow();
        rider.setTeamId(team.getId());

        try{
            Cyclist cyclist = cyclistService.createCyclist(rider);
            team.addCyclist(cyclist);
            teamRepository.save(team);
            return cyclist.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }
}
