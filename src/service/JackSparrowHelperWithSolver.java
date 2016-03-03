package service;

import excel.CommodityParser;
import knapsack.BoundedKnapsackSolver;
import model.Commodity;
import model.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class JackSparrowHelperWithSolver extends AbstractJackSparrowHelper {
    private final BoundedKnapsackSolver knapsackSolver;

    public JackSparrowHelperWithSolver(CommodityParser parser, BoundedKnapsackSolver knapsackSolver) {
        super(parser);
        this.knapsackSolver = knapsackSolver;
    }

    @Override
    protected Set<Purchase> getPurchases(int numberOfGallons, List<Commodity> commodities) {
        int knapsackSize = getKnapsackSize(commodities, numberOfGallons);
        Set<Purchase> excludePurchases = knapsackSolver.solveKnapsack(knapsackSize, commodities);
        List<Commodity> neededCommodities = findNeededCommodities(commodities, excludePurchases);
        return knapsackSolver.solveKnapsack(numberOfGallons, neededCommodities, true);
    }

    private List<Commodity> findNeededCommodities(List<Commodity> commodities, Set<Purchase> excludePurchases) {
        return commodities.stream()
                .map(c -> findNeededC(c, excludePurchases))
                .filter(c -> c.getAmountLeft() != 0)
                .sorted(comparing(Commodity::getAvgPrice))
                .collect(toList());
    }

    private Commodity findNeededC(Commodity commodity, Set<Purchase> excludePurchases) {
        Optional<Purchase> excludeFromCmdty = excludePurchases.stream()
                .filter(p -> commodity.getSource().equals(p.getSourceName())).findFirst();
        return commodity.withAmountLeft(getAmount(commodity, excludeFromCmdty));
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

}
