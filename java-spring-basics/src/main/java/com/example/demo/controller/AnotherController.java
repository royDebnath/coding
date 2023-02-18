package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/another")
public class AnotherController {

    @GetMapping("/countries")
    public String getCountries() {
        return "another countries";
    }
}