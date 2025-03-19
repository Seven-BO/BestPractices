package com.gildedrose;

import com.gildedrose.strategy.UpdateStrategy;
import com.gildedrose.strategy.UpdateStrategyFactory;

class GildedRose {
    Item[] items;

    GildedRose(Item[] items) {
        this.items = items;
    }

    void updateQuality() {
        for (Item item : items) {
            UpdateStrategy strategy = UpdateStrategyFactory.createStrategy(item);
            strategy.update(item);
        }
    }
}