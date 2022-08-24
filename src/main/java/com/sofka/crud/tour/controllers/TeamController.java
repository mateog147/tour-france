package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.controllers.dtos.TeamDTO;
import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final Logger logger = Logger.getLogger("logger");
    @Autowired
    TeamService teamService;

    @GetMapping()
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("country/{countryName}")
    public ResponseEntity<List<Team>> getTeamsByCountry(@PathVariable("countryName") String countryName) {
        return ResponseEntity.ok(teamService.getAllTeamsByCountry(countryName));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Team>  getTeamById(@PathVariable("id") String id) {
        try{
            return  ResponseEntity.ok(teamService.getTeamById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<Team> postNewTeam(@RequestBody TeamDTO newTeam){
        try{
            return ResponseEntity.ok(teamService.createTeam(Team.builder()
                            .code(newTeam.getCode())
                            .name(newTeam.getName())
                            .country(newTeam.getCountry())
                            .build()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") String id){
        try{
            String operationStatus = teamService.deleteTeamById(id);
            return ResponseEntity.ok(operationStatus);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team>  updateTeamInfo(@PathVariable("id") String id, @RequestBody TeamDTO team){
        try {
            Team updatedTeam = Team.builder()
                    .code(team.getCode())
                    .name(team.getName())
                    .country(team.getCountry())
                    .build();
            return ResponseEntity.ok(teamService.updateTeam(id, updatedTeam));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
