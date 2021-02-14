package dev.vrba.vse.adventure;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.Game;

public class AdventureGame {

    /**
     * Application entrypoint, this creates the game instance, instantiates the game plan and opens command prompt
     */
    public static void main(String[] args) {
        Game game = new DungeonGame();
        game.start();
    }
}
