package com.andersen.training.training_refactoring.repo;

import com.andersen.training.training_refactoring.entity.RaceEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEventRepo extends CrudRepository<RaceEvent, Long> {
}
