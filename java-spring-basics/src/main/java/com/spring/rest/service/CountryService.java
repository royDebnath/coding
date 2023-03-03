package com.spring.rest.service;

import com.spring.rest.model.Country;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final List<Country> countries;

    public CountryService(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getAllCountries() {
        return countries;
    }

    public Country getCountryById(int id) {
        return countries.stream().filter(country -> id == country.getId()).findFirst().get();
    }

    public List<Country> getSortedCountries(String sorter) {

        if (sorter.equals("name")){
        return  countries.stream().sorted(Comparator.comparing(Country::getName)).collect(Collectors.toList());
    }
        else {
            return  countries.stream().sorted(Comparator.comparing(Country::getContinent)).collect(Collectors.toList());

        }
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void replaceCountry(int id, Country country) {
        Country replace = countries.stream().filter(c -> c.getId() == id).findFirst().get();
        replace.setName(country.getName());
        replace.setContinent(country.getContinent());
    }

    public void correctName(int id, String name) {
        Country replace = countries.stream().filter(c -> c.getId() == id).findFirst().get();
        replace.setName(name);

    }
}
