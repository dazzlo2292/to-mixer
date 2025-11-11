package ru.lounge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lounge.models.Brand;
import ru.lounge.models.Tobacco;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class TobaccoDto {
    private long id;

    private String name;

    private String description;

    private Brand brand;

    private int strength;

    private char isBase;

    public Tobacco toDomainObject() {
        return new Tobacco(id, name, description, brand, strength, isBase, 'N', new ArrayList<>());
    }

    public static TobaccoDto fromDomainObject(Tobacco tobacco) {
        return new TobaccoDto(
                tobacco.getId(),
                tobacco.getName(),
                tobacco.getDescription(),
                tobacco.getBrand(),
                tobacco.getStrength(),
                tobacco.getIsBase());
    }
}
