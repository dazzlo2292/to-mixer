package ru.lounge.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.lounge.dto.TobaccoDto;
import ru.lounge.exceptions.EntityNotFoundException;
import ru.lounge.models.Tobacco;
import ru.lounge.services.TobaccoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TobaccoController {
    private static final Logger logger = LoggerFactory.getLogger(TobaccoController.class);

    private final TobaccoService tobaccoService;

    @GetMapping("/api/v1/public/tobaccos")
    public List<TobaccoDto> findAll() {
        logger.info("Method called - GET /api/v1/public/tobaccos");
        return tobaccoService.findAll();
    }

    @GetMapping("/api/v1/public/tobaccos/{id}")
    public TobaccoDto findById(@PathVariable Long id) {
        logger.info("Method called - GET /api/v1/public/tobaccos/ with params: {}", id);
        return tobaccoService.findById(id).orElseThrow(() -> new EntityNotFoundException("Tobacco not found!"));
    }

    @GetMapping("/api/v1/public/tobaccos/brand/{id}")
    public List<TobaccoDto> findByBrandId(@PathVariable Long id) {
        logger.info("Method called - GET /api/v1/public/tobaccos/brand/ with params: {}", id);
        return tobaccoService.findByBrandId(id);
    }

    @GetMapping("/api/v1/public/tobaccos/search")
    public List<TobaccoDto> searchByName(@RequestParam String name) {
        logger.info("Method called - GET /api/v1/public/tobaccos with params: {}", name);
        return tobaccoService.searchByName(name);
    }

    @PostMapping("/api/v1/admin/tobaccos")
    public Tobacco save(@RequestBody TobaccoDto tobacco) {
        logger.info("Method called - POST /api/v1/admin/tobaccos with param:{}", tobacco);
        return tobaccoService.save(tobacco);
    }

    @DeleteMapping("/api/v1/admin/tobaccos/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Method called - DELETE /api/v1/admin/tobaccos with param:{}", id);
        tobaccoService.deleteById(id);
    }
}
