package ru.lounge.services;

import ru.lounge.dto.MixDto;

import java.util.List;

public interface MixService {
    MixDto findById(long id);

    MixDto findMixOfDay();

    List<MixDto> findAll();

    List<MixDto> findByTobaccoId(long id);

    MixDto save(MixDto mix);

    void deleteById(long id);

    MixDto like(long id);

    MixDto dislike(long id);

    void clearPreviousMixOfDay();

    void setMixOfDayFlag(long id);
}
