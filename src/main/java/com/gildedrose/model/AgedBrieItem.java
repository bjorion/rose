package com.gildedrose.model;

public class AgedBrieItem extends AbstractItem {

    public static final String NAME = "Aged Brie";

    public AgedBrieItem(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    /**
     * For this item, we suppose quality does not drop after SellIn date.
     */
    @Override
    public void updateQuality() {

        decreaseSellIn();
        int quality = getQuality();
        quality++;
        setQuality(quality);
    }
}
