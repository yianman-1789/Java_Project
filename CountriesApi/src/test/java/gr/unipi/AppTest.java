package gr.unipi;

import gr.unipi.CountryStructure.Country;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void byNameTest() {
        System.out.println("byNameTest");

        Country[] countries = CountriesApi.getCountryByName("Greece");

        assertTrue(countries.length > 0);
    }

    @Test
    public void byCurrencyTest() {
        System.out.println("byCurrencyTest");

        Country[] countries = CountriesApi.getCountryByCurrency("EUR");

        assertTrue(countries.length > 0);
    }

    @Test
    public void byLanguageTest() {
        System.out.println("byLanguageTest");

        Country[] countries = CountriesApi.getCountryByLanguage("Greek");

        assertTrue(countries.length > 0);
    }

    @Test
    public void allCountriesTest() {
        System.out.println("allCountriesTest");

        Country[] countries = CountriesApi.getAllCountries();

        assertTrue(countries.length > 0);
    }
}