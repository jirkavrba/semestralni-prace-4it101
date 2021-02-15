package dev.vrba.vse.adventure.game.plan;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.entity.Enemy;
import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.entity.items.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementace rozhraní místnosti
 */
public class BasicRoom implements Room {

    private final String name;

    private final Set<RoomExit> exits;

    private final Set<Item> items;

    private final Set<LivingEntity> enemies;

    public BasicRoom(@NotNull String name) {
        this.name = name;
        this.exits = new HashSet<>();
        this.items = new HashSet<>();
        this.enemies = new HashSet<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<RoomExit> getExits() {
        return this.exits;
    }

    @Override
    public Set<Item> getItems() {
        return this.items;
    }

    @Override
    public Set<LivingEntity> getEnemies() {
       return this.enemies;
    }

    @Override
    public void addExit(@NotNull RoomExit exit) {
        this.exits.add(exit);
    }

    @Override
    public void addItem(@NotNull Item item) {
        this.items.add(item);
    }

    @Override
    public void addEnemy(@NotNull LivingEntity enemy) {
        this.enemies.add(enemy);
    }

    @Override
    public void removeItem(@NotNull Item item) {
        this.items.remove(item);
    }

    @Override
    public void removeEnemy(@NotNull LivingEntity enemy) {
        this.enemies.remove(enemy);
    }
}
