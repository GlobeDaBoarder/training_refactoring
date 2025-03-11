package com.andersen.training.training_refactoring.repo;

import com.andersen.training.training_refactoring.entity.F1RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface F1RaceRepository extends JpaRepository<F1RaceEntity, Long> {}
