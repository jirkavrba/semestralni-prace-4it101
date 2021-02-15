package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.ConsumableItem;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
import dev.vrba.vse.adventure.game.ui.Color;

/**
 * Příkaz sloužící k vypití lektvaru (nebo jiné implementace ConsumableItem)
 */
public class ConsumeCommand implements Command {
    @Override
    public String getName() {
        return "vypij";
    }

    @Override
    public String getDescription() {
        return "Umožňuje vypít lektvar, a získat zlepšení schopností\n" +
               "např. " + Color.GREEN + "vypij <jméno lektvaru>" + Color.RESET;
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno předmětu, který chceš vypít.");
        }

        Player player = game.getPlayer();
        String name = String.join(" ", arguments);

        PickableItem item = player.getBackpack()
                .getItems()
                .stream()
                .filter(current -> current.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Věc s názvem " + name + " nebyla v batohu nalezena."));

        if (!(item instanceof ConsumableItem)) {
            throw new IllegalArgumentException("Věc s názvem " + item.getName() + " není možné vypít.");
        }

        ConsumableItem consumableItem = (ConsumableItem) item;

        player.getStats().addBoost(consumableItem.getBoostWhenUsed());
        player.getBackpack().remove(item);
    }
}
