package gr.unipi;

import gr.unipi.CountryStructure.Country;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CountriesApi {
    private static String baseUrl = "https://restcountries.com/v3.1/";
    private static String customFields = "?fields=name,region,population,capital,languages,currencies";

    // Πραγματοποιεί GET request στο API που δίνεται στην παράμετρο url
    // Επιστρέφει το HttpEntity που περιέχει το JSON που μας δίνει το API
    // Επιστρέφει null σε περίπτωση σφάλματος
    private static HttpEntity countryGetRequest(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            return entity;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Αποσειριοποιεί το JSON που περιέχεται στο HttpEntity που δίνεται στην παράμετρο entity
    // Επιστρέφει έναν πίνακα από αντικείμενα Country
    // Επιστρέφει null σε περίπτωση σφάλματος
    private static Country[] readCountriesFromEntity(HttpEntity entity) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Χρησιμοποιούμε την readValue του ObjectMapper για να αποσειριοποιήσουμε το JSON
            // Το .class το χρησιμοποιούμε για να δηλώσουμε τον τύπο του αντικειμένου που θέλουμε να αποσειριοποιήσουμε
            Country[] countries = mapper.readValue(entity.getContent(), Country[].class);

            return countries;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // Και οι 4 μεθόδοι ακολουθούν μια παρόμοια διαδικασία
    // Καλούν την countryGetRequest για να πάρουν το JSON από το API με το url που θα δώσουν (με τις κατάλληλες παραμέτρους)
    // Στη συνέχεια καλούν την readCountriesFromEntity για να αποσειριοποιήσουν το JSON και να πάρουν τα αντικείμενα Country
    // Τέλος επιστρέφουν τον πίνακα από αντικείμενα Country
    // Αν για κάποιο λόγο δεν μπορέσουμε να πάρουμε το JSON από το API, επιστρέφουμε null
    // Το ίδιο συμβαίνει και αν δεν μπορέσουμε να αποσειριοποιήσουμε το JSON
    public static Country[] getCountryByName(String name) {
        HttpEntity entity = countryGetRequest(baseUrl + "name/" + name + customFields);

        if (entity == null) {
            return null;
        }

        Country[] countries = readCountriesFromEntity(entity);

        if (countries == null) {
            return null;
        }

        return countries;
    }

    public static Country[] getCountryByCurrency(String currency) {
        HttpEntity entity = countryGetRequest(baseUrl + "currency/" + currency + customFields);

        if (entity == null) {
            return null;
        }

        Country[] countries = readCountriesFromEntity(entity);

        if (countries == null) {
            return null;
        }

        return countries;
    }

    public static Country[] getCountryByLanguage(String language) {
        HttpEntity entity = countryGetRequest(baseUrl + "lang/" + language + customFields);

        if (entity == null) {
            return null;
        }

        Country[] countries = readCountriesFromEntity(entity);

        if (countries == null) {
            return null;
        }

        return countries;
    }

    public static Country[] getAllCountries() {
        HttpEntity entity = countryGetRequest(baseUrl + "all" + customFields);

        if (entity == null) {
            return null;
        }

        Country[] countries = readCountriesFromEntity(entity);

        if (countries == null) {
            return null;
        }

        return countries;
    }
}