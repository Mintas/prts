package service;

import model.Commodity;
import model.Purchase;
import model.Purchases;

import java.util.*;

import static java.lang.Math.max;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class JackSparrowHelperImpl implements JackSparrowHelper {
    @Override
    public Purchases helpJackSparrow(String pathToPrices, int numberOfGallons) {
        List<Commodity> commodities = getCommodities(pathToPrices);

        int gotGallons = 0;
        Set<Purchase> purchases = new HashSet<>();
        while (gotGallons < numberOfGallons) {
            Purchase purchase = decidePurchase2(numberOfGallons - gotGallons, commodities);
            purchases.add(purchase);
            gotGallons+=purchase.getNumberOfGallons();
        }

        Purchases purchases1 = new Purchases(numberOfGallons);
        purchases1.setPurchases(purchases);
        return purchases1;
    }

    private Purchase decidePurchase(int numberOfGallons, int gotGallons, List<Commodity> commodities, Map<Commodity, Double> commoditiesTaken) {
        //List<Purchase> best4Comdties = commodities.stream().map(e -> bestForComdty(e, numberOfGallons - gotGallons)).collect(toList());
        Commodity commodity = commodities.stream()
                .filter(c -> c.getAmountLeft() != 0)
                .sorted(comparing(Commodity::getAvgPrice))
                .findFirst().orElse(null);
      /*  commodities.stream()
                .filter(c -> c.getAmountLeft() != 0)
                .sorted(comparing(Commodity::getAvgPrice))
                .collect(toList());*/
        return commodity == null ? null : bestForComdty(commodity, numberOfGallons - gotGallons);
    }

    private Purchase decidePurchase2(int needGallons, List<Commodity> commodities) {
        List<Commodity> collect = commodities.stream()
                .filter(c -> c.getAmountLeft() != 0)
                .collect(toList());
        for (Commodity commodity : collect) {
            Purchase purchase = bestForComdty(commodity, needGallons);
            if (purchase != null) {
                return purchase;
            }
        }
        return null;
    }


    private Purchase bestForComdty(Commodity cmdty, int needGallons) {
        int amountLeft = cmdty.getAmountLeft();
        int canTake = Math.min(amountLeft, needGallons);
        int canTakeServings = canTake / cmdty.getServingCount();
        if (canTake < cmdty.getMinCount() || canTakeServings == 0) return null;

        cmdty.decreaseAmount(canTake);
        return new Purchase(cmdty.getSource(), canTake, cmdty.getAvgPrice());
    }

    private List<Commodity> getCommodities(String pathToPrices) {
        return Arrays.asList(
                new Commodity("M", 50, 50.0, 1, 1),
                new Commodity("S", 60, 52.0, 1, 1),
                new Commodity("B", 200, 55.0, 1, 1),
                new Commodity("D", 100, 51.0, 100, 100),
                new Commodity("H", 300, 50.5, 200, 100),
                new Commodity("K", 200, 54.0, 200, 200)
        ).stream().sorted(comparing(Commodity::getAvgPrice)).collect(toList());
    }
}
