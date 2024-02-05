package org.fasttrackit.curs18.service;

import org.fasttrackit.curs18.exceptions.ResourceNotFoundException;
import org.fasttrackit.curs18.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final CountryFileReader countryFileReader;
    private final List<Country> countries = new ArrayList<>();

    public CountryService(CountryFileReader countryFileReader) {
        this.countryFileReader = countryFileReader;
        countries.addAll(countryFileReader.populateWithData());
    }

    public List<Country> getAllCountries() {
        return countries;
    }

    public Country getCountryById(String id) {
        return countries.stream()
                .filter(country -> country.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Country with id:%s was not found".formatted(id)));
    }

    public List<Country> getAllCountriesByContinent(String continent) {
        return countries.stream()
                .filter(country -> country.continent().equals(continent))
                .toList();
    }

    public List<Country> getAllCountriesBiggerThan(Integer population) {
        return countries.stream()
                .filter(country -> country.population() > population)
                .toList();
    }

    public Country deleteById(String id) {
        Country countryToBeDeleted = getCountryById(id);
        countries.remove(countryToBeDeleted);
        return countryToBeDeleted;
    }

    public Country addNewCountry(Country newCountry) {
        String newId = UUID.randomUUID().toString();
        countries.add(newCountry.withId(newId));
        return getCountryById(newId);
    }

    public Country replaceCountry(String id, Country replaceCountry) {
        Country foundCountry = getCountryById(id);
        deleteById(id);
        Country updatedCountry = Country.builder()
                .id(foundCountry.id())
                .name(replaceCountry.name())
                .capital(replaceCountry.capital())
                .aria(replaceCountry.aria())
                .population(replaceCountry.population())
                .neighbours(replaceCountry.neighbours())
                .continent(replaceCountry.continent())
                .build();
        countries.add(updatedCountry);
        return updatedCountry;
    }

    public Map<String, List<Country>> groupByContinent() {
        return countries.stream()
                .collect(Collectors.groupingBy(Country::continent));
    }
}
