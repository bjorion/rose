package com.gildedrose;

import com.gildedrose.model.*;

public class AbstractItemFactory {

    public static AbstractItem convert(Item item) {

        return switch (item.name) {
            case AgedBrieItem.NAME -> new AgedBrieItem(item.sellIn, item.quality);
            case SulfurasItem.NAME -> new SulfurasItem(item.sellIn, item.quality);
            case BackstageItem.NAME -> new BackstageItem(item.sellIn, item.quality);
            case ConjuredItem.NAME -> new ConjuredItem(item.sellIn, item.quality);
            default -> new NormalItem(item.name, item.sellIn,  item.quality);
        };
    }
}
