package org.fasttrackit.curs18.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.curs18.model.Country;
import org.fasttrackit.curs18.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("countries") //  http://localhost:8080/countries
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping //   http://localhost:8080/countries?continent={continent}
    public List<Country> getAllCountries(String continent, @RequestParam(defaultValue = "1000") Integer population) {
        if (continent != null) {
            return service.getAllCountriesByContinent(continent);
        }

        if (population != null) {
            return service.getAllCountriesBiggerThan(population);
        }
        return service.getAllCountries();
    }

    @GetMapping("{id}") //  http://localhost:8080/countries/{id}
    public Country getOneCountry(@PathVariable String id) {
        return service.getCountryById(id);
    }

    @DeleteMapping("{id}") //  http://localhost:8080/countries/{id}
    public Country deleteById(@PathVariable String id) {
        return service.deleteById(id);
    }

    @PostMapping//   http://localhost:8080/countries
    public Country addCountry(@RequestBody Country newCountry){
        return service.addNewCountry(newCountry);
    }

    @PutMapping("{id}")
    public Country replaceCountry(@PathVariable String id, @RequestBody Country replaceCountry){
        return service.replaceCountry(id, replaceCountry);
    }

    @GetMapping("reports/continent") //  http://localhost:8080/countries/reports/continent
    public Map<String,List<Country>> getTralala(){
        return service.groupByContinent();
    }
}
