package org.fasttrackit.country.repository;

import org.fasttrackit.country.model.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepository extends JpaRepository<President, Long> {
}
