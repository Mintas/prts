package excel;

import model.Commodity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;
import static model.Commodity.init;

/**
 * Created by SBT-Kovalev-DA on 25.02.2016.
 */
public class CSVCommodityParserImpl implements CommodityParser {

    private final String separator;

    public CSVCommodityParserImpl(String separator) {
        this.separator = separator;
    }

    @Override
    public List<Commodity> parseFile(String fileName) {
        List<Commodity> commodities = new ArrayList<>();

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(fileName));
            br.readLine(); //avoid headers
            while ((line = br.readLine()) != null) {
                String[] commodity = line.split(separator);
                commodities.add(init(commodity[0],
                        valueOf(commodity[1]), Double.valueOf(commodity[2]),
                        valueOf(commodity[3]), valueOf(commodity[4])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return commodities;
    }
}
