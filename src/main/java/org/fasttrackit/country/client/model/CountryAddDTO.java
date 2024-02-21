package org.fasttrackit.country.client.model;

import lombok.Builder;

@Builder
public record CountryAddDTO(
        Long id,
        String countryName,
        String capital,
        Integer population,
        Integer aria,
        String continent
) {
}
