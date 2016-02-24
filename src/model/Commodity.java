package model;

/**
 * Created by SBT-Kovalev-DA on 24.02.2016.
 */
public class Commodity {
    private final String source;
    private int amountLeft;
    private final double avgPrice;
    private final int minSize;
    private final int servingSize;

    public Commodity(String source, int amount, double avgPrice, int minSize, int servingSize) {
        this.source = source;
        this.amountLeft = amount;
        this.avgPrice = avgPrice;
        this.minSize = minSize;
        this.servingSize = servingSize;
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

    public int getMinSize() {
        return minSize;
    }

    public int getServingSize() {
        return servingSize;
    }

}
