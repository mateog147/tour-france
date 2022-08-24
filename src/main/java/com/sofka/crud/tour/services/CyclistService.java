package com.sofka.crud.tour.services;

import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.repositories.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class CyclistService {

    @Autowired
    CyclistRepository repository;

    public Cyclist createCyclist(Cyclist newCyclist){
        Objects.requireNonNull(newCyclist.getFullName());
        Objects.requireNonNull(newCyclist.getFullName());
        verifyRiderNumber(newCyclist.getRiderNumber());

        return repository.save(newCyclist.toBuilder()
                .nationality(newCyclist.getNationality().toUpperCase(Locale.ROOT))
                .build());
    }

    public List<Cyclist> getAll() {
        return repository.findAll();
    }

    public List<Cyclist>getByCountry(String countryName){
        return repository.findAll()
                .stream()
                .filter(cyclist -> cyclist.getNationality().equals(countryName.toUpperCase()))
                .toList();
    }

    public String deleteById(String id) {
        Cyclist cyclist = repository.findById(id)
                .orElseThrow();
        if(Objects.isNull(cyclist.getTeamId())){
            repository.deleteById(id);
            return "Cyclist delete id:" + id;
        }
        throw new IllegalArgumentException("The rider is currently in a team");
    }

    public Cyclist updateInfo(Cyclist updatedCyclist, Boolean isTheSameRiderNumber) {

        if(Boolean.FALSE.equals(isTheSameRiderNumber)){
            verifyRiderNumber(updatedCyclist.getRiderNumber());
        }
        return repository.save(updatedCyclist
                .toBuilder()
                .nationality(updatedCyclist.getNationality().toUpperCase(Locale.ROOT))
                .build());
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
