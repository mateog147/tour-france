package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping()
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
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
    public ResponseEntity<Team> postNewTeam(@RequestBody Team newTeam){
        try{
            return ResponseEntity.ok(teamService.createTeam(newTeam));
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
    public ResponseEntity<Team>  updateTeamInfo(@PathVariable("id") String id, @RequestBody Team team){
        try {
            return ResponseEntity.ok(teamService.updateTeam(id, team));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
