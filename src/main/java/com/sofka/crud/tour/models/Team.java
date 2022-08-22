package com.sofka.crud.tour.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
        this.riders.add(cyclist);
    }
}
