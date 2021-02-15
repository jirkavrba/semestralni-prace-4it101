package dev.vrba.vse.adventure.game;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.ui.CommandPrompt;

public abstract class GameTest {

     private static class FakeCommandPrompt extends CommandPrompt {
        public FakeCommandPrompt(DungeonGame game) {
            super(game);
        }

        @Override
        public void startInputLoop() {
            // Do nothing so tests won't hang on awaiting input
        }
    }

    protected static DungeonGame createGameWithMockedCommandPrompt() {
         DungeonGame game = new DungeonGame();

         game.setCommandPrompt(new FakeCommandPrompt(game));
         game.start();

         return game;
    }
}
