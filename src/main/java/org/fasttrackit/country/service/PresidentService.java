package org.fasttrackit.country.service;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.country.model.Country;
import org.fasttrackit.country.model.President;
import org.fasttrackit.country.repository.CountryRepository;
import org.fasttrackit.country.repository.PresidentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PresidentService {
    private final PresidentRepository repository;
    private final CountryRepository countryRepository;

    public List<President> getAll() {
        return repository.findAll();
    }

    public President createPresident(President newPresident) {
        President president = repository.save(newPresident);
        Country countryById = countryRepository.findById(president.getCountryId())
                .orElseThrow(() -> new RuntimeException("Not found"));
        countryRepository.save(countryById.withPresident(president));
        return president;
    }
}
