package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedBrieStrategy extends AbstractUpdateStrategy {
    @Override
    protected void updateQualityBeforeSellIn(Item item) {
        increaseQuality(item);
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        increaseQuality(item);
    }
}
