package com.gildedrose.model;

public class ConjuredItem extends AbstractItem {

    public static final String NAME = "Conjured";

    public ConjuredItem(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    /**
     * Degrade in Quality twice as fast as normal items
     */
    @Override
    public void updateQuality() {

        decreaseSellIn();
        int quality = getQuality();
        if (getSellIn() >= 0) {
            quality -= 2;
        } else {
            quality -= 4;
        }
        setQuality(quality);
    }
}
