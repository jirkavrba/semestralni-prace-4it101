package dev.vrba.vse.adventure.game.entity;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import dev.vrba.vse.adventure.game.items.Backpack;
import dev.vrba.vse.adventure.game.items.EquipableItem;

public class Player implements Entity {

    @Nullable
    private EquipableItem equippedItem = null;

    private final Backpack backpack;

    public Player(@NotNull Backpack backpack) {
        this.backpack = backpack;
    }

    @Override
    public String getName() {
        return "player";
    }

    public Backpack getBackpack() {
        return this.backpack;
    }

    public void equip(@NotNull EquipableItem item) {
        this.equippedItem = item;
    }

    public boolean hasEquippedItem() {
        return this.equippedItem != null;
    }

    @Nullable
    public EquipableItem getEquippedItem() {
        return this.equippedItem;
    }
}
