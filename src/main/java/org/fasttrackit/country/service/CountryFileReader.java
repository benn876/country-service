package org.fasttrackit.country.service;

import lombok.SneakyThrows;
import org.fasttrackit.country.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.stream;

@Repository
public class CountryFileReader implements DataProvider {
    @Value("${file.countries}")
    private String fileCountriesPath;
    @SneakyThrows
    @Override
    public List<Country> populateWithData() {
        return Files.lines(Path.of(fileCountriesPath))
                .map(this::lineToCountry)
                .toList();
    }

    private Country lineToCountry(String line) {
        String[] countryParts = line.split("\\|");
        return Country.builder()
                .name(countryParts[0])
                .capital(countryParts[1])
                .population(valueOf(countryParts[2]))
                .aria(valueOf(countryParts[3]))
                .continent(countryParts[4])
                .neighbours(countryParts.length > 5 ? parseNeighbours(countryParts[5]) : List.of())
                .build();
    }

    private List<String> parseNeighbours(String neighboursLine) {
        return stream(neighboursLine.split("~")).toList();
    }
}
