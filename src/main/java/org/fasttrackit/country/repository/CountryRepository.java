package org.fasttrackit.country.repository;

import org.fasttrackit.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByPopulationGreaterThan(Integer population);

    List<Country> findAllByContinent(String continent);

    @Query("select c from Country c where c.continent=:conti")
    List<Country> findAllByContinentQuery(@Param("conti") String continent);

    @Query(value = "SELECT * FROM COUNTRY WHERE continent=:conti", nativeQuery = true)
    List<Country> findByContinentQueryNative(@Param("conti") String continent);
}
