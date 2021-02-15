package dev.vrba.vse.adventure.game.entity;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import dev.vrba.vse.adventure.game.entity.items.Backpack;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;

public class Player implements LivingEntity {
    @Nullable
    private EquipableItem equippedItem = null;

    private final LivingEntityStats stats;

    private final Backpack backpack;

    public Player(@NotNull Backpack backpack, @NotNull LivingEntityStats stats) {
        this.backpack = backpack;
        this.stats = stats;
    }

    @Override
    public String getName() {
        return "player";
    }

    public Backpack getBackpack() {
        return this.backpack;
    }

    public void equip(@Nullable EquipableItem item) {
        if (this.equippedItem != null) {
            this.stats.removeBoost(this.equippedItem.getEquippedBoost());
        }

        this.equippedItem = item;

        if (item != null) {
            this.stats.addBoost(item.getEquippedBoost());
        }
    }

    public boolean hasEquippedItem() {
        return this.equippedItem != null;
    }

    @Nullable
    public EquipableItem getEquippedItem() {
        return this.equippedItem;
    }

    @Override
    public LivingEntityStats getStats() {
        return this.stats;
    }
}
