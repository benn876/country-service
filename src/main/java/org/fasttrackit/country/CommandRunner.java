package org.fasttrackit.country;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.country.model.Country;
import org.fasttrackit.country.repository.CountryRepository;
import org.fasttrackit.country.service.CountryFileReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
    private final CountryRepository repository;
    private final CountryFileReader countryFileReader;

    @Override
    public void run(String... args) {
        this.repository.saveAll(countryFileReader.populateWithData());
        this.repository.save(Country.builder()

                .build());
    }
}
