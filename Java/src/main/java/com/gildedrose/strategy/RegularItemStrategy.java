package com.gildedrose.strategy;

import com.gildedrose.Item;

public class RegularItemStrategy extends AbstractUpdateStrategy {
    @Override
    protected void updateQualityBeforeSellIn(Item item) {
        decreaseQuality(item);
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        decreaseQuality(item);
    }
}
