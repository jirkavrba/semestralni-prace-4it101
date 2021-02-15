package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.items.Item;
import dev.vrba.vse.adventure.game.items.PickableItem;
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

        Room room = game.getGamePlan().getCurrentRoom();
        StringBuilder builder = new StringBuilder();

        builder.append("Nacházíš se v místnosti ")
                .append(Color.CYAN)
                .append(room.getName())
                .append(Color.RESET)
                .append("\n");

        printExists(builder);
        printItems(builder);

        return builder.toString();
    }

    private void printExists(@NotNull StringBuilder builder) {
        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

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
                    builder.append(" - ")
                            .append(Color.BLUE)
                            .append(exit.getTargetRoom().getName())
                            .append(Color.RESET)
                            .append("\n");
                }
            }

            if (!locked.isEmpty()) {
                builder.append("Východy, které není možné použít:\n");

                for (RoomExit exit : locked) {
                    builder.append(Color.RED)
                            .append(" - ")
                            .append("<Zamčený východ> - ")
                            .append(Color.RESET)
                            .append(exit.getReasonWhyCannotBeUsed())
                            .append("\n");
                }
            }
        }
    }

    private void printItems(@NotNull StringBuilder builder) {
        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        Set<Item> items = room.getItems();

        if (!items.isEmpty()) {
            builder.append("V místnosti se nachází následující věci:\n");

            for (Item item : items) {
                builder.append(" - ").append(item.getName());

                if (item instanceof PickableItem) {
                    PickableItem pickableItem = (PickableItem) item;

                    builder.append(" - váha: ")
                            .append(player.getBackpack().canAdd(pickableItem) ? Color.GREEN : Color.RED)
                            .append(pickableItem.getWeight())
                            .append(" (")
                            .append(player.getBackpack().canAdd(pickableItem) ? "tuto věc lze sebrat" : "tato věc je příliš těžká")
                            .append(")")
                            .append(Color.RESET);
                }

                builder.append("\n");
            }
        }
    }
}
