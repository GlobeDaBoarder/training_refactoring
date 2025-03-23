package com.andersen.training.training_refactoring.repo;

import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.entity.RaceResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RaceResultRepoTest {

    @Autowired
    RaceResultRepo raceResultRepo;

    @Autowired
    DriverRepo driverRepo;

    Driver lewis;
    Driver lando;

    @BeforeEach
    void setUp() {
        lewis = Driver.builder().driverName("Lewis Hamilton").carBrand("Mercedes").build();
        lando = Driver.builder().driverName("Lando Norris").carBrand("McLaren").build();

        driverRepo.saveAll(List.of(lewis, lando));

        List<RaceResult> raceResults = List.of(
                RaceResult.builder().driver(lewis).finishingPosition(1).build(),
                RaceResult.builder().driver(lewis).finishingPosition(1).build(),
                RaceResult.builder().driver(lewis).finishingPosition(2).build(),
                RaceResult.builder().driver(lando).finishingPosition(1).build());

        raceResultRepo.saveAll(raceResults);
    }

    @Test
    void countAllByDriver_withLewis_returnsCountOf3() {
        long lewisResultCount = raceResultRepo.countAllByDriver(lewis);

        assertThat(lewisResultCount).isEqualTo(3);
    }

    @Test
    void countAllByDriver_withLando_returnsCountOf1() {
        long landoResultCount = raceResultRepo.countAllByDriver(lando);

        assertThat(landoResultCount).isEqualTo(1);
    }

    @Test
    void countAllByDriverAndFinishingPositionIs_withLewisAndFinishingPosition2_returnsCountOf1() {
        long lewisFinishingSecondCount = raceResultRepo.countAllByDriverAndFinishingPositionIs(lewis, 2);

        assertThat(lewisFinishingSecondCount).isEqualTo(1);
    }

    @Test
    void countAllByDriverAndFinishingPositionIsFirst_withLewis_returnsCountOf2() {
        long lewisFinishingFirstCount = raceResultRepo.countAllByDriverAndFinishingPositionIsFirst(lewis);

        assertThat(lewisFinishingFirstCount).isEqualTo(2);
    }
}
