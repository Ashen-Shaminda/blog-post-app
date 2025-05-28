package com.ashen_dissanayake.blog.repositories;

import com.ashen_dissanayake.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

   Optional<User> findByEmail(String email);
}
