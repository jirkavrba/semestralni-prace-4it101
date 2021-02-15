package dev.vrba.vse.adventure.game.items;

import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

public interface EquipableItem extends PickableItem {
    LivingEntityStats getEquippedBoost();
}
