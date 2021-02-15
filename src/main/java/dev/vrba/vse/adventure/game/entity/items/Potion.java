package dev.vrba.vse.adventure.game.entity.items;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

/**
 * Třída reprezentující lektvar, který je možné vypít
 */
public class Potion implements ConsumableItem {

    private final String name;

    private final LivingEntityStats boost;

    private final int weight;

    public Potion(@NotNull String name, @NotNull LivingEntityStats boost, int weight) {
        this.name = name;
        this.boost = boost;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LivingEntityStats getBoostWhenUsed() {
        return this.boost;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }
}
