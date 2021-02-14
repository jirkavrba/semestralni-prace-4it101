package dev.vrba.vse.adventure.plan.items;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.plan.items.Backpack;
import dev.vrba.vse.adventure.game.plan.items.PickableItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class BackpackTests {

    private static class SimplePickableItem implements PickableItem {
        private final String name;
        private final int weight;

        public SimplePickableItem(@NotNull String name, @NotNull int weight) {
            this.name = name;
            this.weight = weight;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getWeight() {
            return weight;
        }
    }

    @Test
    public void testBackpackCalculatesWeightOfItsItems() {
        Backpack backpack = new Backpack(100);

        assertEquals(100, backpack.getAvailableWeight());
        assertEquals(100, backpack.getRemainingWeight());
        assertEquals(0, backpack.getTotalItemsWeight());

        SimplePickableItem something = new SimplePickableItem("Something", 10);

        assertTrue(backpack.canAdd(something));

        backpack.add(something);

        assertEquals(100, backpack.getAvailableWeight());
        assertEquals(90, backpack.getRemainingWeight());
        assertEquals(10, backpack.getTotalItemsWeight());

        SimplePickableItem another = new SimplePickableItem("Another thing", 20);

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

        SimplePickableItem firstItem = new SimplePickableItem("First", 10);
        SimplePickableItem secondItem = new SimplePickableItem("Second", 20);
        SimplePickableItem thirdItem = new SimplePickableItem("Third", 10);

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
