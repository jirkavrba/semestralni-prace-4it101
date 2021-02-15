package dev.vrba.vse.adventure.game.entity.items;

/**
 * Rozhraní představující věc, kterou je možné zvednou a dát do batohu
 */
public interface PickableItem extends Item {
    /**
     * @return váha věci
     */
    int getWeight();
}
