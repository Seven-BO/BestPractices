package com.gildedrose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    private Item[] items;
    private GildedRose app;

    @BeforeEach
    void setUp() {
        items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert 15 days", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert 10 days", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert 5 days", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };
        app = new GildedRose(items);
    }

    @Test
    void testUpdateQualityForOneDay() {
        // Execute the method once
        app.updateQuality();

        // Assert all items have their values correctly updated after one day
        assertItem("+5 Dexterity Vest", 9, 19);
        assertItem("Aged Brie", 1, 1);
        assertItem("Elixir of the Mongoose", 4, 6);
        assertItem("Sulfuras, Hand of Ragnaros", 0, 80);
        assertItem("Sulfuras, Hand of Ragnaros", -1, 80);
        assertItem("Backstage passes to a TAFKAL80ETC concert 15 days", 14, 21);
        assertItem("Backstage passes to a TAFKAL80ETC concert 10 days", 9, 50); // Quality capped at 50
        assertItem("Backstage passes to a TAFKAL80ETC concert 5 days", 4, 50); // Quality capped at 50
        assertItem("Conjured Mana Cake", 2, 5);
    }

    @Test
    void testUpdateQualityForTwoDays() {
        // Execute the method twice
        app.updateQuality();
        app.updateQuality();

        assertItem("+5 Dexterity Vest", 8, 18);
        assertItem("Aged Brie", 0, 2);
        assertItem("Elixir of the Mongoose", 3, 5);
        assertItem("Sulfuras, Hand of Ragnaros", 0, 80);
        assertItem("Sulfuras, Hand of Ragnaros", -1, 80);
        assertItem("Backstage passes to a TAFKAL80ETC concert 15 days", 13, 22);
        assertItem("Backstage passes to a TAFKAL80ETC concert 10 days", 8, 50);
        assertItem("Backstage passes to a TAFKAL80ETC concert 5 days", 3, 50);
        assertItem("Conjured Mana Cake", 1, 4);
    }

    @Test
    void testQualityNeverExceeds50() {
        // Create a new item array with items at quality 49
        Item[] qualityItems = new Item[] {
            new Item("Aged Brie", 5, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        };
        GildedRose qualityApp = new GildedRose(qualityItems);

        // Update quality once
        qualityApp.updateQuality();

        // Both items should be capped at 50
        assertEquals(50, qualityItems[0].getQuality());
        assertEquals(50, qualityItems[1].getQuality());

        // Update quality again
        qualityApp.updateQuality();

        // Both items should still be capped at 50
        assertEquals(50, qualityItems[0].getQuality());
        assertEquals(50, qualityItems[1].getQuality());
    }

    @Test
    void testQualityNeverNegative() {
        // Create items with quality 0
        Item[] qualityItems = new Item[] {
            new Item("+5 Dexterity Vest", 10, 0),
            new Item("Elixir of the Mongoose", 5, 0)
        };
        GildedRose qualityApp = new GildedRose(qualityItems);

        // Update quality once
        qualityApp.updateQuality();

        // Quality should not go below 0
        assertEquals(0, qualityItems[0].getQuality());
        assertEquals(0, qualityItems[1].getQuality());
    }

    @Test
    void testAgedBrieIncreasesQualityAfterExpiry() {
        // Create Aged Brie that is expired
        Item[] brieItems = new Item[] {
            new Item("Aged Brie", 0, 10)
        };
        GildedRose brieApp = new GildedRose(brieItems);

        // Update quality once
        brieApp.updateQuality();

        // Aged Brie increases in quality by 2 after expiry
        assertEquals(-1, brieItems[0].getSellIn());
        assertEquals(12, brieItems[0].getQuality());
    }

    @Test
    void testBackstagePassesQualityDropsToZeroAfterExpiry() {
        // Create Backstage pass that is about to expire
        Item[] passItems = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)
        };
        GildedRose passApp = new GildedRose(passItems);

        // Update quality once
        passApp.updateQuality();

        // Backstage pass quality drops to 0 after the concert
        assertEquals(-1, passItems[0].getSellIn());
        assertEquals(0, passItems[0].getQuality());
    }

    @Test
    void testBackstagePassesQualityIncreaseRates() {
        // Create various backstage passes with different sellIn days
        Item[] passItems = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), // Normal increase (+1)
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20), // +2 (10 days or less)
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)   // +3 (5 days or less)
        };
        GildedRose passApp = new GildedRose(passItems);

        // Update quality once
        passApp.updateQuality();

        // Verify different increase rates
        assertEquals(21, passItems[0].getQuality()); // +1
        assertEquals(22, passItems[1].getQuality()); // +2
        assertEquals(23, passItems[2].getQuality()); // +3
    }

    @Test
    void testSulfurasNeverChanges() {
        // Create Sulfuras item
        Item[] sulfurasItems = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 0, 80)
        };
        GildedRose sulfurasApp = new GildedRose(sulfurasItems);

        // Update quality multiple times
        for (int i = 0; i < 10; i++) {
            sulfurasApp.updateQuality();
        }

        // Sulfuras never changes
        assertEquals(0, sulfurasItems[0].getSellIn());
        assertEquals(80, sulfurasItems[0].getQuality());
    }

    @Test
    void testNormalItemsDegradeQualityTwiceAsFastAfterExpiry() {
        // Create a normal item that's about to expire
        Item[] normalItems = new Item[] {
            new Item("+5 Dexterity Vest", 0, 10)
        };
        GildedRose normalApp = new GildedRose(normalItems);

        // Update quality once
        normalApp.updateQuality();

        // Quality should decrease by 2 after expiry
        assertEquals(-1, normalItems[0].getSellIn());
        assertEquals(8, normalItems[0].getQuality());
    }

    private void assertItem(String name, int expectedSellIn, int expectedQuality) {
        Item foundItem = null;
        for (Item item : items) {
            if (item.getName().equals(name)) {
                foundItem = item;
                break;
            }
        }
        assertNotNull(foundItem, "Item not found: " + name);
        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
            assertEquals(expectedSellIn, foundItem.getSellIn(), "Incorrect sellIn for " + name);
        }
        assertEquals(expectedQuality, foundItem.getQuality(), "Incorrect quality for " + name);
    }
}