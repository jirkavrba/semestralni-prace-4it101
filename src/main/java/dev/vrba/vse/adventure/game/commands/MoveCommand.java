package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;
import dev.vrba.vse.adventure.game.entity.Player;
import dev.vrba.vse.adventure.game.plan.Room;
import dev.vrba.vse.adventure.game.plan.RoomExit;

import java.util.Optional;

public class MoveCommand implements Command {
    @Override
    public String getName() {
        return "jdi";
    }

    @Override
    public DungeonGame execute(DungeonGame game, String... arguments) {
        Player player = game.getPlayer();
        Room room = game.getGamePlan().getCurrentRoom();

        if (arguments.length != 1) {
            throw new IllegalArgumentException("Příkaz vyžaduje právě jeden argument, a to jméno cílové místnosti.");
        }

        String name = arguments[0];
        Optional<RoomExit> chosenExit = room.getExits()
                .stream()
                .filter(exit -> exit.canBeUsed(player))
                .filter(exit -> exit.getTargetRoom().getName().equals(name))
                .findFirst();

        if (!chosenExit.isPresent()) {
            throw new IllegalArgumentException("Východ do místnosti " + name + " nenalezen!");
        }

        game.getGamePlan().setCurrentRoom(chosenExit.get().getTargetRoom());

        return game;
    }
}
