package dev.vrba.vse.adventure.game.ui;

import com.sun.istack.NotNull;
import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.LivingEntity;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Backpack;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;
import dev.vrba.vse.adventure.game.entity.items.Item;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
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

        printBackpack(builder);
        printEquippedItem(builder);
        printPlayerStats(builder);
        printCurrentRoom(builder);
        printEnemies(builder);
        printExists(builder);
        printItems(builder);

        return builder.toString();
    }

    private void printBackpack(@NotNull StringBuilder builder) {
        Backpack backpack = game.getPlayer().getBackpack();

        builder.append("Váha věcí v batohu: " + Color.BLUE)
                .append(backpack.getTotalItemsWeight())
                .append("/").append(backpack.getAvailableWeight())
                .append(Color.RESET)
                .append("\n");

        if (!backpack.getItems().isEmpty()) {
            Iterable<String> names = backpack.getItems()
                    .stream()
                    .map(current -> {
                        if (current instanceof EquipableItem) {
                            return Color.PURPLE + current.getName() + Color.RESET;
                        }
                        // TODO: Add support for colored consumables
                        else {
                            return current.getName();
                        }
                    })
                    .collect(Collectors.toList());

            builder.append("Věci v batohu: ")
                    .append(String.join(", ", names))
                    .append("\n");
        }
    }

    private void printEquippedItem(@NotNull StringBuilder builder) {
        Player player = game.getPlayer();

        if (player.hasEquippedItem()) {
            builder.append("Momentálně držíš: ")
                    .append(Color.PURPLE)
                    .append(player.getEquippedItem().getName())
                    .append(Color.RESET)
                    .append("\n");
        }
    }

    private void printPlayerStats(@NotNull StringBuilder builder) {
        Player player = game.getPlayer();

        builder.append("Tvoje životy: ")
                .append(Color.RED)
                .append(player.getStats().getHealth())
                .append(Color.RESET)
                .append("\n")
                .append("Tvoje síla: ")
                .append(Color.YELLOW)
                .append(player.getStats().getStrength())
                .append(Color.RESET)
                .append("\n");
    }

    private void printCurrentRoom(@NotNull StringBuilder builder) {
        Room room = game.getGamePlan().getCurrentRoom();

        builder.append("Nacházíš se v místnosti ")
                .append(Color.CYAN)
                .append(room.getName())
                .append(Color.RESET)
                .append("\n");
    }

    private void printEnemies(@NotNull StringBuilder builder) {
        Room room = game.getGamePlan().getCurrentRoom();

        if (!room.getEnemies().isEmpty()) {
            builder.append(Color.RED)
                    .append("V místnosti se nachází nepřátelé!\n")
                    .append(Color.RESET);

            for (LivingEntity enemy : room.getEnemies()) {
                builder.append(" - ")
                        .append(Color.RED)
                        .append(enemy.getName())
                        .append(Color.RESET)
                        .append(" (")
                        .append(Color.RED)
                        .append("životy: ")
                        .append(enemy.getStats().getHealth())
                        .append(Color.RESET)
                        .append(", ")
                        .append(Color.YELLOW)
                        .append("síla: ")
                        .append(enemy.getStats().getStrength())
                        .append(Color.RESET)
                        .append(")\n");
            }
        }
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
