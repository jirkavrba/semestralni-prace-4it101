package dev.vrba.vse.adventure.game.entity.items;

import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

/**
 * Rozhraní představující věc, kterou je možné vypít (spotřebovat)
 */
public interface ConsumableItem extends PickableItem {
    /**
     * @return vylepšení statistik, které hráč získá po vypití
     */
    LivingEntityStats getBoostWhenUsed();
}
