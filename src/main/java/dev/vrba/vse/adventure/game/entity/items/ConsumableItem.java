package dev.vrba.vse.adventure.game.entity.items;

import dev.vrba.vse.adventure.game.entity.LivingEntityStats;

public interface ConsumableItem extends PickableItem {
    LivingEntityStats getBoostWhenUsed();
}
