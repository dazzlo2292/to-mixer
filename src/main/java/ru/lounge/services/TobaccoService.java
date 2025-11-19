package ru.lounge.services;


import ru.lounge.dto.TobaccoDto;
import ru.lounge.models.Tobacco;

import java.util.List;

public interface TobaccoService {
    TobaccoDto findById(long id);

    List<TobaccoDto> findByBrandId(long id);

    List<TobaccoDto> findAllBased();

    List<TobaccoDto> findAll();

    List<TobaccoDto> searchByName(String name);

    Tobacco save(TobaccoDto tobacco);

    void deleteById(long id);
}
