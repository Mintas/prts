package model;

public class Purchase {
    public static final Purchase HEAVY = new Purchase("", Integer.MAX_VALUE, Double.MAX_VALUE);
    private final String sourceName;
    private final Integer numberOfGallons;
    private final Double priceOfGallon;

    public Purchase(String sourceName, Integer numberOfGallons, Double priceOfGallon) {
        this.sourceName = sourceName;
        this.numberOfGallons = numberOfGallons;
        this.priceOfGallon = priceOfGallon;
    }

    public String getSourceName() {
        return sourceName;
    }

    public Integer getNumberOfGallons() {
        return numberOfGallons;
    }

    public Double getPriceOfGallon() {
        return priceOfGallon;
    }

    public Double getTotalPrice() {
        return getPriceOfGallon() * getNumberOfGallons();
    }
}
