package com.sofka.crud.tour.repositories;

import com.sofka.crud.tour.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface TeamRepository extends MongoRepository<Team, String> {
    ArrayList<Team> findAll();
}
