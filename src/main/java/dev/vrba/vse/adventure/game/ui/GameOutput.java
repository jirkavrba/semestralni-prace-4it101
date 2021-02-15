package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;

public class GameOutput {

    public static class TerminalColor {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
    }

    private final DungeonGame game;

    public GameOutput(@NotNull DungeonGame game) {
        this.game = game;
    }

    @NotNull
    public String printCurrentState() {
        if (!game.isPlaying()) {
            return "Hra skončila";
        }

        return "Nacházíš se v místnosti " +
                TerminalColor.CYAN +
                game.getGamePlan().getCurrentRoom().getName() +
                TerminalColor.RESET;
    }
}
