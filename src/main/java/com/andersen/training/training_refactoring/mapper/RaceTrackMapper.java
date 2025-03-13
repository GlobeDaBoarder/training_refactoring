package com.andersen.training.training_refactoring.mapper;

import com.andersen.training.training_refactoring.dto.RaceTrackCreationDto;
import com.andersen.training.training_refactoring.entity.RaceTrack;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RaceTrackMapper {

    RaceTrack toDriverEntity(RaceTrackCreationDto raceTrackCreationDto);
}
