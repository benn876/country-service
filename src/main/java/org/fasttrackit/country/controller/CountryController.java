package org.fasttrackit.country.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.country.client.model.CountryAddDTO;
import org.fasttrackit.country.client.model.CountryDTO;
import org.fasttrackit.country.client.model.CountryMapper;
import org.fasttrackit.country.model.Country;
import org.fasttrackit.country.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.fasttrackit.country.client.model.CountryMapper.toDto;
import static org.fasttrackit.country.client.model.CountryMapper.toEntity;

@RestController
@RequestMapping("countries") //  http://localhost:8080/countries
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping //   http://localhost:8080/countries?continent={continent}
    public List<CountryDTO> getAllCountries(String continent, Integer population) {
        if (continent != null) {
            return service.getAllCountriesByContinent(continent).stream()
                    .map(CountryMapper::toDto)
                    .toList();
        }

        if (population != null) {
            return service.getAllCountriesBiggerThan(population).stream()
                    .map(CountryMapper::toDto)
                    .toList();
        }
        return service.getAllCountries().stream()
                .map(CountryMapper::toDto)
                .toList();
    }

    @GetMapping("{id}") //  http://localhost:8080/countries/{id}
    public CountryDTO getOneCountry(@PathVariable Long id) {
        return toDto(service.getCountryById(id));
    }

    @DeleteMapping("{id}") //  http://localhost:8080/countries/{id}
    public CountryDTO deleteById(@PathVariable Long id) {
        return toDto(service.deleteById(id));
    }

    @PostMapping//   http://localhost:8080/countries
    public CountryDTO addCountry(@RequestBody CountryAddDTO newCountry) {
        Country countryEntity = service.addNewCountry(toEntity(newCountry));
        return toDto(countryEntity);
    }

    @PutMapping("{id}")
    public CountryDTO replaceCountry(@PathVariable Long id, @RequestBody CountryAddDTO replaceCountry) {
        return toDto(service.replaceCountry(id, toEntity(replaceCountry)));
    }
}
