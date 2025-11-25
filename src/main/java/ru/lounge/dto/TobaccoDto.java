package ru.lounge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lounge.models.Tobacco;


@Data
@AllArgsConstructor
public class TobaccoDto {
    private long id;

    private String name;

    private String description;

    private BrandDto brand;

    private int strength;

    private char isBase;

    public static TobaccoDto fromDomainObject(Tobacco tobacco) {
        return new TobaccoDto(
                tobacco.getId(),
                tobacco.getName(),
                tobacco.getDescription(),
                BrandDto.fromDomainObject(tobacco.getBrand()),
                tobacco.getStrength(),
                tobacco.getIsBase());
    }
}
