package com.andersen.training.training_refactoring.repo;

import com.andersen.training.training_refactoring.entity.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepo extends JpaRepository<RaceResult, Long> {
}
