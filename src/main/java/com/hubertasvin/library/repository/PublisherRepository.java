package com.hubertasvin.library.repository;

import com.hubertasvin.library.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
