package org.fasttrackit.country.client.model;

import lombok.Builder;
import org.fasttrackit.country.model.City;
import org.fasttrackit.country.model.President;

import java.util.List;

@Builder
public record CountryDTO(
        Long id,
        String countryName,
        String capital,
        Integer population,
        President president,
        List<City> cities
) {
}
