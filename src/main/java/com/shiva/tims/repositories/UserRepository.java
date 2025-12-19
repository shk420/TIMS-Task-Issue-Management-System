package com.shiva.tims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shiva.tims.models.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

   
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
