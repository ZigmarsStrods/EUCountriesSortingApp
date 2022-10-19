import java.util.Objects;

public class Country {

    private String name;

    private String capital;

    private int population;

    private double area;

    private Currency currency;

    public Country(String name, String capital, int population, double area, Currency currency) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.currency = currency;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return population/area;
    }

    @Override
    public String toString() {
        return "\nCountry{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", currency=" + currency +
                '}';
    }
}
