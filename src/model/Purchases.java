package model;

import java.util.Set;

public class Purchases {
    private final int requestedNumberOfGallons;
    private final Set<Purchase> purchases;

    public Purchases(int requestedNumberOfGallons, Set<Purchase> purchases) {
        this.requestedNumberOfGallons = requestedNumberOfGallons;
        this.purchases = purchases;
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

    public Set<Purchase> getPurchases() {
        return purchases;
    }
}
