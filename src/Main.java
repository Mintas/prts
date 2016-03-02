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
        //JackSparrowHelper jackSparrowHelper = new JackSparrowHelperGreedy(new CSVCommodityParserImpl(";"));
        JackSparrowHelper jackSparrowHelper = new JackSparrowHelperWithSolver(new CSVCommodityParserImpl(";"), new GreedyBoundedKnapsackSolver());

        Purchases abr = jackSparrowHelper.helpJackSparrow("C:\\Users\\SBT-Kovalev-DA\\Downloads\\pirates\\sources.csv", 500);

        System.out.println(abr.calculateAveragePrice());
        abr.getPurchases().forEach(p -> System.out.println(p.getTotalPrice()));
        System.out.println("TOTAL is: " + abr.getPurchases().stream().mapToDouble(Purchase::getTotalPrice).sum());
    }
}
