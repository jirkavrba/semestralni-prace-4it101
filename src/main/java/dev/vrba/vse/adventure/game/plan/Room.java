package dev.vrba.vse.adventure.game.plan;

import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.entity.items.Item;

import java.util.Set;

public interface Room {
    String getName();
    Set<RoomExit> getExits();
    Set<Item> getItems();
    Set<LivingEntity> getEnemies();

    void addExit(RoomExit exit);
    void addItem(Item item);
    void addEnemy(LivingEntity enemy);

    void removeItem(Item item);
    void removeEnemy(LivingEntity enemy);
}
