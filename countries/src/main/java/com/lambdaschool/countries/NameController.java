package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NameController
{
    // localhost:8080/names/all
    @GetMapping(value = "/all",
                produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        ArrayList<Country> rtnCountries = CountriesApplication.myCountryList.findCountries((c -> c.getName() != null));
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:8080/names/start/{letter}
    @GetMapping(value = "/start/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByFirstLetter(@PathVariable char letter)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.myCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:8080/names/size/{number}
    @GetMapping(value = "/size/{number}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByNameLength(@PathVariable int number)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.myCountryList.findCountries(c -> c.getName().length() >= number);
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }
}
