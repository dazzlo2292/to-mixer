package ru.lounge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lounge.dto.MixDto;
import ru.lounge.dto.TobaccoDto;
import ru.lounge.exceptions.EntityNotFoundException;
import ru.lounge.models.Mix;
import ru.lounge.models.Tobacco;
import ru.lounge.repositories.MixRepository;
import ru.lounge.repositories.TobaccoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MixServiceImpl implements MixService{

    private final MixRepository mixRepository;

    private final TobaccoRepository tobaccoRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<MixDto> findById(long id) {
        return mixRepository.findById(id)
                .map(MixDto::fromDomainObject);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MixDto> findByTobaccoId(long id) {
        return mixRepository.findByTobaccoId(id).stream()
                .map(MixDto::fromDomainObject)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MixDto> findAll() {
        return mixRepository.findAll()
                .stream()
                .map(MixDto::fromDomainObject)
                .toList();
    }

    @Transactional
    @Override
    public Mix save(MixDto mix) {
        List<TobaccoDto> tobaccosInMix = mix.getTobaccos();

        for (TobaccoDto t : tobaccosInMix) {
            if (tobaccoRepository.findById(t.getId()).isEmpty()) {
                throw new EntityNotFoundException("Tobacco with id %d not found".formatted(t.getId()));
            }
        }

        return mixRepository.save(convertMixDtoToDomain(mix));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        mixRepository.deleteById(id);
    }

    private Mix convertMixDtoToDomain(MixDto mixDto) {
        List<Tobacco> mixTobaccos = new ArrayList<>();

        for (TobaccoDto t : mixDto.getTobaccos()) {
            tobaccoRepository.findById(t.getId()).ifPresent(mixTobaccos::add);
        }

        return new Mix(
                mixDto.getId(),
                mixDto.getName(),
                'N',
                mixTobaccos
        );
    }
}

