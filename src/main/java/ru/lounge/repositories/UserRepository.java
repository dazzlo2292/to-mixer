package ru.lounge.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lounge.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "user-role-entity-graph")
    User findByUsername(String username);
}
