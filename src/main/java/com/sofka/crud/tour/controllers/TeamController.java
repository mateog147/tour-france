package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping()
    public ArrayList<Team> getTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping(path = "/{id}")
    public Team getTeamById(@PathVariable("id") String id) {
        return teamService.getTeamById(id);
    }

    @PostMapping()
    public Team postNewTeam(@RequestBody Team newTeam){
        return teamService.createTeam(newTeam);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteTeam(@PathVariable("id") String id){
        return teamService.deleteTeamById(id);
    }


    @PutMapping("/{id}")
    public Team updateTeamInfo(@PathVariable("id") String id, @RequestBody Team team){
        return teamService.updateTeam(id, team);
    }
}
