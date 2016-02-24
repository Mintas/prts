import model.Purchase;
import model.Purchases;
import service.JackSparrowHelperImpl;

/**
 * Created by SBT-Kovalev-DA on 24.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        JackSparrowHelperImpl jackSparrowHelper = new JackSparrowHelperImpl();
        Purchases abr = jackSparrowHelper.helpJackSparrow("abr", 300);

        System.out.println(abr.calculateAveragePrice());
        abr.getPurchases().forEach(p -> System.out.println(p.getTotalPrice()));
        System.out.println(abr.getPurchases().stream().mapToDouble(Purchase::getTotalPrice).sum());
    }
}
