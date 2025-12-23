package com.gildedrose.model;

public class BackstageItem extends AbstractItem {

    public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

    public BackstageItem(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    /**
     * Specification not very clear, possible bug in the old implementation.
     */
    @Override
    public void updateQuality() {

        decreaseSellIn();
        int sellIn = getSellIn();
        int quality = getQuality();

        if (sellIn >= 10) {
            quality += 1;
        }
        // between 10 and 6 days, increase by 2
        else if (sellIn >= 5) {
            quality += 2;
        }
        // between 5 and 0 days, increase by 3
        else if (sellIn >= 0) {
            quality += 3;
        }
        // after concert, drop to 0
        else {
            quality = 0;
        }
        setQuality(quality);
    }
}
