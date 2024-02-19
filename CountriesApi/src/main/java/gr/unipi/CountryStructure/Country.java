package gr.unipi.CountryStructure;

import java.util.Map;

// Η κλάση Country αντιπροσωπεύει τα στοιχεία που παίρνουμε από το API
// Είναι έτσι δομημένη ώστε η βιβλιοθήκη jackson να μπορεί να αντιστοιχίσει το JSON που παίρνουμε από το API σε μορφή αντικειμένου
// Η Name και η Currency ακολουθούν την ίδια λογική και είναι ορισμένες σε ξεχωριστά αρχεία
public class Country {
    private Name name;
    private String region;
    private int population;
    private String[] capital;
    private Map<String, String> languages;
    private Map<String, Currency> currencies;

    public Country() {
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }
}