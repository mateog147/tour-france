package com.sofka.crud.tour.services;

import com.sofka.crud.tour.controllers.dtos.RiderTransferDTO;
import com.sofka.crud.tour.models.Cyclist;
import com.sofka.crud.tour.models.Team;
import com.sofka.crud.tour.repositories.CyclistRepository;
import com.sofka.crud.tour.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class TeamMembersService {
    @Autowired
    CyclistRepository cyclistRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CyclistService cyclistService;

    public List<Cyclist> getTeamRidersByCode(String code){
        try{
            Team team = teamRepository.findByCode(code)
                    .orElseThrow();
            return cyclistRepository.findAll()
                    .stream()
                    .filter(cyclist -> cyclist.getTeamId().equals(team.getId()))
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String addTeamRider(String id, Cyclist rider) {
        Objects.requireNonNull(id);
        Team team  = teamRepository.findById(id)
                .orElseThrow();
        rider.setTeamId(team.getId());

        try{
            Cyclist cyclist = cyclistService.createCyclist(rider);
            team.addCyclist(cyclist);
            teamRepository.save(team);
            return cyclist.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public String removeTeamRider(String id, String riderId) {
        Team team = teamRepository.findById(id)
                .orElseThrow();
        Cyclist cyclist = cyclistRepository.findById(riderId)
                .orElseThrow();
        try{
            team.removeCyclist(cyclist);
            cyclist.setTeamId(null);
            cyclistRepository.save(cyclist);
            teamRepository.save(team);
            return "Rider remove from the team";
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String transferRider(RiderTransferDTO transfer) {
        Team oldTeam =teamRepository.findById(transfer.getCurrentTeamId())
                .orElseThrow();

        Team newTeam =teamRepository.findById(transfer.getNewTeamId())
                .orElseThrow();

        Cyclist rider = cyclistRepository.findById(transfer.getRiderId())
                .orElseThrow();

        try{
            oldTeam.removeCyclist(rider);
            rider.setTeamId(newTeam.getId());
            newTeam.addCyclist(rider);

            teamRepository.saveAll(Arrays.asList(oldTeam, newTeam));
            cyclistRepository.save(rider);
            return "Transfer successful:" + transfer.getRiderId();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Cyclist updateCyclistInfo(String id, Cyclist cyclist) {
        Objects.requireNonNull(cyclist);
        Cyclist oldCyclist = cyclistRepository.findById(id)
                .orElseThrow();
        Boolean isTheSameRiderNumber =oldCyclist.getRiderNumber().equals(cyclist.getRiderNumber());

        Cyclist updatedCyclist = cyclistService.updateInfo(
                cyclist.toBuilder().id(oldCyclist.getId()).teamId(oldCyclist.getTeamId()).nationality(cyclist.getNationality().toUpperCase(Locale.ROOT)).build(),
                isTheSameRiderNumber);

        updateTeamRiderInfo(updatedCyclist);

        return updatedCyclist;
    }

    private void updateTeamRiderInfo(Cyclist updatedCyclist) {
        Team team = teamRepository.findById(updatedCyclist.getTeamId())
                .orElseThrow();
        team.updateRider(updatedCyclist);
        teamRepository.save(team);
    }
}
