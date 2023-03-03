package com.spring.rest.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/another")
public class AnotherController {

    @GetMapping("/countries")
    public String getCountries() {
        return "another countries";
    }
}