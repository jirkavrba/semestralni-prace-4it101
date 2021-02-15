package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.plan.RoomExit;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("SpellCheckingInspection")
public class GameOutput {

    private final DungeonGame game;

    public GameOutput(@NotNull DungeonGame game) {
        this.game = game;
    }

    @NotNull
    public String printCurrentState() {
        if (!game.isPlaying()) {
            return Color.GREEN + "Hra skončila." + Color.RESET;
        }

        StringBuilder builder = new StringBuilder();

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        builder.append("Nacházíš se v místnosti ")
                .append(Color.CYAN)
                .append(room.getName())
                .append(Color.RESET)
                .append("\n");

        if (!room.getExits().isEmpty()) {

            Set<RoomExit> usable = room.getExits()
                    .stream()
                    .filter(exit -> exit.canBeUsed(player))
                    .collect(Collectors.toSet());

            Set<RoomExit> locked = room.getExits()
                    .stream()
                    .filter(exit -> !exit.canBeUsed(player))
                    .collect(Collectors.toSet());

            if (!usable.isEmpty()) {
                builder.append("Východy, které je možné použít:\n");

                for (RoomExit exit : usable) {
                    builder.append(Color.BLUE)
                            .append(exit.getTo().getName())
                            .append(Color.RESET)
                            .append("\n");
                }
            }

            if (!locked.isEmpty()) {
                builder.append("Východy, které není možné použít:\n");

                for (RoomExit exit : locked) {
                    builder.append(Color.RED)
                            .append("<Zamčený východ> - ")
                            .append(exit.getReasonWhyCannotBeUsed())
                            .append("\n");
                }
            }
        }

        return builder.toString();
    }
}
