package ru.lounge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lounge.dto.TobaccoDto;
import ru.lounge.exceptions.EntityNotFoundException;
import ru.lounge.exceptions.ValidationException;
import ru.lounge.models.Tobacco;
import ru.lounge.repositories.BrandRepository;
import ru.lounge.repositories.TobaccoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TobaccoServiceImpl implements TobaccoService {
    private final TobaccoRepository tobaccoRepository;

    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<TobaccoDto> findById(long id) {
        return tobaccoRepository.findById(id)
                .map(TobaccoDto::fromDomainObject);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TobaccoDto> findByBrandId(long id) {
        return tobaccoRepository.findByBrandId(id)
                .stream()
                .map(TobaccoDto::fromDomainObject)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TobaccoDto> searchByName(String name) {
        List<Tobacco> allTobaccos = tobaccoRepository.findAll();
        List<TobaccoDto> resultTobaccos = new ArrayList<>();

        for (Tobacco t : allTobaccos) {
            if (t.getName().contains(name)) {
                resultTobaccos.add(TobaccoDto.fromDomainObject(t));
            }
        }

        return resultTobaccos;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TobaccoDto> findAll() {
        return tobaccoRepository.findAll()
                .stream()
                .map(TobaccoDto::fromDomainObject)
                .toList();
    }

    @Transactional
    @Override
    public Tobacco save(TobaccoDto tobacco) {
        var brandId = tobacco.getBrand().getId();
        var brand = brandRepository.findById(tobacco.getBrand().getId())
                .orElseThrow(() -> new EntityNotFoundException("Brand with id %d not found".formatted(brandId)));

        if (tobacco.getStrength() < 0 || tobacco.getStrength() > 10) {
            throw new ValidationException("The strength of tobacco should be from 1 to 10");
        }

        return tobaccoRepository.save(tobacco.toDomainObject());
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        tobaccoRepository.deleteById(id);
    }
}
