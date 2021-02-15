package dev.vrba.vse.adventure.game.commands;

import dev.vrba.vse.adventure.game.DungeonGame;

/**
 * Příkaz, sloužící k ukončení hry
 */
public class ExitCommand implements Command {

    @Override
    public String getName() {
        return "konec";
    }

    @Override
    public String getDescription() {
        return "Ukončí hru";
    }

    @Override
    public void execute(DungeonGame game, String... arguments) {
        game.stop();
    }
}
