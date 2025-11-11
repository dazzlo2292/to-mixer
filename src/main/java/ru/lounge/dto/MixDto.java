package ru.lounge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lounge.models.Mix;

import java.util.List;

@Data
@AllArgsConstructor
public class MixDto {
    private Long id;

    private String name;

    private List<TobaccoDto> tobaccos;

    public Mix toDomainObject() {
        return new Mix(
                id,
                name,
                tobaccos.stream()
                        .map(TobaccoDto::toDomainObject)
                        .toList()
        );
    }

    public static MixDto fromDomainObject(Mix mix) {
        return new MixDto(
                mix.getId(),
                mix.getName(),
                mix.getTobaccos().stream()
                        .map(TobaccoDto::fromDomainObject)
                        .toList()
        );
    }
}
