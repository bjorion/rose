package com.gildedrose.model;

public class ConjuredItem extends AbstractItem {

    public static final String NAME = "Conjured";

    public ConjuredItem(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    @Override
    public void updateQuality() {

        decreaseSellIn();
        int quality = getQuality();
        quality -= 2;
        setQuality(quality);
    }
}
