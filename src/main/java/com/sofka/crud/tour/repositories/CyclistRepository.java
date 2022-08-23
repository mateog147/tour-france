package com.sofka.crud.tour.repositories;

import com.sofka.crud.tour.models.Cyclist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CyclistRepository extends MongoRepository<Cyclist, String> {
    Optional<Cyclist> findByRiderNumber(Integer riderNumber);
}
