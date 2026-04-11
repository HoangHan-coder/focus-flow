package dev.focusflow.repositories;

import dev.focusflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :param or u.username = :param")
    Optional<User> getUserByEmailOrUsername(@Param("param") String param);
}
