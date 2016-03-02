package knapsack;

import model.Commodity;
import model.Purchase;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by SBT-Kovalev-DA on 01.03.2016.
 */
public class GreedyBoundedKnapsackSolver implements BoundedKnapsackSolver {
    @Override
    public Set<Purchase> solveKnapsack(int capacity, List<Commodity> commodities) {
        List<Commodity> cmdtDescendingPrice = commodities.stream().sorted(comparing(Commodity::getAvgPrice).reversed()).collect(toList());

        int gotWeight = 0;
        Set<Purchase> purchases = new LinkedHashSet<>();
        while (gotWeight < capacity) {
            Purchase purchase = decidePurchase(capacity - gotWeight, cmdtDescendingPrice);
            if (purchase == null || gotWeight + purchase.getNumberOfGallons() > capacity) break;

            purchases.add(purchase);
            gotWeight += purchase.getNumberOfGallons();
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
