package com.sofka.crud.tour.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RiderTransferDTO {
    String currentTeamId;
    String newTeamId;
    String riderId;
}
