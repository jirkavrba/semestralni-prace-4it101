package dev.vrba.vse.adventure.game;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.AdventureGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Trophy;
import dev.vrba.vse.adventure.game.plan.GamePlan;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.ui.TerminalCommandPrompt;

import java.util.NoSuchElementException;

public abstract class GameTest {

    private static class FakeCommandPrompt extends TerminalCommandPrompt {
        public FakeCommandPrompt(DungeonGame game) {
            super(game);
        }

        @Override
        public void startInputLoop() {
            // Do nothing so tests won't hang on awaiting input
        }
    }

    protected static DungeonGame createGameWithMockedCommandPrompt() {
        Trophy trophy = AdventureGame.createDefaultTrophy();
        Player player = AdventureGame.createDefaultPlayer();
        GamePlan plan = AdventureGame.createDefaultGamePlan(trophy);

        return createGameWithMockedCommandPrompt(trophy, player, plan);
    }

    protected static DungeonGame createGameWithMockedCommandPrompt(
            @NotNull Trophy trophy,
            @NotNull Player player,
            @NotNull GamePlan plan
    ) {

        DungeonGame game = new DungeonGame(player, plan);

        game.setTrophy(trophy);
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
