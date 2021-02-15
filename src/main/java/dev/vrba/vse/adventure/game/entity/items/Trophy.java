package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

public class Trophy implements EquipableItem {

    private final String name;

    private final int weight;

    public Trophy(@NotNull String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public LivingEntityStats getEquippedBoost() {
        return new LivingEntityStats(0, 0);
    }
}
