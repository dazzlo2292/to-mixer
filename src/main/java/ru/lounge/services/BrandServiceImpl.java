package ru.lounge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lounge.dto.BrandDto;
import ru.lounge.exceptions.EntityNotFoundException;
import ru.lounge.models.Brand;
import ru.lounge.models.Tobacco;
import ru.lounge.repositories.BrandRepository;
import ru.lounge.repositories.TobaccoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;

    private final TobaccoRepository tobaccoRepository;

    private final TobaccoService tobaccoService;

    @Transactional(readOnly = true)
    @Override
    public BrandDto findById(long id) {
        Brand targetBrand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand with id %d not found".formatted(id)));
        return BrandDto.fromDomainObject(targetBrand);
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
        return brandRepository.save(
                new Brand(
                        brand.getId(),
                        brand.getName(),
                        'N'
                )
        );
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        List<Tobacco> tobaccosByBrand = tobaccoRepository.findByBrandId(id);

        for (Tobacco t : tobaccosByBrand) {
            tobaccoService.deleteById(t.getId());
        }

        brandRepository.deleteById(id);
    }
}
