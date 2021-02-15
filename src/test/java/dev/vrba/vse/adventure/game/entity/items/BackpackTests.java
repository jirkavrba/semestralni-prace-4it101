package dev.vrba.vse.adventure.game.entity.items;

import org.junit.Test;

import static org.junit.Assert.*;

public class BackpackTests {
    @Test
    public void testBackpackCalculatesWeightOfItsItems() {
        Backpack backpack = new Backpack(100);

        assertEquals(100, backpack.getAvailableWeight());
        assertEquals(100, backpack.getRemainingWeight());
        assertEquals(0, backpack.getTotalItemsWeight());

        Coins something = new Coins(10);

        assertTrue(backpack.canAdd(something));

        backpack.add(something);

        assertEquals(100, backpack.getAvailableWeight());
        assertEquals(90, backpack.getRemainingWeight());
        assertEquals(10, backpack.getTotalItemsWeight());

        Coins another = new Coins(20);

        assertTrue(backpack.canAdd(another));

        backpack.add(another);

        assertEquals(100, backpack.getAvailableWeight());
        assertEquals(70, backpack.getRemainingWeight());
        assertEquals(30, backpack.getTotalItemsWeight());

        backpack.remove(something);

        assertEquals(100, backpack.getAvailableWeight());
        assertEquals(80, backpack.getRemainingWeight());
        assertEquals(20, backpack.getTotalItemsWeight());
    }

    @Test
    public void testBackpackCanOnlyCarryItemsUptoGivenWeight() {
        Backpack backpack = new Backpack(30);

        Coins firstItem = new Coins(10);
        Coins secondItem = new Coins(20);
        Coins thirdItem = new Coins(10);

        assertEquals(30, backpack.getRemainingWeight());
        assertTrue(backpack.canAdd(firstItem));

        backpack.add(firstItem);

        assertEquals(20, backpack.getRemainingWeight());
        assertTrue(backpack.canAdd(secondItem));

        backpack.add(secondItem);

        assertEquals(0, backpack.getRemainingWeight());
        assertFalse(backpack.canAdd(thirdItem));
    }
}
