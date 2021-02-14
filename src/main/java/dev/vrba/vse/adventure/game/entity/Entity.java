package dev.vrba.vse.adventure.game.entity;

import com.sun.istack.NotNull;

/**
 * An interface representing an entity of the game, this can be a number of things, the player, items within dungeon,
 * enemies, ...
 */
public interface Entity {
    @NotNull
    String getName();
}
