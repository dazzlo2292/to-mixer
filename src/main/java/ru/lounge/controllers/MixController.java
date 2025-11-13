package ru.lounge.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.lounge.dto.MixDto;
import ru.lounge.services.MixService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MixController {
    private static final Logger logger = LoggerFactory.getLogger(MixController.class);

    private final MixService mixService;

    @GetMapping("/api/v1/public/mixes")
    public List<MixDto> findAll() {
        logger.info("Method called - GET /api/v1/public/mixes");
        return mixService.findAll();
    }

    @GetMapping("/api/v1/public/mixes/{id}")
    public MixDto findById(@PathVariable Long id) {
        logger.info("Method called - GET /api/v1/public/mixes/ with params: {}", id);
        return mixService.findById(id);
    }

    @GetMapping("/api/v1/public/mixes/tobacco/{id}")
    public List<MixDto> findByTobaccoId(@PathVariable Long id) {
        logger.info("Method called - GET /api/v1/public/mixes/tobacco/ with params: {}", id);
        return mixService.findByTobaccoId(id);
    }

    @PostMapping("/api/v1/admin/mixes")
    public MixDto save(@RequestBody MixDto mix) {
        logger.info("Method called - POST /api/v1/admin/mixes with param:{}", mix);
        return MixDto.fromDomainObject(mixService.save(mix));
    }

    @DeleteMapping("/api/v1/admin/mixes/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Method called - DELETE /api/v1/admin/mixes with param:{}", id);
        mixService.deleteById(id);
    }
}
