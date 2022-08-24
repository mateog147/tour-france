package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.controllers.dtos.CyclistDTO;
import com.sofka.crud.tour.controllers.dtos.RiderTransferDTO;
import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.services.TeamMembersService;
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
    public ResponseEntity<String> addTeamRider(@PathVariable("id") String id, @RequestBody CyclistDTO rider){
        try {
            return ResponseEntity.ok(teamMembersService.addTeamRider(id,Cyclist.builder()
                            .fullName(rider.getFullName())
                            .riderNumber(rider.getRiderNumber())
                            .nationality(rider.getNationality())
                            .build())
            );
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

    @PutMapping("/team/remove/{id}")
    public ResponseEntity<String> removeTeamRider(@PathVariable("id") String id, @RequestBody CyclistDTO rider){
        try {
            return ResponseEntity.ok(teamMembersService.removeTeamRider(id, rider.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/team/transfer")
    public ResponseEntity<String> transferTeamRider(@RequestBody RiderTransferDTO transfer){
        try {
            return ResponseEntity.ok(teamMembersService.transferRider(transfer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/cyclist/{id}")
    public ResponseEntity<Cyclist> updateCyclistBasicInfo(@PathVariable("id") String id, @RequestBody CyclistDTO rider){
        try {
            return ResponseEntity.ok(teamMembersService.updateCyclistInfo(id, Cyclist.builder()
                    .nationality(rider.getNationality())
                    .riderNumber(rider.getRiderNumber())
                    .fullName(rider.getFullName())
                    .build()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
