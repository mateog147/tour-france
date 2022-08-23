package com.sofka.crud.tour.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Cyclist {
    private String id;
    private String teamId;
    private String fullName;
    private Integer riderNumber;
    private String nationality;
}
