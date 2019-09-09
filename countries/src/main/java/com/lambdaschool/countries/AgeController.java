package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/age")
public class AgeController
{
    // localhost:8080/age/age/{age}
    @GetMapping(value = "/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByMedAge(@PathVariable
                                                                  int age)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.myCountryList.findCountries(c -> c.getMedianAge() >= age);
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:8080/age/min
    @GetMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByMinAge()
    {
        Country minAgeCountry = CountriesApplication.myCountryList.countryList.get(0);
        int minAge = minAgeCountry.getMedianAge();

        for (Country c : CountriesApplication.myCountryList.countryList)
        {
            if (c.getMedianAge() < minAge)
            {
                minAge = c.getMedianAge();
                minAgeCountry = c;
            }
        }

        return new ResponseEntity<>(minAgeCountry, HttpStatus.OK);
    }

    // localhost:8080/age/max
    @GetMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByMaxAge()
    {
        Country maxAgeCountry = CountriesApplication.myCountryList.countryList.get(0);
        int maxAge = maxAgeCountry.getMedianAge();

        for (Country c : CountriesApplication.myCountryList.countryList)
        {
            if (c.getMedianAge() > maxAge)
            {
                maxAge = c.getMedianAge();
                maxAgeCountry = c;
            }
        }

        return new ResponseEntity<>(maxAgeCountry, HttpStatus.OK);
    }
}
