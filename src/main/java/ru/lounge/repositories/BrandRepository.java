package ru.lounge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lounge.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
