import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CountryTest {

    List<Country> simpleCountryList = new ArrayList<>();
    String filler = "A";
    int population = 11;
    double area = 1;

    @BeforeEach
    public void getSampleList() {
        for (int i = 0; i < 11; i++) {
            Country sample = new Country(filler, filler, population, area, new Currency(filler, filler, filler));
            simpleCountryList.add(sample);
            filler = Character.toString(filler.charAt(0) + 1);
            population--;
            area++;
        }
    }

    @Test
    public void checkTop10Population() {
        List<Country> result = CountryApp.getTop10Population(simpleCountryList);
        Assertions.assertEquals(11, result.get(0).getPopulation());
        Assertions.assertEquals(10, result.get(1).getPopulation());
        Assertions.assertEquals(9, result.get(2).getPopulation());
        Assertions.assertEquals(8, result.get(3).getPopulation());
    }

    @Test
    public void checkTop10Area() {
        List<Country> result = CountryApp.getTop10Area(simpleCountryList);
        Assertions.assertEquals(11, result.get(0).getArea());
        Assertions.assertEquals(10, result.get(1).getArea());
        Assertions.assertEquals(9, result.get(2).getArea());
        Assertions.assertEquals(8, result.get(3).getArea());
    }

    @Test
    public void checkTop10Density() {
        List<Country> result = CountryApp.getTop10Density(simpleCountryList);
        Assertions.assertEquals(11, result.get(0).getDensity());
        Assertions.assertEquals(5, result.get(1).getDensity());
        Assertions.assertEquals(3, result.get(2).getDensity());
        Assertions.assertEquals(2, result.get(3).getDensity());
    }
}
