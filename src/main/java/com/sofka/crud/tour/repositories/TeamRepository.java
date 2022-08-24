package com.sofka.crud.tour.repositories;

import com.sofka.crud.tour.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, String> {
    Optional<Team> findByCode(String code);
}
