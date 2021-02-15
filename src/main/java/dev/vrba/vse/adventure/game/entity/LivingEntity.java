package dev.vrba.vse.adventure.game.entity;

public interface LivingEntity extends Entity {
    LivingEntityStats getStats();

    default boolean isDead() {
        return getStats().getHealth() <= 0;
    }

    default void damage(int damage) {
        LivingEntityStats loss = new LivingEntityStats(damage, 0);
        this.getStats().removeBoost(loss);
    }
}
