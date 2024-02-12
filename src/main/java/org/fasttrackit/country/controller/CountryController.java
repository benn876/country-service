package org.fasttrackit.country.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.country.model.Country;
import org.fasttrackit.country.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("countries") //  http://localhost:8080/countries
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping //   http://localhost:8080/countries?continent={continent}
    public List<Country> getAllCountries(String continent, Integer population) {
        if (continent != null) {
            return service.getAllCountriesByContinent(continent);
        }

        if (population != null) {
            return service.getAllCountriesBiggerThan(population);
        }
        return service.getAllCountries();
    }

    @GetMapping("{id}") //  http://localhost:8080/countries/{id}
    public Country getOneCountry(@PathVariable Long id) {
        return service.getCountryById(id);
    }

    @DeleteMapping("{id}") //  http://localhost:8080/countries/{id}
    public Country deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @PostMapping//   http://localhost:8080/countries
    public Country addCountry(@RequestBody Country newCountry){
        return service.addNewCountry(newCountry);
    }

    @PutMapping("{id}")
    public Country replaceCountry(@PathVariable Long id, @RequestBody Country replaceCountry) {
        return service.replaceCountry(id, replaceCountry);
    }
}
