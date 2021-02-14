package dev.vrba.vse.adventure.game.entity;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.plan.items.PickableItem;

import java.util.Set;

public interface ItemsCarryingEntity extends Entity {
    int getAvailableWeight();
    int getTotalItemsWeight();
    Set<PickableItem> getItems();
    boolean canPickup(@NotNull PickableItem item);
    ItemsCarryingEntity pickup(PickableItem item);
}
