import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class CountryApp {
    public static void main(String[] args) throws IOException {

        Set<Country> euCountries = createCountryDataSet();

        Set<Country> countriesTopPopulation = euCountries.stream()
                .sorted(Comparator.comparingInt(Country::getPopulation).reversed())
                .limit(10)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Country> countriesTopArea = euCountries.stream()
                .sorted(Comparator.comparingDouble(Country::getArea).reversed())
                .limit(10)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Country> countriesTopDensity = euCountries.stream()
                .sorted(Comparator.comparingDouble(Country::getDensity).reversed())
                .limit(10)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Set<Country> createCountryDataSet() throws IOException {
        String rawCountryData = getStringData();
        JSONArray jsonArrCountryData = new JSONArray(rawCountryData);
        Set<Country> countries = new HashSet<>();
        for (Object countryData : jsonArrCountryData) {
            JSONObject jsonObjCountryData = (JSONObject) countryData;
            String countryName = jsonObjCountryData.optString("name", "");
            String countryCapital = jsonObjCountryData.optString("capital", "");
            int countryPopulation = jsonObjCountryData.optInt("population", 0);
            double countryArea = jsonObjCountryData.optDouble("area", 0);
            JSONObject jsonCurrencyData = (JSONObject) jsonObjCountryData.getJSONArray("currencies").get(0);
            String currencyCode = jsonCurrencyData.optString("code", "");
            String currencyName = jsonCurrencyData.optString("name", "");
            String currencySymbol = jsonCurrencyData.optString("symbol", "");
            Currency countryCurrency = new Currency(currencyCode, currencyName, currencySymbol);
            Country currentCountry = new Country(countryName, countryCapital, countryPopulation, countryArea, countryCurrency);
            countries.add(currentCountry);
        }
        return countries;
    }

    private static String getStringData() throws IOException {
        String theURL = "https://restcountries.com/v2/regionalbloc/eu?fields=name,capital,currencies,population,area";
        URL url = new URL(theURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder inputData = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            inputData.append(inputLine);
        }
        return inputData.toString();
    }

    private static void createFile(String input) {


    }
}