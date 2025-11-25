package ru.lounge.services;

import ru.lounge.dto.BrandDto;
import ru.lounge.models.Brand;

import java.util.List;

public interface BrandService {
    BrandDto findById(long id);

    List<BrandDto> findAll();

    Brand save(BrandDto brand);

    void deleteById(long id);
}
