package com.sofka.crud.tour.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CyclistDTO {
    private String id;
    private String teamId;
    private String fullName;
    private Integer riderNumber;
    private String nationality;
}
