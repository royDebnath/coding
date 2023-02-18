package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/base")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return service.getAllCountries();
    }

    @GetMapping("/countries/{id}")
    public Country getCountriesById(@PathVariable int id) {
        return service.getCountryById(id);
    }

    @GetMapping("/countries/sort")
    public List<Country> getSortedCountries(@RequestParam String sorter) {
        return service.getSortedCountries(sorter);
    }

    @PostMapping("/countries/")
    public List<Country> addCountry(@RequestBody Country country) {
        service.addCountry(country);
        return service.getAllCountries();
    }

    @PutMapping("/countries/{countryId}")
    public Country replaceCountry(@RequestBody Country country, @PathVariable(name="countryId") int id) {
        service.replaceCountry(id, country);
        return service.getCountryById(id);
    }

    @PatchMapping("/countries/{countryId}")
    public Country correctName(@RequestBody String name, @PathVariable(name="countryId") int id) {
        service.correctName(id, name);
        return service.getCountryById(id);
    }
}