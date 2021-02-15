package dev.vrba.vse.adventure.game.entity.items;

import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

/**
 * Rozhraní představující věc, kterou je možné si vzít do ruky
 */
public interface EquipableItem extends PickableItem {
    /**
     * @return vylepšení statistik, které hráč získá, pokud danou věc drží v ruce
     */
    LivingEntityStats getEquippedBoost();
}
