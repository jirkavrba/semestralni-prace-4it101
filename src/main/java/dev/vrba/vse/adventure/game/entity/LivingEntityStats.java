package dev.vrba.vse.adventure.game.entity;

import com.sun.istack.NotNull;

public class LivingEntityStats {
    private int health;
    private int strength;

    public LivingEntityStats(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    public int getHealth() {
        return this.health;
    }

    public int getStrength() {
        return this.strength;
    }

    public void addBoost(@NotNull LivingEntityStats boost) {
        health += boost.health;
        strength += boost.strength;
    }

    public void removeBoost(@NotNull LivingEntityStats boost) {
        health -= boost.health;
        strength -= boost.strength;
    }
}
