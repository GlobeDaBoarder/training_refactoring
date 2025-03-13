package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.DriverResultDto;
import com.andersen.training.training_refactoring.dto.F1RaceResultsDto;
import com.andersen.training.training_refactoring.entity.F1RaceEntity;
import com.andersen.training.training_refactoring.repo.F1RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Deprecated
/**
 * @deprecated in favour of {@code DriverService, TODO add deprecated docs }
 */
public class F1RaceService {

    private final F1RaceRepository raceRepo;

    public void saveRaces(List<F1RaceResultsDto> races) {
        for (F1RaceResultsDto race : races) {
            for (DriverResultDto driver: race.results()) {
                F1RaceEntity entity = new F1RaceEntity();
                entity.setDate(race.date());
                entity.setCountry(race.country());
                entity.setTrack(race.track());
                entity.setDriver(driver.driver());
                entity.setCar(driver.car());
                entity.setPosition(driver.position());
                raceRepo.save(entity);
            }
        }
    }

    public Double calculateWinningChanceForDriver(String driver) {
        List<F1RaceEntity> allRaces = raceRepo.findAll();
        int total = 0;
        int wins = 0;
        for (F1RaceEntity race : allRaces) {
            if (race.getDriver().equalsIgnoreCase(driver)) {
                total++;
                if (race.getPosition() == 1) {
                    wins++;
                }
            }
        }
        return total == 0 ? 0 : (double) wins / total * 100;
    }
}
