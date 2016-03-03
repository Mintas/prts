package model;

/**
 * Created by SBT-Kovalev-DA on 24.02.2016.
 */
public class Commodity {
    private final String source;
    private final int totalAmount;
    private int amountLeft;
    private final double avgPrice;
    private final int minSize;
    private final int servingSize;

    private Commodity(String source, int totalAmount, int amount, double avgPrice, int minSize, int servingSize) {
        this.source = source;
        this.amountLeft = amount;
        this.totalAmount = totalAmount;
        this.avgPrice = avgPrice;
        this.minSize = minSize;
        this.servingSize = servingSize;
    }

    public static Commodity init(String source, int amount, double avgPrice, int minSize, int servingSize) {
        return new Commodity(source, amount, amount, avgPrice, minSize, servingSize);
    }

    public Commodity withAmountLeft(int amountLeft){
        return new Commodity(this.source, this.totalAmount, amountLeft, this.avgPrice, this.minSize, this.servingSize);
    }

    public String getSource() {
        return source;
    }

    public int getTotalAmount() {
        return totalAmount;
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
