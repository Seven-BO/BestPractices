package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BackstagePassStrategy extends AbstractUpdateStrategy{
    @Override
    protected void updateQualityBeforeSellIn(Item item) {
        increaseQuality(item);

        if (item.getSellIn()< 11) {
            increaseQuality(item);
        }

        if (item.getSellIn() < 6) {
            increaseQuality(item);
        }
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        setQuality(item, MIN_QUALITY);
    }
}
