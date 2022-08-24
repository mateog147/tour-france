package com.sofka.crud.tour.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Document
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Team {

    private  String id;
    private String name;
    private String code;
    private String country;
    private Set<Cyclist> riders;

    public Team(String name, String code, String country) {
        this.name = Objects.requireNonNull(name);
        this.code = Objects.requireNonNull(code);
        this.country = Objects.requireNonNull(country);
        this.riders = new HashSet<>();
    }

    public Team() {
        this.riders = new HashSet<>();
    }

    public void addCyclist(Cyclist cyclist){
        Objects.requireNonNull(cyclist);
        if(this.riders.size() >= 8 ){
            throw new IllegalStateException("Team riders number is already the maximum");
        }
        this.riders.add(cyclist);
    }

    public void removeCyclist(Cyclist cyclist){
        Objects.requireNonNull(cyclist);
        try{
            this.riders.remove(cyclist);
        } catch (Exception e) {
            throw new IllegalStateException("Can not remove the cyclist from the given team");
        }
    }

    public void updateRider(Cyclist updatedCyclist) {
        try {
            this.riders = riders.stream()
                    .map(cyclist -> {
                        if (cyclist.getId().equals(updatedCyclist.getId())){
                            return updatedCyclist;
                        }
                        return cyclist;
                    }).collect(Collectors.toSet());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
