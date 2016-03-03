import excel.CSVCommodityParserImpl;
import knapsack.GreedyBoundedKnapsackSolver;
import model.Purchase;
import model.Purchases;
import service.JackSparrowHelper;
import service.JackSparrowHelperGreedy;
import service.JackSparrowHelperWithSolver;

/**
 * Created by SBT-Kovalev-DA on 24.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        JackSparrowHelper jackSparrowHelperOld = new JackSparrowHelperGreedy(new CSVCommodityParserImpl(";"));
        JackSparrowHelper jackSparrowHelper = new JackSparrowHelperWithSolver(new CSVCommodityParserImpl(";"), new GreedyBoundedKnapsackSolver());

        printSolution(jackSparrowHelperOld);
        System.out.println();
        printSolution(jackSparrowHelper);
    }

    private static void printSolution(JackSparrowHelper jackSparrowHelper) {
        Purchases abr = jackSparrowHelper.helpJackSparrow("C:\\Users\\SBT-Kovalev-DA\\Downloads\\pirates\\sources.csv", 222);

        System.out.println(abr.calculateAveragePrice() + "  REAL AVG: " + getAvg(abr));
        System.out.println("amount: " + getGallons(abr));
        System.out.println("TOTAL is: " + abr.getPurchases().stream().mapToDouble(Purchase::getTotalPrice).sum());
    }

    private static double getAvg(Purchases abr) {
        return getTotalPrice(abr)/getGallons(abr);
    }

    private static int getGallons(Purchases purchases) {
        return purchases.getPurchases().stream().mapToInt(Purchase::getNumberOfGallons).sum();
    }
    private static double getTotalPrice(Purchases purchases) {
        return purchases.getPurchases().stream().mapToDouble(Purchase::getTotalPrice).sum();
    }
}
