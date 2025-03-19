package com.gildedrose.strategy;

import com.gildedrose.Item;

public class UpdateStrategyFactory {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static UpdateStrategy createStrategy(Item item) {
        switch (item.getName()) {
            case AGED_BRIE:
                return new AgedBrieStrategy();
            case BACKSTAGE_PASSES:
                return new BackstagePassStrategy();
            case SULFURAS:
                return new SulfurasStrategy();
            default:
                return new RegularItemStrategy();
        }
    }
}
