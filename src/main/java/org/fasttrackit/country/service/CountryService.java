package org.fasttrackit.country.service;

import org.fasttrackit.country.exceptions.ResourceNotFoundException;
import org.fasttrackit.country.model.Country;
import org.fasttrackit.country.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getAllCountries() {
        return repository.findAll();
    }

    public Country getCountryById(Long id) {
        Country country = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id:%s was not found".formatted(id)));
        return country;
    }

    public List<Country> getAllCountriesByContinent(String continent) {
        return repository.findAllByContinent(continent);
    }

    public List<Country> getAllCountriesBiggerThan(Integer population) {
        return repository.findAllByPopulationGreaterThan(population);
    }

    public Country deleteById(Long id) {
        Country foundCountry = getCountryById(id);
        repository.deleteById(id);
        return foundCountry;
    }

    public Country addNewCountry(Country newCountry) {
        return repository.save(newCountry);
    }

    public Country replaceCountry(Long id, Country replaceCountry) {
        Country foundCountry = getCountryById(id);
        Country updatedCountry = Country.builder()
                .id(foundCountry.getId())
                .name(replaceCountry.getName())
                .capital(replaceCountry.getCapital())
                .aria(replaceCountry.getAria())
                .population(replaceCountry.getPopulation())
                .neighbours(replaceCountry.getNeighbours())
                .continent(replaceCountry.getContinent())
                .build();
        return repository.save(updatedCountry);
    }
}
