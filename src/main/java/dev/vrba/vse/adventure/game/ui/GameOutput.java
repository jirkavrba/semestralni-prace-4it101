package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.plan.RoomExit;

@SuppressWarnings("SpellCheckingInspection")
public class GameOutput {

    public static class TerminalColor {
        public static final String RESET = "\u001B[0m";
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
            return TerminalColor.GREEN + "Hra skončila." + TerminalColor.RESET;
        }

        StringBuilder builder = new StringBuilder();

        Room room = game.getGamePlan().getCurrentRoom();

        builder.append("Nacházíš se v místnosti ")
                .append(TerminalColor.CYAN)
                .append(room.getName())
                .append(TerminalColor.RESET)
                .append("\n");

        if (!room.getExits().isEmpty()) {
            builder.append("Z místnosti jsou následující východy:\n");

            for (RoomExit exit : room.getExits()) {
                builder.append(TerminalColor.BLUE)
                       .append(exit.getTo().getName())
                       .append(TerminalColor.RESET)
                       .append(",");
            }
        }

        return builder.toString();
    }
}
