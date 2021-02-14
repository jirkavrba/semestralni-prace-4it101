package dev.vrba.vse.adventure.game.plan.items;

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

    public Backpack(int availableWeight, @NotNull Set<PickableItem> items) {
        this.availableWeight = availableWeight;
        this.items = items;
    }

    public int getAvailableWeight() {
        return this.availableWeight;
    }

    public int getTotalItemsWeight() {
        return this.items
                .stream()
                .map(PickableItem::getWeight)
                .reduce(0, Integer::sum);
    }

    public Set<PickableItem> getItems() {
        return this.items;
    }

    public boolean containsItem(@NotNull PickableItem item) {
        return this.items.contains(item);
    }

    public boolean canInsertItem(@NotNull PickableItem item) {
       return (this.getTotalItemsWeight() + item.getWeight()) <= this.getAvailableWeight();
    }

    public Backpack insertItem(@NotNull PickableItem item) {
        this.items.add(item);
        return this;
    }
}
