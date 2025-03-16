package com.andersen.training.training_refactoring.repo;

import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.entity.RaceResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepo extends CrudRepository<RaceResult, Long> {

    long countAllByDriver(Driver driver);

    long countAllByDriverAndFinishingPositionIs(Driver driver, long finishingPosition);

    default long countAllByDriverAndFinishingPositionIsFirst(Driver driver) {
        return countAllByDriverAndFinishingPositionIs(driver, 1);
    }
}
