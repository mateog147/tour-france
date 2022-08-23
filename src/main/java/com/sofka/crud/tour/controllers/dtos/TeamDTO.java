package com.sofka.crud.tour.controllers.dtos;

import com.sofka.crud.tour.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TeamDTO {
    private  String id;
    private String name;
    private String code;
    private String country;
    private Set<Cyclist> riders;
}
