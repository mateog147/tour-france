package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.repositories.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CyclistService {

    @Autowired
    CyclistRepository repository;

    public Cyclist createCyclist(Cyclist newCyclist){
        Objects.requireNonNull(newCyclist.getFullName());
        Objects.requireNonNull(newCyclist.getFullName());
        verifyRiderNumber(newCyclist.getRiderNumber());

        return repository.save(newCyclist);
    }

    private void verifyRiderNumber(Integer riderNumber) {
        int length = String.valueOf(riderNumber).length();

        if(repository.findByRiderNumber(riderNumber).isPresent()){
            throw new IllegalArgumentException("Rider number is already used");
        }

        if(length > 3){
            throw new IllegalArgumentException("Rider number outside the boundaries of 3 digits");
        }
    }
}
