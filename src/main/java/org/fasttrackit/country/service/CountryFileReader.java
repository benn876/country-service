package org.fasttrackit.country.service;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.fasttrackit.country.model.City;
import org.fasttrackit.country.model.Country;
import org.fasttrackit.country.model.President;
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
        Faker faker = new Faker();
        President president = President.builder()
                .name(faker.name().fullName())
                .build();
        List<City> cities = List.of(
                City.builder()
                        .name(faker.address().cityName())
                        .build(),
                City.builder()
                        .name(faker.address().cityName())
                        .build(),
                City.builder()
                        .name(faker.address().cityName())
                        .build()
        );
        return Country.builder()
                .name(countryParts[0])
                .capital(countryParts[1])
                .population(valueOf(countryParts[2]))
                .aria(valueOf(countryParts[3]))
                .president(president)
                .cities(cities)
                .continent(countryParts[4])
                .neighbours(countryParts.length > 5 ? parseNeighbours(countryParts[5]) : List.of())
                .build();
    }

    private List<String> parseNeighbours(String neighboursLine) {
        return stream(neighboursLine.split("~")).toList();
    }
}
