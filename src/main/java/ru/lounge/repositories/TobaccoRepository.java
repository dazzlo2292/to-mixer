package ru.lounge.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lounge.models.Tobacco;

import java.util.List;
import java.util.Optional;

public interface TobaccoRepository extends JpaRepository<Tobacco, Long> {
    @EntityGraph(value = "tobacco-brand-entity-graph")
    Optional<Tobacco> findById(long id);

    @EntityGraph(value = "tobacco-brand-entity-graph")
    List<Tobacco> findByBrandId(long id);

    @NonNull
    @EntityGraph(value = "tobacco-brand-entity-graph")
    List<Tobacco> findAll();
}
