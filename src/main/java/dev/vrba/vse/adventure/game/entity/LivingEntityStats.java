package dev.vrba.vse.adventure.game.entity;

import com.sun.istack.NotNull;

/**
 * Třída reprezentující vlastnosti entity, obsahuje životy a sílu
 */
public class LivingEntityStats {
    private int health;
    private int strength;

    public LivingEntityStats(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    /**
     * @return kolik má entita životů
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * @return jakou má entita sílu
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Aplikuje (dočasné) zlepšení vlastností
     * @param boost zlepšení, které se má aplikovat
     */
    public void addBoost(@NotNull LivingEntityStats boost) {
        health += boost.health;
        strength += boost.strength;
    }

    /**
     * Zruší aplikaci zlepšení vlastností
     * @param boost zlepšení, jehož aplikace se má zrušit
     */
    public void removeBoost(@NotNull LivingEntityStats boost) {
        health -= boost.health;
        strength -= boost.strength;
    }
}
