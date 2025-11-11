package ru.lounge.services;

import ru.lounge.dto.MixDto;
import ru.lounge.models.Mix;

import java.util.List;
import java.util.Optional;

public interface MixService {
    Optional<MixDto> findById(long id);

    List<MixDto> findAll();

    List<MixDto> findByTobaccoId(long id);

    Mix save(MixDto mix);

    void deleteById(long id);
}
