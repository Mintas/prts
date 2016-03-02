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

public abstract class AbstractJackSparrowHelper implements JackSparrowHelper {
    private final CommodityParser parser;

    public AbstractJackSparrowHelper(CommodityParser parser) {
        this.parser = parser;
    }

    @Override
    public Purchases helpJackSparrow(String pathToPrices, int numberOfGallons) {
        List<Commodity> commodities = getCommodities(pathToPrices);

        Set<Purchase> purchases = getPurchases(numberOfGallons, commodities);

        return new Purchases(numberOfGallons, purchases);
    }

    protected abstract Set<Purchase> getPurchases(int numberOfGallons, List<Commodity> commodities);

    private List<Commodity> getCommodities(String pathToPrices) {
        return parser.parseFile(pathToPrices)
                .stream().sorted(comparing(Commodity::getAvgPrice)).collect(toList());
    }
}
