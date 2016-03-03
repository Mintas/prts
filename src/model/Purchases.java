package model;

import java.util.Set;

import static java.util.Collections.singleton;
import static model.Purchase.HEAVY;

public class Purchases {
    private final int requestedNumberOfGallons;
    private final Set<Purchase> purchases;

    public Purchases(int requestedNumberOfGallons, Set<Purchase> purchases) {
        this.requestedNumberOfGallons = requestedNumberOfGallons;
        this.purchases = purchases;
    }

    public static Purchases heavyEmpty(int requestedNumberOfGallons){
        return new Purchases(requestedNumberOfGallons, singleton(HEAVY));
    }

    public Double calculateAveragePrice() {
        double totalSum = 0;
        for (Purchase purchase : getPurchases()) {
            totalSum += purchase.getTotalPrice();
        }
        return getRequestedNumberOfGallons() == 0
                ? null
                : totalSum / getRequestedNumberOfGallons();
    }

    public Integer getRequestedNumberOfGallons() {
        return requestedNumberOfGallons;
    }

    public double getAvg() {
        return getTotalPrice()/getGallons();
    }

    public  int getGallons( ) {
        return purchases.stream().mapToInt(Purchase::getNumberOfGallons).sum();
    }
    public  double getTotalPrice( ) {
        return purchases.stream().mapToDouble(Purchase::getTotalPrice).sum();
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }
}
