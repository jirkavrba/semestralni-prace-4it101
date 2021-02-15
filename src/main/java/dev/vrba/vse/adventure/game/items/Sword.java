package dev.vrba.vse.adventure.game.items;

import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

public class Sword implements EquipableItem {

    private final int strength;

    public Sword(int strength) {
        this.strength = strength;
    }

    @Override
    public String getName() {
        return "meč";
    }

    @Override
    public int getWeight() {
        return 35;
    }

    @Override
    public LivingEntityStats getEquippedBoost() {
        return new LivingEntityStats(
                0,
                strength
        );
    }
}
