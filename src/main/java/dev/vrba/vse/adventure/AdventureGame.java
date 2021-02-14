package dev.vrba.vse.adventure;

import dev.vrba.vse.adventure.game.DungeonGame;

public class AdventureGame {

    /**
     * Application entrypoint, this creates the game instance, instantiates the game plan and opens command prompt
     */
    public static void main(String[] args) {
        new DungeonGame().start();
    }
}
