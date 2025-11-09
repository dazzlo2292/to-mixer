package ru.lounge.services;

import ru.lounge.dto.BrandDto;
import ru.lounge.models.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Optional<BrandDto> findById(long id);

    List<BrandDto> findAll();

    Brand save(Brand brand);

    void deleteById(long id);
}
