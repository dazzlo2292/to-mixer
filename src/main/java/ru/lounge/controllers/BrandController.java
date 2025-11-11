package ru.lounge.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.lounge.dto.BrandDto;
import ru.lounge.exceptions.EntityNotFoundException;
import ru.lounge.models.Brand;
import ru.lounge.services.BrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService brandService;

    @GetMapping("/api/v1/public/brands")
    public List<BrandDto> findAll() {
        logger.info("Method called - GET /api/v1/public/brands");
        return brandService.findAll();
    }

    @GetMapping("/api/v1/public/brands/{id}")
    public BrandDto findById(@PathVariable Long id) {
        logger.info("Method called - GET /api/v1/public/brands/ with params: {}", id);
        return brandService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand with id %d not found".formatted(id)));
    }

    @PostMapping("/api/v1/admin/brands")
    public Brand save(@RequestBody BrandDto brand) {
        logger.info("Method called - POST /api/v1/admin/brands with param:{}", brand);
        return brandService.save(brand);
    }

    @DeleteMapping("/api/v1/admin/brands/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Method called - DELETE /api/v1/admin/brands with param:{}", id);
        brandService.deleteById(id);
    }
}
