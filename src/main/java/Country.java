public class Country {

    private final String name;

    private final String capital;

    private final double population;

    private final double area;

    private final Currency currency;

    public Country(String name, String capital, double population, double area, Currency currency) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.currency = currency;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return population / area;
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
