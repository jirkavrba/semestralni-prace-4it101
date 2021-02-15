package dev.vrba.vse.adventure.game.entity;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import dev.vrba.vse.adventure.game.entity.items.Backpack;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;

/**
 * Třída reprezentující hráče, který může procházet mapou, sbírat věci a útočit na nepřátelé
 */
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

    @Override
    public LivingEntityStats getStats() {
        return this.stats;
    }

    /**
     * @return vrátí instanci batohu, který hráč nosí
     */
    public Backpack getBackpack() {
        return this.backpack;
    }

    /**
     * Hráč si vezme věc, předanou v parametru do ruky, což způsobí aplikaci všech zlepšení vlastností
     * @param item věc, kterou si má hráč vzít do ruky
     */
    public void equip(@Nullable EquipableItem item) {
        if (this.equippedItem != null) {
            this.stats.removeBoost(this.equippedItem.getEquippedBoost());
        }

        this.equippedItem = item;

        if (item != null) {
            this.stats.addBoost(item.getEquippedBoost());
        }
    }

    /**
     * @return jestli má hráč momentálně nějakou věc v ruce
     */
    public boolean hasEquippedItem() {
        return this.equippedItem != null;
    }

    /**
     * @return věc, kterou má momentálně hráč v ruce
     */
    @Nullable
    public EquipableItem getEquippedItem() {
        return this.equippedItem;
    }
}
