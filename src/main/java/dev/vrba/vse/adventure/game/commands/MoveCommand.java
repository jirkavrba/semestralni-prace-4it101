package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.plan.RoomExit;
import dev.vrba.vse.adventure.game.ui.Color;

import java.util.Optional;

/**
 * Příkaz, sloužící k přechodu mezi místnostmi
 */
public class MoveCommand implements Command {

    @Override
    public String getName() {
        return "jdi";
    }

    @Override
    public String getDescription() {
        return "Umožňuje průchod mezi místnostmi.\n" +
                "např. " + Color.GREEN + "jdi <jméno místnosti>" + Color.RESET;
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno cílové místnosti.");
        }

        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        if (!room.getEnemies().isEmpty()) {
            throw new IllegalArgumentException("Nemůžeš utéct z místnosti, kde jsou nepřátelé.");
        }

        String name = String.join(" ", arguments);
        Optional<RoomExit> chosenExit = room.getExits()
                .stream()
                .filter(exit -> exit.canBeUsed(player))
                .filter(exit -> exit.getTargetRoom().getName().equals(name))
                .findFirst();

        if (!chosenExit.isPresent()) {
            throw new IllegalArgumentException("Východ do místnosti " + name + " nenalezen!");
        }

        game.getGamePlan().setCurrentRoom(chosenExit.get().getTargetRoom());

    }
}
