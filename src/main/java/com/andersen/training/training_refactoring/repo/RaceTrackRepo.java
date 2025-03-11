package com.andersen.training.training_refactoring.repo;

import com.andersen.training.training_refactoring.entity.RaceTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceTrackRepo extends JpaRepository<RaceTrack, Long> {
}
