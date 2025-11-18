package ru.lounge.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lounge.models.Mix;

import java.util.List;
import java.util.Optional;

public interface MixRepository extends JpaRepository<Mix, Long> {
    @Override
    @NonNull
    @EntityGraph(value = "mixes.with-tobaccos-and-brand")
    @Query("SELECT m FROM Mix m WHERE m.isDeleted = 'N'" +
            "ORDER BY (m.likesCount - m.dislikesCount) DESC")
    List<Mix> findAll();

    @EntityGraph(value = "mixes.with-tobaccos-and-brand")
    @Query("SELECT m FROM Mix m JOIN FETCH m.tobaccos t " +
            "WHERE m.isDeleted = 'N'" +
            " and t.isDeleted = 'N' " +
            " and t.id = :id")
    List<Mix> findByTobaccoId(@Param("id") Long id);

    @Override
    @EntityGraph(value = "mixes.with-tobaccos-and-brand")
    @Query("SELECT m FROM Mix m WHERE m.isDeleted = 'N' and m.id = :id")
    Optional<Mix> findById(@Param("id") Long id);

    @Override
    @Modifying
    @Query("UPDATE Mix m SET m.isDeleted = 'Y' WHERE m.id = :id")
    void deleteById(@Param("id") Long id);
}
