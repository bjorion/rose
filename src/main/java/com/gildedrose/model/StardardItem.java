package com.gildedrose.model;

public class StardardItem extends AbstractItem {

    public StardardItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    /**
     * Standard behavior: quality decreases of 1 per day
     */
    @Override
    public void updateQuality() {

        decreaseSellIn();
        int quality = getQuality();

        if (getSellIn() >= 0) {
            quality -= 1;
        } else {
            quality -= 2;
        }
        setQuality(quality);
    }
}
