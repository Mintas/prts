package service;

import model.Purchases;

public interface JackSparrowHelper {
    /**
     * @param pathToPrices     path to file with rum providers sources.csv
     * @param numberOfGallons  requested number of gallons
     * @return <tt>model.Purchases</tt> result
     */
    Purchases helpJackSparrow(String pathToPrices, int numberOfGallons);
}
