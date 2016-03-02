package service;

import excel.CommodityParser;
import knapsack.BoundedKnapsackSolver;
import model.Commodity;
import model.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class JackSparrowHelperWithSolver extends AbstractJackSparrowHelper {
    private final BoundedKnapsackSolver knapsackSolver;

    public JackSparrowHelperWithSolver(CommodityParser parser, BoundedKnapsackSolver knapsackSolver) {
        super(parser);
        this.knapsackSolver = knapsackSolver;
    }

    @Override
    protected Set<Purchase> getPurchases(int numberOfGallons, List<Commodity> commodities) {
        int knapsackSize = getKnapsackSize(commodities, numberOfGallons);
        Set<Purchase> excludePurchases = fillKnapsack(knapsackSize, commodities);
        return findNeededPurchases(commodities, excludePurchases);
    }

    private Set<Purchase> findNeededPurchases(List<Commodity> commodities, Set<Purchase> excludePurchases) {
        return commodities.stream()
                .map(c -> findNeeded(c, excludePurchases))
                .filter(p -> p.getNumberOfGallons()!=0)
                .collect(toSet());
    }

    private Purchase findNeeded(Commodity commodity, Set<Purchase> excludePurchases) {
        Optional<Purchase> excludeFromCmdty = excludePurchases.stream()
                .filter(p -> commodity.getSource().equals(p.getSourceName())).findFirst();
        return new Purchase(commodity.getSource(),
                getAmount(commodity, excludeFromCmdty),
                commodity.getAvgPrice());
    }

    private Integer getAmount(Commodity commodity, Optional<Purchase> excludeFromCmdty) {
        return commodity.getTotalAmount() - (excludeFromCmdty.isPresent() ? excludeFromCmdty.get().getNumberOfGallons() : 0);
    }

    private int getKnapsackSize(List<Commodity> commodities, int numberOfGallons) {
        return calculateTotalPurchaseSize(commodities) - numberOfGallons;
    }

    private int calculateTotalPurchaseSize(List<Commodity> commodities) {
        return commodities.stream().mapToInt(Commodity::getAmountLeft).sum();
    }

    private Set<Purchase> fillKnapsack(int knapsackSize, List<Commodity> commodities) {
        return knapsackSolver.solveKnapsack(knapsackSize, commodities);
    }
}
