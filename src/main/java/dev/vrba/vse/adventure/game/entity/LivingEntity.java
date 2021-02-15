package dev.vrba.vse.adventure.game.entity;

/**
 * Rozhraní, představující žijící entitu, je to buď hráč, nebo nepřítel
 */
public interface LivingEntity extends Entity {
    /**
     * @return statistika entity, udávající její sílu a životy
     */
    LivingEntityStats getStats();

    /**
     * @return jestli je entita mrtvá
     */
    default boolean isDead() {
        return getStats().getHealth() <= 0;
    }

    /**
     * Udělí entitě zranění podle zadaného parametru
     * @param damage počet životů, které se entitě uberou
     */
    default void damage(int damage) {
        LivingEntityStats loss = new LivingEntityStats(damage, 0);
        this.getStats().removeBoost(loss);
    }
}
