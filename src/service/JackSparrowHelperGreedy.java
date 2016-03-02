package service;

import excel.CommodityParser;
import model.Commodity;
import model.Purchase;
import model.Purchases;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Deprecated
public class JackSparrowHelperGreedy extends AbstractJackSparrowHelper {
    public JackSparrowHelperGreedy(CommodityParser parser) {
        super(parser);
    }

    @Override
    protected Set<Purchase> getPurchases(int numberOfGallons, List<Commodity> commodities) {
        int gotGallons = 0;
        Set<Purchase> purchases = new LinkedHashSet<>();
        while (gotGallons < numberOfGallons) {
            Purchase purchase = decidePurchase(numberOfGallons - gotGallons, commodities);
            purchases.add(purchase);
            gotGallons += purchase.getNumberOfGallons();
        }
        return purchases;
    }

    private Purchase decidePurchase(int needGallons, List<Commodity> commodities) {
        List<Commodity> cmdtsLeft = commodities.stream()
                .filter(c -> c.getAmountLeft() != 0)
                .collect(toList());
        for (Commodity commodity : cmdtsLeft) {
            Purchase purchase = bestForCommodity(commodity, needGallons);
            if (purchase != null) return purchase;
        }
        return null;
    }

    private Purchase bestForCommodity(Commodity cmdty, int needGallons) {
        int canTake = min(cmdty.getAmountLeft(), needGallons);
        int canTakeByServings = (canTake / cmdty.getServingSize()) * cmdty.getServingSize();
        if (canTake < cmdty.getMinSize() || canTakeByServings == 0) return null;

        cmdty.decreaseAmount(canTakeByServings);
        return new Purchase(cmdty.getSource(), canTakeByServings, cmdty.getAvgPrice());
    }
}
