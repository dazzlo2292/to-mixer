package ru.lounge.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lounge.models.Mix;
import ru.lounge.models.Tobacco;

import java.util.List;
import java.util.Optional;

public interface TobaccoRepository extends JpaRepository<Tobacco, Long> {
    @Override
    @EntityGraph(value = "tobacco-brand-mixes-entity-graph")
    @Query("SELECT t FROM Tobacco t WHERE t.isDeleted = 'N' and t.id = :id")
    Optional<Tobacco> findById(@Param("id") Long id);

    @EntityGraph(value = "tobacco-brand-mixes-entity-graph")
    @Query("SELECT t FROM Tobacco t WHERE t.isDeleted = 'N' and t.brand.id = :id")
    List<Tobacco> findByBrandId(@Param("id") Long id);

    @Override
    @NonNull
    @EntityGraph(value = "tobacco-brand-mixes-entity-graph")
    @Query("SELECT t FROM Tobacco t WHERE t.isDeleted = 'N'")
    List<Tobacco> findAll();

    @Override
    @Modifying
    @Query("UPDATE Tobacco t SET t.isDeleted = 'Y' WHERE t.id = :id")
    void deleteById(@Param("id") Long id);
}


