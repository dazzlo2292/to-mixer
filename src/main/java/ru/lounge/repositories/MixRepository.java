package ru.lounge.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lounge.models.Mix;

import java.util.List;

public interface MixRepository extends JpaRepository<Mix, Long> {
    @Override
    @NonNull
    @EntityGraph(value = "mixes-tobacco-entity-graph")
    List<Mix> findAll();

    @Query("SELECT m FROM Mix m JOIN FETCH m.tobaccos t WHERE t.id = :id")
    List<Mix> findByTobaccoId(@Param("id") Long id);
}
