package knapsack;

import model.Commodity;
import model.Purchase;

import java.util.List;
import java.util.Set;

/**
 * Created by SBT-Kovalev-DA on 01.03.2016.
 */
public interface BoundedKnapsackSolver {
    Set<Purchase> solveKnapsack(int capacity, List<Commodity> commodities, boolean minPrice);

    default Set<Purchase> solveKnapsack(int capacity, List<Commodity> commodities)  {
        return solveKnapsack(capacity, commodities, false);//look for maxPrice by default
    };
}
