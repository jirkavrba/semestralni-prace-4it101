package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Backpack {

    private final int availableWeight;

    private final Set<PickableItem> items;

    public Backpack(int availableWeight) {
        this.availableWeight = availableWeight;
        this.items = new HashSet<>();
    }

    public int getAvailableWeight() {
        return availableWeight;
    }

    public int getRemainingWeight() {
        return availableWeight - getTotalItemsWeight();
    }

    public int getTotalItemsWeight() {
        return items
                .stream()
                .map(PickableItem::getWeight)
                .reduce(0, Integer::sum);
    }

    public Set<PickableItem> getItems() {
        return items;
    }

    public boolean containsItem(@NotNull PickableItem item) {
        return items.contains(item);
    }

    public boolean canAdd(@NotNull PickableItem item) {
       return item.getWeight() <= this.getRemainingWeight();
    }

    public Backpack add(@NotNull PickableItem item) {
        this.items.add(item);
        return this;
    }

    public Backpack remove(@NotNull PickableItem item) {
        this.items.remove(item);
        return this;
    }
}
