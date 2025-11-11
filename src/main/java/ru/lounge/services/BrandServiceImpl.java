package ru.lounge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lounge.dto.BrandDto;
import ru.lounge.models.Brand;
import ru.lounge.repositories.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<BrandDto> findById(long id) {
        return brandRepository.findById(id)
                .map(BrandDto::fromDomainObject);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(BrandDto::fromDomainObject)
                .toList();
    }

    @Transactional
    @Override
    public Brand save(BrandDto brand) {
        return brandRepository.save(brand.toDomainObject('N'));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        brandRepository.deleteById(id);
    }
}
