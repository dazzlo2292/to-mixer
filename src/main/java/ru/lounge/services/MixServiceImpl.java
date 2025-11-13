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

@Service
@RequiredArgsConstructor
public class MixServiceImpl implements MixService{

    private final MixRepository mixRepository;

    private final TobaccoRepository tobaccoRepository;

    @Transactional(readOnly = true)
    @Override
    public MixDto findById(long id) {
        Mix targetMix = mixRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mix with id %d not found".formatted(id)));
        return MixDto.fromDomainObject(targetMix);
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

        return mixRepository.save(convertMixDtoToDomain(mix, 'N'));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        mixRepository.deleteById(id);
    }

    private Mix convertMixDtoToDomain(MixDto mixDto, char isDeleted) {
        List<Tobacco> mixTobaccos = new ArrayList<>();

        for (TobaccoDto t : mixDto.getTobaccos()) {
            tobaccoRepository.findById(t.getId()).ifPresent(mixTobaccos::add);
        }

        return new Mix(
                mixDto.getId(),
                mixDto.getName(),
                isDeleted,
                mixTobaccos
        );
    }
}

