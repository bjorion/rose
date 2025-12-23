package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("deprecation")
class GildedRoseTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    @Test
    void normalItem_normalScenario() {

        var items = new Item[]{new Item("base", 10, 20)};
        var app = new GildedRose(items);
        // app.updateQuality();
        app.updateQuality2();
        assertItem(app.items[0], "base", 9, 19);
    }

    @Test
    void normalItem_afterSellDateQualityDecreaseTwiceAsFast() {

        var items = new Item[]{new Item("base", 1, 20)};
        var app = new GildedRose(items);
        app.updateQuality2(); // 0 19
        app.updateQuality2(); // -1 17
        assertItem(app.items[0], "base", -1, 17);
    }

    @Test
    void normalItem_qualityNeverNegative() {

        var items = new Item[]{new Item("base", 0, 0)};
        var app = new GildedRose(items);
        app.updateQuality2();
        assertItem(app.items[0], "base", -1, 0);
    }

    @Test
    void agedBrie_qualityIncreasePerDay() {

        var items = new Item[]{new Item(AGED_BRIE, 10, 20)};
        var app = new GildedRose(items);
        app.updateQuality2();
        assertItem(app.items[0], AGED_BRIE, 9, 21);
    }

    @Test
    void agedBrie_qualityIsNeverAbove50() {

        var items = new Item[]{new Item(AGED_BRIE, 10, 48)};
        var app = new GildedRose(items);
        app.updateQuality2();
        assertItem(app.items[0], AGED_BRIE, 9, 49);
        app.updateQuality2();
        assertItem(app.items[0], AGED_BRIE, 8, 50);
        app.updateQuality2();
        assertItem(app.items[0], AGED_BRIE, 7, 50);
    }

    // not clear if Aged Brie quality decreases or not after the sell date
    // there is probably a bug here in the old code where quality increase of 2 instead of 1
    @Test
    void agedBrie_qualityAfterSellDate() {

        var items = new Item[]{new Item(AGED_BRIE, 1, 10)};
        var app = new GildedRose(items);
        app.updateQuality2();
        assertItem(app.items[0], AGED_BRIE, 0, 11);
        app.updateQuality2();
        // bug with old code
        assertItem(app.items[0], AGED_BRIE, -1, 12);
        app.updateQuality2();
        // bug with old code
        assertItem(app.items[0], AGED_BRIE, -2, 13);
    }

    @Test
    void sulfuras_noSellDateAndNoQualityDecrease() {

        // given quality is ignored
        var items = new Item[]{new Item(SULFURAS, 1, 100)};
        var app = new GildedRose(items);
        app.updateQuality2();
        // quality is 80 and never alters
        assertItem(app.items[0], SULFURAS, 1, 80);
        app.updateQuality2();
        assertItem(app.items[0], SULFURAS, 1, 80);
    }

    // Specification not clear
    // Possible bug in the old implementation
    @Test
    void backstage_increaseQualityNormal() {

        var items = new Item[]{new Item(BACKSTAGE, 11, 40)};
        var app = new GildedRose(items);
        app.updateQuality2();
        // should be 42 according to me
        assertItem(app.items[0], BACKSTAGE, 10, 41);
    }

    @Test
    void backstage_increaseQualityBy2Or3() {

        var quality = 20;
        var items = new Item[]{new Item(BACKSTAGE, 10, quality)};
        var app = new GildedRose(items);

        // increase by 2 between 10 and 6 days
        for (int day = 10; day >= 6; ) {
            app.updateQuality2();
            day--;
            quality += 2;
            assertItem(app.items[0], BACKSTAGE, day, quality);
        }

        // increase by 3 between 5 days or less
        for (int day = 5; day >= 1; ) {
            app.updateQuality2();
            day--;
            quality += 3;
            assertItem(app.items[0], BACKSTAGE, day, quality);
        }

        // after concert date, quality is 0
        app.updateQuality2();
        assertItem(app.items[0], BACKSTAGE, -1, 0);
        app.updateQuality2();
        assertItem(app.items[0], BACKSTAGE, -2, 0);
    }

    @Test
    void conjured_qualityDecreasesTwiceAsFast() {

        var items = new Item[]{new Item(CONJURED, 1, 4)};
        var app = new GildedRose(items);
        app.updateQuality2();
        assertItem(app.items[0], CONJURED, 0, 2);
        app.updateQuality2();
        assertItem(app.items[0], CONJURED, -1, 0);
        app.updateQuality2();
        assertItem(app.items[0], CONJURED, -2, 0);
    }

    private static void assertItem(Item app, String name, int sellIn, int quality) {

        assertEquals(name, app.name);
        assertEquals(sellIn, app.sellIn);
        assertEquals(quality, app.quality);
    }
}