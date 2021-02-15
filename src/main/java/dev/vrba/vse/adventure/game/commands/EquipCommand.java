package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.EquipableItem;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;

public class EquipCommand implements Command {
    @Override
    public String getName() {
        return "vezmi";
    }

    @Override
    public DungeonGame execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou má hráč vzít do ruky.");
        }

        Player player = game.getPlayer();
        String name = String.join(" ", arguments);

        PickableItem item = player.getBackpack().getItems()
                .stream()
                .filter(current -> current.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Věc s názvem " + name + " nebyla v batohu nalezena."));

        if (!(item instanceof EquipableItem)) {
            throw new IllegalArgumentException("Věc s názvem " + item.getName() + " není možné vzít do ruky.");
        }

        player.equip((EquipableItem) item);
        return game;
    }
}
