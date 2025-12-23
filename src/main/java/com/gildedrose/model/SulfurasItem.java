package com.gildedrose.model;

public class SulfurasItem extends AbstractItem {

    public static final String NAME = "Sulfuras, Hand of Ragnaros";
    public static final int QUALITY = 80;

    /**
     * Given quality is ignored since this is always 80.
     */
    @SuppressWarnings("unused")
    public SulfurasItem(int sellIn, int quality) {
        super(NAME, sellIn, QUALITY);
    }

    /**
     * Quality nor SellIn decrease.
     */
    @Override
    public void updateQuality() {
    }
}
