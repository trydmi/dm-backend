package com.luxoft.repo;

import com.luxoft.model.RiservaNetta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RiservaNettaRepository extends JpaRepository<RiservaNetta, Long> {
    Optional<RiservaNetta> findByDate(LocalDate date);
}
