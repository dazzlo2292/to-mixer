package ru.lounge.services;


import ru.lounge.dto.TobaccoDto;
import ru.lounge.models.Tobacco;

import java.util.List;
import java.util.Optional;

public interface TobaccoService {
    Optional<TobaccoDto> findById(long id);

    List<TobaccoDto> findByBrandId(long id);

    List<TobaccoDto> findAll();

    List<TobaccoDto> searchByName(String name);

    Tobacco save(TobaccoDto tobacco);

    void deleteById(long id);
}
