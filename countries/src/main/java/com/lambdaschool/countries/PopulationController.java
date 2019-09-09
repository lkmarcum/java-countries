package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")
public class PopulationController
{
    // localhost:8080/population/size/{people}
    @GetMapping(value = "/size/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByPopulationSize(@PathVariable
                                                              long people)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.myCountryList.findCountries(c -> c.getPopulation() >= people);
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:8080/population/min
    @GetMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByMinPopulation()
    {
        Country minPopCountry = CountriesApplication.myCountryList.countryList.get(0);
        long minPop = minPopCountry.getPopulation();

        for (Country c : CountriesApplication.myCountryList.countryList)
        {
            if (c.getPopulation() < minPop)
            {
                minPop = c.getPopulation();
                minPopCountry = c;
            }
        }

        return new ResponseEntity<>(minPopCountry, HttpStatus.OK);
    }

    // localhost:8080/population/max
    @GetMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByMaxPopulation()
    {
        Country maxPopCountry = CountriesApplication.myCountryList.countryList.get(0);
        long maxPop = maxPopCountry.getPopulation();

        for (Country c : CountriesApplication.myCountryList.countryList)
        {
            if (c.getPopulation() > maxPop)
            {
                maxPop = c.getPopulation();
                maxPopCountry = c;
            }
        }

        return new ResponseEntity<>(maxPopCountry, HttpStatus.OK);
    }
}
