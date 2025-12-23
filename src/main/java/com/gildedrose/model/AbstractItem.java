package com.gildedrose.model;

public abstract class AbstractItem {

    private static final int MAX_QUALITY = 50;

    private final String name;
    private int sellIn;
    private int quality;

    public AbstractItem(String name, int sellIn, int quality) {

        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    protected void decreaseSellIn() {
        --this.sellIn;
    }

    /**
     * Update quality and also make sure quality is within limits
     */
    protected void setQuality(int quality) {

        this.quality = checkBounds(quality);
    }

    /**
     * Override this method if quality boundaries must be overridden for a specific item.
     */
    protected int checkBounds(int quality) {

        quality = Math.min(quality, MAX_QUALITY);
        quality = Math.max(quality, 0);
        return quality;
    }

    public abstract void updateQuality();
}
