package ru.lounge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lounge.models.Brand;

@Data
@AllArgsConstructor
public class BrandDto {

    private long id;

    private String name;

    public static BrandDto fromDomainObject(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName());
    }
}
