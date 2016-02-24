package excel;

import model.Commodity;

import java.util.List;

/**
 * Created by SBT-Kovalev-DA on 25.02.2016.
 */
public interface CommodityParser {
    List<Commodity> parseFile(String fileName);
}
