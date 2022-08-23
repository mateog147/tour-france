package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/team")
public class TeamMembersController {
    @Autowired
    TeamService teamService;

    @PostMapping("/{id}")
    public ResponseEntity<String> addTeamRider(@PathVariable("id") String id, @RequestBody Cyclist rider){
        try {
            return ResponseEntity.ok(teamService.addTeamRider(id, rider));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
