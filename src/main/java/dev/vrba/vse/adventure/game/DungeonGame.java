package dev.vrba.vse.adventure.game;

public class DungeonGame implements Game {
    @Override
    public String getName() {
        return "Dungeon Game";
    }

    @Override
    public Game start() {
        return this;
    }
}
