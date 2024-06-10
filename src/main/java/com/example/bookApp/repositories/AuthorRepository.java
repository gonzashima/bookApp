package com.example.bookApp.repositories;

import com.example.bookApp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByName(String name);

    Optional<Author> findAuthorByName(String name);
}
