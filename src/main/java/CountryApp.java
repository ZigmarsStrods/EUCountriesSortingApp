import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class CountryApp {

    private static final int TOP_RANGE = 10;

    public static void main(String[] args) throws IOException {
        String theURL = "https://restcountries.com/v2/regionalbloc/eu?fields=name,capital,currencies,population,area";
        createFile(theURL);
        String countryDataString = getStringData("src/main/resources/CountryFile.json");
        List<Country> euCountries = createCountryDataSet(countryDataString);
        List<Country> topPopulation = getTop10Population(euCountries);
        List<Country> topArea = getTop10Area(euCountries);
        List<Country> topDensity = getTop10Density(euCountries);
        System.out.println(topPopulation);

    }

    public static <T> List<T> getTop10(List<T> countriesList, ToDoubleFunction<T> comparatorFunc) {
        return countriesList.stream()
                .sorted(Comparator.comparingDouble(comparatorFunc).reversed())
                .limit(TOP_RANGE)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Country> getTop10Density(List<Country> countries) {
        return getTop10(countries, Country::getDensity);
    }

    public static List<Country> getTop10Area(List<Country> countries) {
        return getTop10(countries, Country::getArea);
    }

    public static List<Country> getTop10Population(List<Country> countries) {
        return getTop10(countries, Country::getPopulation);
    }

    private static List<Country> createCountryDataSet(String rawCountryData) {
        JSONArray jsonArrCountryData = new JSONArray(rawCountryData);
        List<Country> countries = new ArrayList<>();
        for (Object countryData : jsonArrCountryData) {
            JSONObject jsonObjCountryData = (JSONObject) countryData;
            String countryName = jsonObjCountryData.optString("name", "");
            String countryCapital = jsonObjCountryData.optString("capital", "");
            double countryPopulation = jsonObjCountryData.optDouble("population", 0);
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

    public static String getStringData(String fileLocation) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        StringBuilder input = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            input.append(line);
        }
        return input.toString();
    }

    private static void createFile(String stringURL) throws IOException {
        URL url = new URL(stringURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/CountryFile.json"));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.write(inputLine);
        }
        in.close();
        out.close();
    }
}