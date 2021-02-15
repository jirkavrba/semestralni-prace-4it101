package dev.vrba.vse.adventure.game.entity;

import com.sun.istack.NotNull;

/**
 * Třída reprezentující nepřítele, který bude na hráče útočit, dokud jeden ze zúčastněných nezemře
 */
public class Enemy implements LivingEntity {

    private final String name;

    private final LivingEntityStats stats;

    public Enemy(@NotNull String name, @NotNull LivingEntityStats stats) {
        this.name = name;
        this.stats = stats;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LivingEntityStats getStats() {
        return this.stats;
    }
}
