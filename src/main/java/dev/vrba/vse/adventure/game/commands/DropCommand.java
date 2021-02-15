package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.items.PickableItem;
import dev.vrba.vse.adventure.game.plan.Room;

import java.util.Optional;

public class DropCommand implements Command {
    @Override
    public String getName() {
        return "polož";
    }

    @Override
    public DungeonGame execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou má hráč položit.");
        }

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        String name = String.join(" ", arguments);
        Optional<PickableItem> item = player.getBackpack().getItems()
                .stream()
                .filter(current -> current.getName().equals(name))
                .findFirst();

        if (!item.isPresent()) {
            throw new IllegalArgumentException("Věc s názvem " + name + " nebyla v batohu nalezena.");
        }

        room.addItem(item.get());
        player.getBackpack().remove(item.get());

        return game;
    }
}
