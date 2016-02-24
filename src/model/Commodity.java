package model;

/**
 * Created by SBT-Kovalev-DA on 24.02.2016.
 */
public class Commodity {
    private final String source;
    private int amountLeft;
    private final double avgPrice;
    private final int minCount;
    private final int servingCount;

    public Commodity(String source, int amount, double avgPrice, int minCount, int servingCount) {
        this.source = source;
        this.amountLeft = amount;
        this.avgPrice = avgPrice;
        this.minCount = minCount;
        this.servingCount = servingCount;
    }

    public String getSource() {
        return source;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void decreaseAmount(int got) {
        amountLeft -= got;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getServingCount() {
        return servingCount;
    }

}
