package service;

import model.Purchases;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
import static model.Purchases.heavyEmpty;

/**
 * Created by SBT-Kovalev-DA on 03.03.2016.
 */
public class JackSparrowHelperEnsemble implements JackSparrowHelper {
    private final List<JackSparrowHelper> helpers;

    public JackSparrowHelperEnsemble(List<JackSparrowHelper> helpers) {
        this.helpers = helpers;
    }

    @Override
    public Purchases helpJackSparrow(String pathToPrices, int numberOfGallons) {
        List<Purchases> candidates = helpers.stream()
                .map(h -> h.helpJackSparrow(pathToPrices, numberOfGallons))
                .collect(Collectors.toList());
        return candidates.stream()
                .reduce(heavyEmpty(numberOfGallons), this::findBest);
    }

    private Purchases findBest(Purchases first, Purchases second) {
        Comparator<Purchases> purchasesComparator = comparingDouble(Purchases::getTotalPrice)
                .thenComparing(comparing(Purchases::getGallons).reversed());
        return purchasesComparator.compare(first, second) <= 0 ? first : second;
    }
}
