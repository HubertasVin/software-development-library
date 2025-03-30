package com.hubertasvin.library.repository;

import com.hubertasvin.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFullName(String fullName);
}
