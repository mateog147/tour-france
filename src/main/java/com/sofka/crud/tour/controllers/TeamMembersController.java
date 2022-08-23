package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.services.TeamMembersService;
import com.sofka.crud.tour.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TeamMembersController {
    @Autowired
    TeamMembersService teamMembersService;

    @PostMapping("/team/{id}")
    public ResponseEntity<String> addTeamRider(@PathVariable("id") String id, @RequestBody Cyclist rider){
        try {
            return ResponseEntity.ok(teamMembersService.addTeamRider(id, rider));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/cyclist/team/{code}")
    public ResponseEntity<List<Cyclist>> getRidersByTeamCode(@PathVariable("code") String code){
        try{
            return ResponseEntity.ok(teamMembersService.getTeamRidersByCode(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
