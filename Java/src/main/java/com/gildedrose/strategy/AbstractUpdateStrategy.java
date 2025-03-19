package com.gildedrose.strategy;

import com.gildedrose.Item;

public abstract class AbstractUpdateStrategy implements UpdateStrategy {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    @Override
    public void update(Item item) {
        updateSellIn(item);
        updateQualityBeforeSellIn(item);

        if (item.getSellIn() < 0) {
            updateQualityAfterSellIn(item);
        }
    }

    protected abstract void updateQualityBeforeSellIn(Item item);
    protected abstract void updateQualityAfterSellIn(Item item);

    // Por defecto, disminuye el sellIn en 1, las subclases pueden sobrescribir este comportamiento
    protected void updateSellIn(Item item) {
        item.setSellIn(item.getSellIn()-1);
    }

    protected void increaseQuality(Item item) {
        if (item.getQuality() < MAX_QUALITY) {
            item.setQuality(item.getQuality()+1);
        }
    }

    protected void decreaseQuality(Item item) {
        if (item.getQuality() > MIN_QUALITY) {
            item.setQuality(item.getQuality()-1);
        }
    }

    protected void setQuality(Item item, int value) {
        item.setQuality(Math.min(Math.max(value, MIN_QUALITY), MAX_QUALITY));
    }
}