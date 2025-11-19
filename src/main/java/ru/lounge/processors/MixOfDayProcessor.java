package ru.lounge.processors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lounge.dto.MixDto;
import ru.lounge.dto.TobaccoDto;
import ru.lounge.services.MixService;
import ru.lounge.services.TobaccoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MixOfDayProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MixOfDayProcessor.class);

    private final MixService mixService;

    private final TobaccoService tobaccoService;

    private final Random random = new Random();

    @Scheduled(cron = "0 0 0 * * *")
    public void generationMixOfDayTask() {
        mixService.clearPreviousMixOfDay();

        switch (random.nextInt(2)) {
            case 0 -> selectMixOfDayInCreatedMixes();
            case 1 -> createNewMixOfDay();
        }

        logger.info("Generation Mix of Day task completed");
    }

    private void selectMixOfDayInCreatedMixes() {
        List<MixDto> allMixes = mixService.findAll();

        MixDto newMixOfDay = allMixes.get(random.nextInt(allMixes.size()));
        mixService.setMixOfDayFlag(newMixOfDay.getId());
    }

    private void createNewMixOfDay() {
        List<TobaccoDto> basedTobaccos = tobaccoService.findAllBased();
        List<TobaccoDto> allTobaccos = tobaccoService.findAll();
        List<TobaccoDto> tobaccosForMixOfDay = new ArrayList<>();

        int countTobaccosInMix = random.nextInt(2) + 2;

        TobaccoDto randomBasedTobacco = basedTobaccos.get(random.nextInt(basedTobaccos.size()));
        tobaccosForMixOfDay.add(randomBasedTobacco);

        for (int i = 1; i <= countTobaccosInMix; i++) {
            tobaccosForMixOfDay.add(allTobaccos.get(random.nextInt(allTobaccos.size())));
        }

        MixDto newMixOfDay = getNewMixOfDay(tobaccosForMixOfDay);

        mixService.save(newMixOfDay);
    }

    private MixDto getNewMixOfDay(List<TobaccoDto> tobaccosForMixOfDay) {
        StringBuilder mixName = new StringBuilder();

        for (int i = 0; i < tobaccosForMixOfDay.size(); i++) {
            mixName.append(tobaccosForMixOfDay.get(i).getDescription());
            if (i != tobaccosForMixOfDay.size() - 1) {
                mixName.append(" + ");
            }
        }

        return new MixDto(
                null,
                mixName.toString(),
                'Y',
                0,
                0,
                tobaccosForMixOfDay
        );
    }
}
