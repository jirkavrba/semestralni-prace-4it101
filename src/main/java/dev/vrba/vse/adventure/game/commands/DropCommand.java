package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.ui.Color;

/**
 * Příkaz sloužící k vyhození předmětu z batohu
 */
public class DropCommand implements Command {

    @Override
    public String getName() {
        return "polož";
    }

    @Override
    public String getDescription() {
        return "Umožňuje vyhodit předmět z batohu.\n" +
                "např. " + Color.GREEN + "polož <jméno předmětu>" + Color.RESET;
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou chceš položit.");
        }

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        String name = String.join(" ", arguments);
        PickableItem item = player.getBackpack().getItems()
                .stream()
                .filter(current -> current.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Věc s názvem " + name + " nebyla v batohu nalezena."));

        room.addItem(item);
        player.getBackpack().remove(item);

        if (player.hasEquippedItem() && player.getEquippedItem().equals(item)) {
            player.equip(null);
        }

    }
}
