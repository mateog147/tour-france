package com.sofka.crud.tour.repositories;

import com.sofka.crud.tour.models.Cyclist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CyclistRepository extends MongoRepository<Cyclist, String> {
}
