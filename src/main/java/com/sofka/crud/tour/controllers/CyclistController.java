package com.sofka.crud.tour.controllers;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/cyclist")
public class CyclistController {
    @Autowired
    CyclistService cyclistService;

    @GetMapping()
    public ResponseEntity<List<Cyclist>> getAllCyclists(){
        return ResponseEntity.ok(cyclistService.getAll());
    }

    @GetMapping("country/{countryName}")
    public ResponseEntity<List<Cyclist>> getAllCyclistsByCountry(@PathVariable("countryName") String countryName){
        return ResponseEntity.ok(cyclistService.getByCountry(countryName));
    }
}
