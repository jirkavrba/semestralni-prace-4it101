package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.entity.items.Item;
import dev.vrba.vse.adventure.game.entity.items.PickableItem;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.ui.Color;

import java.util.Optional;

/**
 * Příkaz sloužící k sebrání předmětu (instance implementující PickableItem) jeho vložení do batohu
 */
public class PickCommand implements Command {

    @Override
    public String getName() {
        return "seber";
    }


    @Override
    public String getDescription() {
        return "Umožňuje sebrat věc v místnosti a dát jí do batohu, pokud není příliš těžká.\n" +
                "např. " + Color.GREEN + "seber <jméno věci v místnosti>" + Color.RESET;
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno věci, kterou má hráč sebrat.");
        }

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        String name = String.join(" ", arguments);
        Optional<Item> target = room.getItems()
                .stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();

        if (!target.isPresent()) {
            throw new IllegalArgumentException("Věc s názvem " + name + " nebyla v aktuální místnosti nalezena.");
        }

        if (!(target.get() instanceof PickableItem)) {
            throw new IllegalArgumentException("Věc s názvem " + name + " nelze sebrat.");
        }

        PickableItem item = (PickableItem) target.get();

        if (!player.getBackpack().canAdd(item)) {
            throw new IllegalArgumentException("Věc s názvem " + name + " je příliš těžká a nevešla by se do batohu.");
        }

        room.removeItem(item);
        player.getBackpack().add(item);

    }
}
