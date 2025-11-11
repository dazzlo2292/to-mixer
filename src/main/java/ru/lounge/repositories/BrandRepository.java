package ru.lounge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lounge.models.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Override
    @Modifying
    @Query("UPDATE Brand b SET b.isDeleted = 'Y' WHERE b.id = :id")
    void deleteById(@Param("id") Long id);

    @Override
    @Query("SELECT b FROM Brand b WHERE b.isDeleted = 'N' and b.id = :id")
    Optional<Brand> findById(@Param("id") Long id);

    @Override
    @Query("SELECT b FROM Brand b WHERE b.isDeleted = 'N'")
    List<Brand> findAll();
}
