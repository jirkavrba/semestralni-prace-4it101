package dev.vrba.vse.adventure.game;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.ui.CommandPrompt;

import java.util.NoSuchElementException;

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

    protected static Room findRoomByName(@NotNull DungeonGame game, @NotNull String name) {
        return game.getGamePlan()
                .getRooms()
                .stream()
                .filter(room -> room.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
