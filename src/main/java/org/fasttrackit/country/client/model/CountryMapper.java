package org.fasttrackit.country.client.model;

import org.fasttrackit.country.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public static CountryDTO toDto(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .countryName(country.getName())
                .population(country.getPopulation())
                .capital(country.getCapital())
                .president(country.getPresident())
                .cities(country.getCities())
                .build();
    }

    public static Country toEntity(CountryAddDTO countryDTO) {
        return Country.builder()
                .name(countryDTO.countryName())
                .capital(countryDTO.capital())
                .population(countryDTO.population())
                .aria(countryDTO.aria())
                .continent(countryDTO.continent())
                .build();
    }
}
